package com.lan.game.config.activity;


/**
 * @author lanbei
 * 活动配置项
 *     "id": 1,  活动id
 *     "type": "CLAIM", 活动类型
 *     "begin": "2020-01-01 08:00:00", 活动开启时间
 *     "show": "2020-01-01 09:59:59",  活动展示时间
 *     "end": "2020-01-01 09:59:59",   活动结束时间
 *     "owner": "player"               宿主类型
 */
public class ActivityConfigItem {
    private int id;
    private String type;
    private long beginTs;
    private long showTs;
    private long endTs;
    private String owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getBeginTs() {
        return beginTs;
    }

    public void setBeginTs(long beginTs) {
        this.beginTs = beginTs;
    }

    public long getShowTs() {
        return showTs;
    }

    public void setShowTs(long showTs) {
        this.showTs = showTs;
    }

    public long getEndTs() {
        return endTs;
    }

    public void setEndTs(long endTs) {
        this.endTs = endTs;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
