package com.lan.game.timer.impl;

import com.lan.game.player.Player;
import com.lan.game.timer.ITimerTarget;


/**
 * @author lanbei
 * 和玩家关联的定时器配置实现
 */
public class PlayerTimerCallBack extends AbstractTimerCallBack {
    /**
     * 根据数据构建timer
     *
     * @param target 定时器回调相关的对象
     * @param data   定时器相关的数据
     * @param args   定时器自定义参数
     */
    public PlayerTimerCallBack(ITimerTarget target, TimerData data, TimerArgs args) {
        super(target, data, args);
    }

    @Override
    public void callback(long nowTs) {
        Player p = (Player)this.getTarget();
        p.setName("xiaoming");
        p.sayHi();
    }
}
