package com.lan.game.timer.impl;

import com.lan.game.player.Player;
import com.lan.game.timer.ITimerTarget;

/**
 * 和玩家关联的定时器配置实现
 */
public class PlayerTimerCallBack extends AbstractTimerCallBack {


    /**
     * 根据数据构建timer
     *
     * @param target
     * @param data   定时器相关的数据
     * @param args
     */
    public PlayerTimerCallBack(ITimerTarget target, TimerData data, TimerArgs args) {
        super(target, data, args);
    }

    public void callback(long nowTs) {
        Player p = (Player)this.getTarget();
        p.sayHi();
    }
}
