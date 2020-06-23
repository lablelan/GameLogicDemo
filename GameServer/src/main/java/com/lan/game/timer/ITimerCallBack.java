package com.lan.game.timer;

import com.lan.game.timer.impl.Timer;
import com.lan.game.timer.impl.TimerArgs;

/**
 * @author lanbei
 * timer回调实现接口
 */
public interface ITimerCallBack {
    /**
     * 到达某个时间点后回调
     * @param curTick 当前时间戳
     */
     void callback(ITimerTarget target, long curTick, TimerArgs args);
}
