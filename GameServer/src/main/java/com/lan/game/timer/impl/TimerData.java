package com.lan.game.timer.impl;

/**
 * @author lanbei
 * 定时器相关数据 保存数据库用于恢复定时器
 */
public class TimerData {
    /**
     * timer的唯一id
     */
    String id;
    /**
     * timer最后执行的时间戳
     */
    long lastTick;

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
