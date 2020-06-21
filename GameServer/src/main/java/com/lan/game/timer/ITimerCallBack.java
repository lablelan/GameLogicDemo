package com.lan.game.timer;

/**
 * @author lanbei
 * timer回调实现接口
 */
public interface ITimerCallBack {
    /**
     * 和timer关联的对象
     */
    ITimerTarget target = null;

    /**
     * 到达某个时间点后回调
     * @param curTick 当前时间戳
     */
    void callback(long curTick);

    /**
     * 尝试调用callback并刷新timer
     * @param curTick 当前时间戳
     */
    void tryRefreshTimer(long curTick);
}
