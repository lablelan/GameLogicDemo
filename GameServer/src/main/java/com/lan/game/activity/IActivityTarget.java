package com.lan.game.activity;

import com.lan.game.activity.impl.ActivityManager;
import com.lan.game.timer.ITimerTarget;
import com.lan.game.timer.impl.Timer;

/**
 * @author lanbei
 * 活动宿主对象接口
 */
public interface IActivityTarget extends ITimerTarget {
    /**
     * 获取定时器对象
     * @return 定时器对象
     */
    Timer getTimer();

    /**
     * 获取活动管理类
     * @return
     */
    ActivityManager getActivityManager();
}
