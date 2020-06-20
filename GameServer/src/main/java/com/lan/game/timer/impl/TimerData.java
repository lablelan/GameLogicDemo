package com.lan.game.timer.impl;

/**
 * 定时器相关数据 保存数据库用于恢复定时器
 */
public class TimerData {
    String id = "";
    long lastTick = 0;
    boolean isFast = false;
    TimerType type = TimerType.INVALID;
    long arg = 0;

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

    public boolean isFast() {
        return isFast;
    }

    public void setFast(boolean fast) {
        isFast = fast;
    }

    public TimerType getType() {
        return type;
    }

    public void setType(TimerType type) {
        this.type = type;
    }

    public long getArg() {
        return arg;
    }

    public void setArg(long arg) {
        this.arg = arg;
    }

    public TimerData(String id, long lastTick, boolean isFast, TimerType type, long arg) {
        this.id = id;
        this.lastTick = lastTick;
        this.isFast = isFast;
        this.type = type;
        this.arg = arg;
    }
}
