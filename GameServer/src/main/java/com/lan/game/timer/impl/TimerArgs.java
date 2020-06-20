package com.lan.game.timer.impl;

public class TimerArgs {
    public TimerArgs(boolean isFast, TimerType type, long arg, long nowTs) {
        this.isFast = isFast;
        this.type = type;
        this.arg = arg;
        this.nowTs = nowTs;
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

    public long getNowTs() {
        return nowTs;
    }

    public void setNowTs(long nowTs) {
        this.nowTs = nowTs;
    }

    boolean isFast = false;
    TimerType type = TimerType.INVALID;
    long arg = 0;
    long nowTs = 0;
}
