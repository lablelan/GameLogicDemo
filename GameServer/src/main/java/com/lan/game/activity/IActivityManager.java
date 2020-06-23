package com.lan.game.activity;

public interface IActivityManager {
    /**
     * 根据配置初始化所有活动
     */
    void init(long nowTs);

    /**
     * 操作逻辑
     * @param id 活动id
     * @param args 自定义参数
     * @return
     */
    boolean claim(Integer id, Object args);
}
