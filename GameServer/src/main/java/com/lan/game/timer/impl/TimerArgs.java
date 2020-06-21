package com.lan.game.timer.impl;

/**
 * @author lanbei
 * 定时器自定义参数
 */
public class TimerArgs {
    /**
     * 是否只调用一次
     */
    boolean isFast;
    /**
     * timer类型
     */
    TimerType type;
    /**
     * timer类型对应的辅助参数 如setTimeOut为n毫秒 startInterval为n个单位时间
     */
    long arg;
    /**
     * 注册timer时的当前时间
     */
    long nowTs;

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

    public void setNowTs(long nowTs) { this.nowTs = nowTs; }
}
