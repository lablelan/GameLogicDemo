package com.lan.game.timer.impl;

import com.lan.game.timer.ITimerCallBack;

/**
 * @author lanbei
 * 定时器自定义参数
 */
public class TimerArgs {

    /**
     * timer的唯一id
     */
    String id;
    /**
     * timer最后执行的时间戳
     */
    long lastTick;
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

    /**
     * 是否覆盖
     */
    boolean override;

    /**
     * 记录上下文
     */
    Object context;

    /**
     * 下次触发时间戳
     */
    private long nextTick;

    /**
     * 回调对象
     */
    private ITimerCallBack callback;

    /**
     * 是否要移除标记
     */
    private boolean needRemove;

    public TimerArgs() {};

    public TimerArgs(String id, TimerType type, long nowTs, long arg, ITimerCallBack callback) {
        this.id = id;
        this.type = type;
        this.arg = arg;
        this.nowTs = nowTs;
        this.lastTick = nowTs;
        this.callback = callback;
    }

    public void copy(TimerArgs tarArgs) {
        this.id = tarArgs.id;
        this.type = tarArgs.type;
        this.arg = tarArgs.arg;
        this.nowTs = tarArgs.nowTs;
        this.isFast = tarArgs.isFast;
        this.needRemove = tarArgs.needRemove;
        this.callback = tarArgs.callback;
        this.context = tarArgs.context;
        this.nextTick = tarArgs.nextTick;
    }

    public long getNextTick() {
        return nextTick;
    }

    public void setNextTick(long nextTick) {
        this.nextTick = nextTick;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }
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

    public long getNowTs() {
        return nowTs;
    }

    public void setNowTs(long nowTs) { this.nowTs = nowTs; }

    public boolean isNeedRemove() {
        return needRemove;
    }

    public void setNeedRemove(boolean needRemove) {
        this.needRemove = needRemove;
    }

    public ITimerCallBack getCallback() {
        return callback;
    }

    public void setCallback(ITimerCallBack callback) {
        this.callback = callback;
    }
}
