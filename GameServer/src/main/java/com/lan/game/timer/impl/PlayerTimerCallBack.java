package com.lan.game.timer.impl;

import com.lan.game.player.Player;
import com.lan.game.timer.ITimerCallBack;
import com.lan.game.timer.ITimerTarget;


/**
 * @author lanbei
 * 和玩家关联的定时器配置实现
 */
public class PlayerTimerCallBack implements ITimerCallBack {
    /**
     * 根据数据构建timer
     */
    public PlayerTimerCallBack() {
    }

    public void callback(ITimerTarget target, long nowTs, TimerArgs args) {
        Player p = (Player)target;
        p.setName("xiaoming");
        p.sayHi();
    }
}
