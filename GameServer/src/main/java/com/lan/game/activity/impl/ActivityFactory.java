package com.lan.game.activity.impl;

import com.lan.game.activity.IActivityFactory;
import com.lan.game.activity.IActivityTarget;
import com.lan.game.config.activity.ActivityConfigItem;

import java.util.Properties;

/**
 * @author lanbei
 * 活动工厂
 * 根据活动配置创建活动对象
 */
public class ActivityFactory implements IActivityFactory {
    private static ActivityFactory uniqueInstance = new ActivityFactory();
    private Properties properties;

    public static ActivityFactory getUniqueInstance() {
        return uniqueInstance;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * 通过配置读取类名 使用反射创建活动对象
     *
     * @param id 活动id
     * @param type 活动类型
     * @param target 宿主对象
     * @return 活动对象
     */
    public AbstractActivity createActivity(Integer id, String type, IActivityTarget target, ActivityConfigItem config, long nowTs) {
        String className = properties.getProperty(type);
        Class<AbstractActivity> clazz = null;
        AbstractActivity activity = null;
        try {
            clazz = (Class<AbstractActivity>)Class.forName(className);
            activity = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if (activity == null) {
            return null;
        }
        activity.onInit(target);
        ActivityStat stat = activity.getStat();
        if (stat == null) {
            stat = new ActivityStat();
        }
        if (stat.getStat() == null) {
            stat.setStat(ActivityStatType.CLOSE);
        }
        stat.setId(config.getId());
        stat.setBeginTs(config.getBeginTs());
        stat.setShowTs(config.getShowTs());
        stat.setEndTs(config.getEndTs());
        activity.setStat(stat);
        activity.tyrRefresh(target, nowTs);
        return activity;
    }
}
