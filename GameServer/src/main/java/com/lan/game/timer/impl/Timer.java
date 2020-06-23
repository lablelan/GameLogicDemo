package com.lan.game.timer.impl;
import com.lan.game.timer.ITimerTarget;
import com.lan.game.util.GSConst;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author lanbei
 * 定时器类
 */
public class Timer {
    /**
     * 存放当前的所有需要调用的timer
     */
    Map<String, TimerArgs> timerNameMapToCfg;

    ITimerTarget target;

    public Timer() {
        timerNameMapToCfg = new HashMap<String, TimerArgs>();
    }

    public Map<String, TimerArgs> getTimerNameMapToCfg() {
        return timerNameMapToCfg;
    }

    public ITimerTarget getTarget() {
        return target;
    }

    public void setTarget(ITimerTarget target) {
        this.target = target;
    }

    /**
     * 超时调用
     * @param args 定时配置
     */
    public void setTimeOut(TimerArgs args) {
        this.startInterval(args);
    }

    /**
     * 间隔循环调用
     */
    public void startInterval(TimerArgs args) {
        TimerArgs oldArgs = timerNameMapToCfg.get(args.getId());
        if (oldArgs != null) {
            // 覆盖参数
            oldArgs.copy(args);
        } else {
            timerNameMapToCfg.put(args.getId(), args);
        }
    }

    /**
     * 清除调用配置
     * @param id 定时器配置的唯一id
     */
    void clearInterval(String id) {
        TimerArgs args = timerNameMapToCfg.get(id);
        if (args == null) {
            return;
        }
        args.setNeedRemove(true);
        timerNameMapToCfg.remove(id);
    }

    /**
     * 定时器触发 注意只能删除
     * @param nowTs 当前时间戳
     */
    public void tick(long nowTs) {
        for (Iterator<Map.Entry<String, TimerArgs>> it = timerNameMapToCfg.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, TimerArgs> item = it.next();
            TimerArgs timerCfg = item.getValue();
            this.tryRefreshTimer(this.getTarget(), nowTs, timerCfg);
            if (timerCfg.isNeedRemove()) {
                it.remove();
            }
        }
    }

    /**
     * 根据nextTick和类型尝试刷新timer触发callback
     * @param nowTs 当前时间戳
     */
    public void tryRefreshTimer(ITimerTarget target, long nowTs, TimerArgs args) {
        boolean isFast = false;
        // todo fast为false时调用一次后跳出循环
        while (nowTs >= args.getNextTick()) {
            // 初始化时会尝试刷新一次nextTick 但不调用callback
            if (args.getNextTick() != 0) {
                if (!isFast && !args.isFast()) {
                    isFast = true;
                    args.getCallback().callback(target, nowTs, args);
                }
                args.setLastTick(args.getNextTick());
            }
            this.calcNextTick(nowTs, args);
        }
    }

    /**
     * 根据类型和当前时间戳计算下一个tick
     * @param nowTs 当前时间戳
     */
    private void calcNextTick(long nowTs, TimerArgs args) {
        if (args.getNextTick() > nowTs) {
            return;
        }
        long nextTick = args.getNextTick();
        long lastTick = args.getLastTick();
        switch (args.getType()) {
            case TIMEOUT:
                if (args.getNextTick() != 0) {
                    args.setNeedRemove(true);
                }
                lastTick = lastTick == 0 ? nowTs : lastTick;
                nextTick = lastTick + args.getArg();
                break;
            case SEC_INTERVAL:
                nextTick = args.getLastTick() + args.getArg() * GSConst.SEC;
                break;
            case MIN_INTERVAL:
                nextTick = args.getLastTick() + args.getArg() * GSConst.MIN;
                break;
            case HOUR_INTERVAL:
                nextTick = args.getLastTick() + args.getArg() * GSConst.HOUR;
                break;
            case DAY_INTERVAL:
                nextTick = args.getLastTick() + args.getArg() * GSConst.DAY;
                break;
            default:
                break;
        }
        args.setNextTick(nextTick);
    }
}
