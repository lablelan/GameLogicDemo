package com.lan.game.dao.impl;

import com.lan.game.dao.ITimerDao;
import com.lan.game.domain.Timer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class TimerDaoImpl extends JdbcDaoSupport implements ITimerDao {
    public List<Timer> findTimerByPlayerId(String playerId) {
        return this.getJdbcTemplate().query("select * from timer where playerId = ?",
                new BeanPropertyRowMapper<Timer>(Timer.class), playerId);
    }

    public void updateTimer(Timer timer) {
        this.getJdbcTemplate().update("insert into timer(playerId, name, lastTick) values(?,?,?)",
                timer.getPlayerId(),timer.getName(),timer.getLastTick());
//        this.getJdbcTemplate().update("update timer set playerId=?,name=?,lastTick=? where playerId=? and name=?",
//                timer.getPlayerId(), timer.getName(), timer.getLastTick(), timer.getPlayerId(), timer.getName());
    }
}
