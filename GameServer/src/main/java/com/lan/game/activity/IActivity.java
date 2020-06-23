package com.lan.game.activity;

import com.lan.game.config.activity.ActivityConfigItem;

/**
 * @author lanbei
 * 活动接口
 */
public interface IActivity {

    /**
     * 活动开始
     * @param target
     */
    void onStart(IActivityTarget target);

    /**
     * 活动初始化
     * @param target
     */
    void onInit(IActivityTarget target);

    /**
     * 活动展示
     * @param target
     */
    void onShow(IActivityTarget target);

    /**
     * 活动关闭
     * @param target
     */
    void onClose(IActivityTarget target);

    /**
     * 操作函数
     * @param target
     * @param args 自定义参数
     */
    void onClaim(IActivityTarget target, Object args);
}
