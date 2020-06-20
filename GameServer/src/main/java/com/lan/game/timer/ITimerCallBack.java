package com.lan.game.timer;

public interface ITimerCallBack {
    ITimerTarget target = null; // 和timer关联的对象
    void callback(long curTick);// 到达某个时间点后回调
    void tryRefreshTimer(long curTick);// 尝试调用callback并刷新timer
}
