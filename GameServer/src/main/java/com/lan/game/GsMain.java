package com.lan.game;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lan.game.activity.impl.*;
import com.lan.game.player.Player;
import com.lan.game.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class GsMain {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, ParseException {
//        Properties properties = new Properties();
//        InputStream in = GsMain.class.getResourceAsStream("/activityType.properties");
//        properties.load(in);
//        factory.setProperties(properties);
//        Player p = new Player();
//        ActivityClaim act = (ActivityClaim) factory.createActivity(1, "CLAIM", p, new ActivityStat());
//        ActivityClaimRecord record = (ActivityClaimRecord)act.getRecord();
//        System.out.println(record.isClaim());
//        act.onClaim(p, "");
//        System.out.println(record.isClaim());
//        System.out.println(properties.getProperty("CLAIM"));
//
//        URL url = GsMain.class.getResource("/activity.json");
//        System.out.println(url.getPath());
//        String str = FileUtil.ReadFile(url.getPath());
//        JSONObject json = JSON.parseObject(str);
////        System.out.println(json.values());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        Date d = simpleDateFormat.parse("2020-01-01 00:00:00");
//        System.out.println(d);
////        ActivityConfig config = JSONObject.toJavaObject(json, ActivityConfig.class);
////        System.out.println(config);

    }
}
