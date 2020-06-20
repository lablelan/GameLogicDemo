package com.lan.game.timer.impl;
import com.lan.game.player.Player;
import com.lan.game.util.GSConst;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.Calendar;
import java.util.Date;

public class TimerTest extends TestCase {

    @Test
    public void testSetTimeOut() {
        Timer t = new Timer();
        Player p = new Player();
        p.setTimer(t);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 0,1, 0,0,0);
        Date d = calendar.getTime();
        PlayerTimerCallBack playerTimerCfg = new PlayerTimerCallBack(p,
                new TimerData("test", d.getTime()),
                new TimerArgs(false, TimerType.TIMEOUT,10000, d.getTime()));
        t.setTimeOut(playerTimerCfg);
        assertEquals(t.getTimerNameMapToCfg().size(), 1);
        assertEquals(p.getName(), "");
        p.tick(d.getTime() + 9999);
        assertEquals(p.getName(), "");
        assertEquals(t.getTimerNameMapToCfg().size(), 1);
        p.tick(d.getTime() + 10000);
        assertEquals(p.getName(), "xiaoming");
        assertEquals(t.getTimerNameMapToCfg().size(), 0);
    }

    public void testStartInterval() {
        Timer t = new Timer();
        Player p = new Player();
        p.setTimer(t);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 0,1, 0,0,0);
        Date d = calendar.getTime();
        PlayerTimerCallBack playerTimerCfg = new PlayerTimerCallBack(p,
                new TimerData("test", d.getTime()),
                new TimerArgs(false, TimerType.HOUR_INTERVAL,1, d.getTime()));
        t.startInterval(playerTimerCfg);
        assertEquals(t.getTimerNameMapToCfg().size(), 1);
        assertEquals(p.getName(), "");
        p.tick(d.getTime() + GSConst.HOUR * 0);
        assertEquals(p.getName(), "");
        assertEquals(t.getTimerNameMapToCfg().size(), 1);
        // 一个小时过去
        p.tick(d.getTime() + GSConst.HOUR * 1);
        assertEquals(p.getName(), "xiaoming");
        assertEquals(t.getTimerNameMapToCfg().size(), 1);
        // 一个半小时过去
        p.tick(d.getTime() + GSConst.MIN * 90);
        assertEquals(t.getTimerNameMapToCfg().size(), 1);
        // 到第二个小时
        p.tick(d.getTime() + GSConst.HOUR * 2);
        assertEquals(t.getTimerNameMapToCfg().size(), 1);
        // 回退时间无法触发
        p.tick(d.getTime() + GSConst.MIN * 78);
        assertEquals(t.getTimerNameMapToCfg().size(), 1);
        p.tick(d.getTime() + GSConst.HOUR * 2);
        assertEquals(t.getTimerNameMapToCfg().size(), 1);
        // 第三个小时过去
        p.tick(d.getTime() + GSConst.HOUR * 3);
        assertEquals(t.getTimerNameMapToCfg().size(), 1);
    }

    public void testClearInterval() {
        Timer t = new Timer();
        Player p = new Player();
        p.setTimer(t);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 0,1, 0,0,0);
        Date d = calendar.getTime();
        PlayerTimerCallBack playerTimerCfg = new PlayerTimerCallBack(p,
                new TimerData("test", d.getTime()),
                new TimerArgs(false, TimerType.HOUR_INTERVAL,1, d.getTime()));
        t.startInterval(playerTimerCfg);
        // 清除定时器
        assertEquals(t.getTimerNameMapToCfg().size(), 1);
        t.clearInterval("test");
        assertEquals(t.getTimerNameMapToCfg().size(), 0);
    }
}