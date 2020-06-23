package com.lan.game.timer.impl;

/**
 * @author lanbei
 * 定时器类型枚举
 */
public enum TimerType {
    /**
     * 无效的类型
     */
    INVALID(0),
    /**
     * 延迟n秒调用
     */
    TIMEOUT(1),
    /**
     * 每n秒调用
     */
    SEC_INTERVAL(2),
    /**
     * 每n分钟调用
     */
    MIN_INTERVAL(3),
    /**
     * 每n小时调用
     */
    HOUR_INTERVAL(4),
    /**
     * 每n天调用
     */
    DAY_INTERVAL(5);

    private int value;
    TimerType(int value) {
        this.value = value;
    }
    public TimerType valueOf(int value) {
        switch (value) {
            case 1:
                return TimerType.TIMEOUT;
            case 2:
                return TimerType.SEC_INTERVAL;
            case 3:
                return TimerType.MIN_INTERVAL;
            case 4:
                return TimerType.HOUR_INTERVAL;
            case 5:
                return TimerType.DAY_INTERVAL;
            default:
                return TimerType.INVALID;
        }
    }
}
