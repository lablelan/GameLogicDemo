package com.lan.game.activity.impl;

import com.alibaba.fastjson.JSONObject;
import com.lan.game.activity.IActivityTarget;
import com.lan.game.config.GameConfig;
import com.lan.game.config.activity.ActivityConfigItem;
import com.lan.game.timer.impl.*;
import com.lan.game.util.GSConst;

/**
 * @author lanbei
 * 活动抽象类
 */
public abstract class AbstractActivity {
    private ActivityStat stat;
    private Object record;

    public ActivityStat getStat() {
        return stat;
    }

    public void setStat(ActivityStat stat) {
        this.stat = stat;
    }

    public void setRecord(Object record) {
        this.record = record;
    }

    public boolean isGoing() { return this.stat.getStat() == ActivityStatType.GOING; }

    public JSONObject loadFromDb() {
        // todo reload form db
        return null;
    }

    /**
     * 获取活动状态
     * @return
     */
    public ActivityStatType getState() {
        return stat.getStat();
    }

    public Object getRecord() {
        return record;
    }

    /**
     * 活动开始
     * @param target
     */
    public abstract void onStart(IActivityTarget target);

    /**
     * 活动初始化
     * @param target
     */
    public abstract void onInit(IActivityTarget target);

    /**
     * 活动展示
     * @param target
     */
    public abstract void onShow(IActivityTarget target);

    /**
     * 活动结束
     * @param target
     */
    public abstract void onClose(IActivityTarget target);

    /**
     * 操作函数
     * @param target
     * @param args 自定义参数
     */
    public abstract boolean onClaim(IActivityTarget target, Object args);

    /**
     * 刷新活动时间 修改数据
     * @param target
     */
    public void tyrRefresh(IActivityTarget target, long nowTs) {
        ActivityStatType oldStat = this.stat.getStat();
        long nextTs = updateStat(nowTs);
        ActivityStatType currentStat = this.stat.getStat();
        if (nextTs == 0) {
            return;
        }
        // 下个时间戳小于当前说明活动已经结束不需要再更新了
        if (nextTs <= nowTs) {
            this.onClose(target);
            return;
        }
        // 穷举所有状态切换
        if (oldStat == ActivityStatType.CLOSE && currentStat == ActivityStatType.GOING) {
            this.onStart(target);
        } else if (oldStat == ActivityStatType.CLOSE && currentStat == ActivityStatType.SHOW) {
            this.onStart(target);
            this.onShow(target);
        } else if (oldStat == ActivityStatType.GOING && currentStat == ActivityStatType.SHOW) {
            this.onShow(target);
        } else if (oldStat == ActivityStatType.GOING && currentStat == ActivityStatType.CLOSE) {
            this.onClose(target);
        } else if (oldStat == ActivityStatType.SHOW && currentStat == ActivityStatType.CLOSE) {
            this.onClose(target);
        } else if (oldStat == ActivityStatType.SHOW && currentStat == ActivityStatType.GOING) {
            this.onStart(target);
        }
        // 刷新timer
        Timer timer = target.getTimer();
        String name = "actTimer_" + this.stat.getId();
        TimerArgs timerArgs = new TimerArgs(name, TimerType.TIMEOUT, nowTs,
                nextTs - nowTs, new ActivityTimerCallBack());
        timerArgs.setOverride(true);
        timerArgs.setContext(this.stat.getId());
        timer.setTimeOut(timerArgs);
    }

    /**
     * 计算下次活动状态
     * @param nowTs 当前时间戳
     * @return 下次更新时间戳
     */
    private long updateStat(long nowTs) {
        ActivityStatType stat = this.stat.getStat();
        long beginTs = this.stat.getBeginTs();
        long showTs = this.stat.getShowTs();
        long endTs = this.stat.getEndTs();
        if (beginTs == 0) {
            // 读配置初始化时间
            ActivityConfigItem configItem = GameConfig.getUniqueInstance().getActivityConfigById(this.stat.getId());
            this.stat.setBeginTs(configItem.getBeginTs());
            this.stat.setShowTs(configItem.getShowTs());
            this.stat.setEndTs(configItem.getEndTs());
        }
        long nextTs = 0;
        if (nowTs >= beginTs && nowTs < showTs) {
            stat = ActivityStatType.GOING;
            nextTs = showTs;
        } else if (nowTs >= showTs && nowTs < endTs) {
            stat = ActivityStatType.SHOW;
            nextTs = endTs;
        } else if (nowTs < beginTs) {
            stat = ActivityStatType.CLOSE;
            nextTs = beginTs;
        } else if (nowTs > endTs) {
            stat = ActivityStatType.CLOSE;
            nextTs = endTs;
        }
        this.stat.setStat(stat);
        return nextTs;
    }
}
