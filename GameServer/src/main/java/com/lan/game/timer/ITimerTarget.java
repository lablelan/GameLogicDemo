package com.lan.game.timer;

/**
 * @author lanbei
 *
 */
public interface ITimerTarget {
    /**
     * timer的每个时间单位需要调用的接口，实际只要调用一次就会将过去流逝的时间从头跑一次
     * @param nowTs
     */
    void tick(long nowTs);
}
