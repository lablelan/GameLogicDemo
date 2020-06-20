package com.lan.game.timer.impl;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Timer {
    // 存放当前的所有需要调用的timer
    HashMap<String, AbstractTimerCallBack> timerNameMapToCfg;

    Timer() {
        timerNameMapToCfg = new HashMap<String, AbstractTimerCallBack>();
    }

    public HashMap<String, AbstractTimerCallBack> getTimerNameMapToCfg() {
        return timerNameMapToCfg;
    }

    /**
     * 超时调用
     * @param cfg 定时配置
     */
    public void setTimeOut(AbstractTimerCallBack cfg) {
        this.startInterval(cfg);
    }

    /**
     * 间隔循环调用
     * @param cfg 定时器配置
     */
    public void startInterval(AbstractTimerCallBack cfg) {
        timerNameMapToCfg.put(cfg.getTimerData().getId(), cfg);
    }

    /**
     * 清除调用配置
     * @param id 定时器配置的唯一id
     */
    void clearInterval(String id) {
        timerNameMapToCfg.remove(id);
    }

    /**
     * 定时器触发
     * @param nowTs 当前时间戳
     */
    public void tick(long nowTs) {
        for (Iterator<Map.Entry<String, AbstractTimerCallBack>> it = timerNameMapToCfg.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, AbstractTimerCallBack> item = it.next();
            AbstractTimerCallBack timerCfg = item.getValue();
            timerCfg.tryRefreshTimer(nowTs);
            if (timerCfg.isNeedRemove()) {
                it.remove();
                timerCfg.setTarget(null);
            }
        }
    }
}
