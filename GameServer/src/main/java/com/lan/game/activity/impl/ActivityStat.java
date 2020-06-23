package com.lan.game.activity.impl;

/**
 * @author lanbei
 * 活动状态
 */
public class ActivityStat {

    private int id;
    private long beginTs;
    private long showTs;
    private long endTs;
    private ActivityStatType stat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ActivityStatType getStat() {
        return stat;
    }

    public void setStat(ActivityStatType stat) {
        this.stat = stat;
    }
}
