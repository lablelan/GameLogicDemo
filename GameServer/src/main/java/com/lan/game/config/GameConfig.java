package com.lan.game.config;

import com.lan.game.config.activity.ActivityConfig;
import com.lan.game.config.activity.ActivityConfigItem;

import java.util.HashMap;

public class GameConfig {

    private static GameConfig uniqueInstance = new GameConfig();

    private ActivityConfig activityConfig;

    public static GameConfig getUniqueInstance() {
        return uniqueInstance;
    }

    public ActivityConfig getActivityConfig() {
        return activityConfig;
    }

    public ActivityConfigItem getActivityConfigById(Integer id) {
        return activityConfig.getActivityConfigById(id);
    }

    private GameConfig () {
        activityConfig = new ActivityConfig();
        activityConfig.setActivityConfigItemMap(new HashMap<Integer, ActivityConfigItem>());
    }
}
