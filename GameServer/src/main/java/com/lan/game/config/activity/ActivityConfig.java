package com.lan.game.config.activity;

import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author lanbei
 * 活动时间配置
 */
public class ActivityConfig {
    Map<Integer, ActivityConfigItem> activityConfigItemMap;

    public java.util.Collection<ActivityConfigItem> getActivityConfigItemMapValues() {
        return activityConfigItemMap.values();
    }

    public Map<Integer, ActivityConfigItem> getActivityConfigItemMap() {
        return activityConfigItemMap;
    }

    public void setActivityConfigItemMap(Map<Integer, ActivityConfigItem> activityConfigItemMap) {
        this.activityConfigItemMap = activityConfigItemMap;
    }

    /**
     * 读取Json翻译成需要的格式
     * @param jsonObject
     */
    public void loadFromJsonObject(JSONObject jsonObject) {
        for (Object value : jsonObject.values()) {
            JSONObject itemJsonObject = (JSONObject)value;
            ActivityConfigItem item = new ActivityConfigItem();
            item.setId(itemJsonObject.getInteger("id"));
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                Date d = simpleDateFormat.parse(itemJsonObject.getString("begin"));
                item.setBeginTs(d.getTime());
                item.setType(itemJsonObject.getString("type"));
                d = simpleDateFormat.parse(itemJsonObject.getString("show"));
                item.setShowTs(d.getTime());
                d = simpleDateFormat.parse(itemJsonObject.getString("end"));
                item.setEndTs(d.getTime());
                activityConfigItemMap.put(itemJsonObject.getInteger("id"), item);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过活动id获取配置
     * @param id
     * @return
     */
    public ActivityConfigItem getActivityConfigById(Integer id) {
        return this.activityConfigItemMap.get(id);
    }
}
