package com.lan.game.activity.impl;

/**
 * @author lanbei
 * 枚举活动状态
 */

public enum ActivityStatType {
    /**
     * 关闭
     */
    CLOSE(0),
    /**
     * 进行
     */
    GOING(1),
    /**
     * 展示
     */
    SHOW(2);

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    ActivityStatType(int value) {
        this.value = value;
    }

}
