package com.lan.game.timer.impl;

import com.lan.game.activity.IActivityTarget;
import com.lan.game.activity.impl.AbstractActivity;
import com.lan.game.activity.impl.ActivityManager;
import com.lan.game.timer.ITimerCallBack;
import com.lan.game.timer.ITimerTarget;

/**
 * @author lanbei
 * 活动时间回调
 */
public class ActivityTimerCallBack implements ITimerCallBack {
    /**
     * 根据数据构建callback
     */
    public ActivityTimerCallBack() { }

    /**
     * 到达某个时间戳后回调
     *
     * @param nowTs
     */
    public void callback(ITimerTarget target, long nowTs, TimerArgs args) {
        IActivityTarget activityTarget = (IActivityTarget) target;
        ActivityManager manager = activityTarget.getActivityManager();
        Integer activityId = (Integer) args.getContext();
        AbstractActivity activity = manager.getActivityById(activityId);
        activity.tyrRefresh(activityTarget, nowTs);
    }
}
