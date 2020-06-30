package com.lan.game.dao;

import com.lan.game.domain.Timer;

import java.util.List;

public interface ITimerDao {
    public List<Timer> findTimerByPlayerId(String playerId);
    public void updateTimer(Timer timer);
}
