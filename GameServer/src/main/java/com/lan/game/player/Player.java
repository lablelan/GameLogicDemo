package com.lan.game.player;
import com.lan.game.activity.IActivityTarget;
import com.lan.game.activity.impl.ActivityManager;
import com.lan.game.timer.ITimerTarget;
import com.lan.game.timer.impl.Timer;


/**
 * @author lanbei
 * 玩家类
 */
public class Player implements ITimerTarget, IActivityTarget {
    String id;
    String name;
    private Timer timer;

    private ActivityManager activityManager;

    public Player() {
        name = "";
    }

    public ActivityManager getActivityManager() {
        return activityManager;
    }

    public void setActivityManager(ActivityManager activityManager) {
        this.activityManager = activityManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void tick(long nowTs) {
        if (timer != null) {
            timer.tick(nowTs);
        }
    }

    public void sayHi() {
        System.out.println("hi, world!");
    }
}
