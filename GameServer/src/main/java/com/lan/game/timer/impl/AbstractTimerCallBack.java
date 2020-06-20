package com.lan.game.timer.impl;

import com.lan.game.timer.ITimerCfg;
import com.lan.game.timer.ITimerTarget;
import com.lan.game.util.GSConst;

/**
 * 定时器抽象类
 */
public abstract class AbstractTimerCallBack implements ITimerCfg {
    private long nextTick = 0;
    private ITimerTarget target = null; // 和timer关联的对象，供callback使用
    private TimerData timerData = null;

    public ITimerTarget getTarget() {
        return target;
    }

    public void setTarget(ITimerTarget target) {
        this.target = target;
    }

    public TimerData getTimerData() {
        return timerData;
    }

    public void setTimerData(TimerData timerData) {
        this.timerData = timerData;
    }

    public boolean isNeedRemove() {
        return needRemove;
    }

    private boolean needRemove = false; // 是否需要移除

    /**
     * 根据数据构建timer
     * @param data 定时器相关的数据
     */
    public AbstractTimerCallBack(ITimerTarget target, TimerData data, long nowTs) {
        this.timerData = data;
        this.target = target;
        tryRefreshTimer(nowTs);
    }

    /**
     * 到达某个时间戳后回调
     * @param nowTs
     */
    public abstract void callback(long nowTs);

    /**
     * 根据nextTick和类型尝试刷新timer触发callback
     * @param nowTs 当前时间戳
     */
    public void tryRefreshTimer(long nowTs) {
        boolean isFast = false;
        while (nowTs >= this.nextTick) {
            // 初始化时会尝试刷新一次nextTick 但不调用callback
            if (this.nextTick != 0) {
                if (!isFast && !this.getTimerData().isFast()) {
                    isFast = true;
                    this.callback(nowTs);
                }
                this.timerData.setLastTick(this.nextTick);
            }
            this.calcNextTick(nowTs);
        }
    }

    /**
     * 根据类型和当前时间戳计算下一个tick
     * @param nowTs 当前时间戳
     */
    private void calcNextTick(long nowTs) {
        if (this.nextTick > nowTs) {
            return;
        }
        switch (this.getTimerData().getType()) {
            case TIMEOUT:
                if (this.nextTick != 0) {
                    this.needRemove = true;
                }
                this.nextTick = this.getTimerData().getLastTick() + this.getTimerData().getArg();
                return;
            case SEC_INTERVAL:
                this.nextTick = this.getTimerData().getLastTick() + this.getTimerData().getArg() * GSConst.SEC;
            case MIN_INTERVAL:
                this.nextTick = this.getTimerData().getLastTick() + this.getTimerData().getArg() * GSConst.MIN;
            case HOUR_INTERVAL:
                this.nextTick = this.getTimerData().getLastTick() + this.getTimerData().getArg() * GSConst.HOUR;
                return;
            case DAY_INTERVAL:
                this.nextTick = this.getTimerData().getLastTick() + this.getTimerData().getArg() * GSConst.DAY;
                return;
            default:
                return;
        }
    }
}
