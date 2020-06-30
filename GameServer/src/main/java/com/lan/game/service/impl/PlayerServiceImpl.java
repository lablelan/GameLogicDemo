package com.lan.game.service.impl;

import com.lan.game.dao.impl.TimerDaoImpl;
import com.lan.game.domain.Timer;
import com.lan.game.player.Player;
import com.lan.game.service.IPlayerService;
import com.lan.game.timer.impl.TimerArgs;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.Map;

public class PlayerServiceImpl implements IPlayerService {

    public void update(Player player) {
        // todo use single
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        TimerDaoImpl timerDao = ac.getBean("timerDao", TimerDaoImpl.class);
        Map<String, TimerArgs> timerMap = player.getTimer().getTimerNameMapToCfg();
        // todo 批处理
        for (Iterator<Map.Entry<String, TimerArgs>> it = timerMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, TimerArgs> item = it.next();
            TimerArgs timerArgs = item.getValue();
            Timer timer = new Timer();
            timer.setPlayerId(player.getId());
            timer.setLastTick(timerArgs.getLastTick());
            timer.setName(timerArgs.getId());
            timerDao.updateTimer(timer);
        }
    }
}
