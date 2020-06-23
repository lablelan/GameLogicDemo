package com.lan.game.activity.impl;

import com.lan.game.activity.IActivityManager;
import com.lan.game.activity.IActivityTarget;
import com.lan.game.config.GameConfig;
import com.lan.game.config.activity.ActivityConfig;
import com.lan.game.config.activity.ActivityConfigItem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lanbei
 * 活动管理类
 */
public class ActivityManager implements IActivityManager {

    IActivityTarget target;
    Map<Integer, AbstractActivity> activityMap;

    public IActivityTarget getTarget() {
        return target;
    }

    public void setTarget(IActivityTarget target) {
        this.target = target;
    }

    public Map<Integer, AbstractActivity> getActivityMap() {
        return activityMap;
    }

    public boolean isActivityGoing(Integer id) {
        return activityMap.get(id).isGoing();
    }

    public void setActivityMap(Map<Integer, AbstractActivity> activityMap) {
        this.activityMap = activityMap;
    }

    public AbstractActivity getActivityById(Integer id) {
        return activityMap.get(id);
    }

    /**
     * 根据配置初始化所有活动
     */
    public void init(long nowTs) {
        activityMap = new HashMap<Integer, AbstractActivity>();
        ActivityConfig activityConfig = GameConfig.getUniqueInstance().getActivityConfig();
        for (ActivityConfigItem configItem : activityConfig.getActivityConfigItemMapValues()) {
            AbstractActivity activity = ActivityFactory.getUniqueInstance().createActivity(configItem.getId(),
                    configItem.getType(), this.target, configItem, nowTs);
            activityMap.put(configItem.getId(), activity);
        }
    }

    /**
     * 操作逻辑
     *
     * @param id   活动id
     * @param args 自定义参数
     * @return
     */
    public boolean claim(Integer id, Object args) {
        AbstractActivity activity = activityMap.get(id);
        return activity.onClaim(this.target, args);
    }

    public void refreshActivity(long nowTs) {
        ActivityConfig activityConfig = GameConfig.getUniqueInstance().getActivityConfig();
        for (ActivityConfigItem configItem : activityConfig.getActivityConfigItemMapValues()) {
            AbstractActivity activity = activityMap.get(configItem.getId());
            if (activity == null) {
                activity = ActivityFactory.getUniqueInstance().createActivity(configItem.getId(),
                        configItem.getType(), this.target, configItem, nowTs);
                activityMap.put(configItem.getId(), activity);
                activity.onInit(this.target);
            } else {
                activity.tyrRefresh(this.target, nowTs);
            }
        }
    }
}
