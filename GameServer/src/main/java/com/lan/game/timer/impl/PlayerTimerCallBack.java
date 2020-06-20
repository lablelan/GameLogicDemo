package com.lan.game.timer.impl;

import com.lan.game.player.Player;
import com.lan.game.timer.ITimerTarget;

/**
 * 和玩家关联的定时器配置实现
 */
public class PlayerTimerCallBack extends AbstractTimerCallBack {

    public PlayerTimerCallBack(ITimerTarget target, TimerData data, long nowTs) {
        super(target, data, nowTs);
    }

    public void callback(long nowTs) {
        Player p = (Player)this.getTarget();
        p.sayHi();
    }
}
