package com.lan.game.timer.impl;

/**
 * 定时器相关数据 保存数据库用于恢复定时器
 */
public class TimerData {
    String id = "";     // timer的唯一id
    long lastTick = 0;  // timer最后执行的时间戳

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLastTick() {
        return lastTick;
    }

    public void setLastTick(long lastTick) {
        this.lastTick = lastTick;
    }

    public TimerData(String id, long lastTick) {
        this.id = id;
        this.lastTick = lastTick;
    }
}
