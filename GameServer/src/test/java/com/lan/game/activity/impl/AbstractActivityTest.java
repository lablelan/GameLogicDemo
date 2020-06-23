package com.lan.game.activity.impl;

import com.alibaba.fastjson.JSONObject;
import com.lan.game.GsMain;
import com.lan.game.config.GameConfig;
import com.lan.game.player.Player;
import com.lan.game.timer.impl.Timer;
import com.lan.game.util.FileUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class AbstractActivityTest extends TestCase {

    @Test
    public void testTyrRefresh() {
        // 读取活动配置
        URL jsonPath = getClass().getResource("/activity.json");
        String jsonStr = FileUtil.ReadFile(jsonPath.getPath());
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        GameConfig.getUniqueInstance().getActivityConfig().loadFromJsonObject(jsonObject);
        InputStream in = GsMain.class.getResourceAsStream("/activityType.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ActivityFactory.getUniqueInstance().setProperties(properties);
        Timer timer = new Timer();
        Player player = new Player();
        player.setTimer(timer);
        timer.setTarget(player);
        ActivityManager activityManager = new ActivityManager();
        activityManager.setTarget(player);
        player.setActivityManager(activityManager);
        Calendar calendar = Calendar.getInstance();
        // 活动没有开启
        calendar.set(2019, 0,1, 0,0,0);
        Date d = calendar.getTime();
        activityManager.init(d.getTime());
        assertEquals(3, timer.getTimerNameMapToCfg().size());
        assertEquals(3, activityManager.getActivityMap().size());
        assertEquals(false, activityManager.isActivityGoing(1));
        assertEquals(false, activityManager.isActivityGoing(2));
        assertEquals(false, activityManager.isActivityGoing(3));
        // 调用tick让时间流逝 打开id为1的活动
        calendar.set(2020, 0,1, 8,0,0);
        Date d1 = calendar.getTime();
        activityManager.refreshActivity(d1.getTime());
        player.tick(d1.getTime());
        assertEquals(3, timer.getTimerNameMapToCfg().size());
        assertEquals(3, activityManager.getActivityMap().size());
        assertEquals(true, activityManager.isActivityGoing(1));
        // 领取奖励
        ActivityClaimRecord record1 = (ActivityClaimRecord) activityManager.getActivityById(1).getRecord();
        assertEquals(record1.isClaim(), false);
        activityManager.claim(1,null);
        assertEquals(record1.isClaim(), true);
        assertEquals(false, activityManager.isActivityGoing(2));
        assertEquals(false, activityManager.isActivityGoing(3));
        // 调用tick让时间流逝 打开id为2的活动
        calendar.set(2020, 0,1, 13,0,1);
        Date d2 = calendar.getTime();
        activityManager.refreshActivity(d2.getTime());
        player.tick(d2.getTime());
        assertEquals(2, timer.getTimerNameMapToCfg().size());
        assertEquals(3, activityManager.getActivityMap().size());
        assertEquals(false, activityManager.isActivityGoing(1));
        assertEquals(true, activityManager.isActivityGoing(2));
        assertEquals(false, activityManager.isActivityGoing(3));
        // 领取奖励
        ActivityClaimRecord record2 = (ActivityClaimRecord) activityManager.getActivityById(2).getRecord();
        assertEquals(record2.isClaim(), false);
        activityManager.claim(2,null);
        assertEquals(record2.isClaim(), true);
        // 重复领取
        assertEquals(activityManager.claim(2,null), false);
        assertEquals(false, activityManager.isActivityGoing(1));
        assertEquals(true, activityManager.isActivityGoing(2));
        assertEquals(false, activityManager.isActivityGoing(3));
        // 此时活动1已经关闭 数据区重置
        assertEquals(false, record1.isClaim());
    }
}