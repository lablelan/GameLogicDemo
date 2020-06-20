package com.lan.game.player;
import com.lan.game.timer.ITimerTarget;
import com.lan.game.timer.impl.Timer;


public class Player implements ITimerTarget {
    String name = "";
    private Timer timer = null;

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

    public void tick(long nowTs) {
        if (timer != null) {
            timer.tick(nowTs);
        }
    }

    public void sayHi() {
        this.name = "xiaoming";
        System.out.println("hi, world!");
    }
}