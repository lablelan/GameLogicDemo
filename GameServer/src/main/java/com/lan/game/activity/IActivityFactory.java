package com.lan.game.activity;

import com.lan.game.activity.impl.AbstractActivity;
import com.lan.game.activity.impl.ActivityStat;
import com.lan.game.config.activity.ActivityConfigItem;

/**
 * @author lanbei
 * 活动工厂接口
 */
public interface IActivityFactory {
    /**
     * 创建活动对象
     * @param id 活动id
     * @param type 活动类型
     * @param nowTs 当前时间戳
     * @return 返回活动对象
     */
    AbstractActivity createActivity(Integer id, String type, IActivityTarget target, ActivityConfigItem config, long nowTs);
}
