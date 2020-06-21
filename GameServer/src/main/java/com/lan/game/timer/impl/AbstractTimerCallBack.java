package com.lan.game.timer.impl;

import com.lan.game.timer.ITimerCallBack;
import com.lan.game.timer.ITimerTarget;
import com.lan.game.util.GSConst;

/**
 * @author lanbei
 * 定时器抽象类
 */
public abstract class AbstractTimerCallBack implements ITimerCallBack {
    /**
     * 动态计算的下次执行时间戳
     */
    private long nextTick;
    /**
     * timer关联的对象，供callback使用
     */
    private ITimerTarget target;
    /**
     * timer相关用来恢复定时器的数据
     */
    private TimerData timerData;
    /**
     * timer相关的自定义参数
     */
    TimerArgs args;

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

    private boolean needRemove = false; // 执行完是否需要移除

    /**
     * 根据数据构建timer
     * @param data 定时器相关的数据
     */
    public AbstractTimerCallBack(ITimerTarget target, TimerData data, TimerArgs args) {
        this.timerData = data;
        this.target = target;
        this.args = args;
        tryRefreshTimer(this.args.getNowTs());
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
                if (!isFast && !this.args.isFast()) {
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
        switch (this.args.getType()) {
            case TIMEOUT:
                if (this.nextTick != 0) {
                    this.needRemove = true;
                }
                this.nextTick = this.getTimerData().getLastTick() + this.args.getArg();
                return;
            case SEC_INTERVAL:
                this.nextTick = this.getTimerData().getLastTick() + this.args.getArg() * GSConst.SEC;
            case MIN_INTERVAL:
                this.nextTick = this.getTimerData().getLastTick() + this.args.getArg() * GSConst.MIN;
            case HOUR_INTERVAL:
                this.nextTick = this.getTimerData().getLastTick() + this.args.getArg() * GSConst.HOUR;
                return;
            case DAY_INTERVAL:
                this.nextTick = this.getTimerData().getLastTick() + this.args.getArg() * GSConst.DAY;
                return;
            default:
                return;
        }
    }
}
