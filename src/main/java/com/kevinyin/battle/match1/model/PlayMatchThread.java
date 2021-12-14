package com.kevinyin.battle.match1.model;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/11/16 19:27
 */
@Slf4j
public class PlayMatchThread extends Thread {

    private Player player;
    private GameMatch1 gameMatch;

    public PlayMatchThread(String threadName, Player player, GameMatch1 gameMatch) {
        super(threadName);
        this.player = player;
        this.gameMatch = gameMatch;
    }

    @SneakyThrows
    @Override
    public void run() {
        Room r = gameMatch.match(player);
        while (!r.matchSuccess()) {
            log.info("{}正在进行匹配中....", Thread.currentThread().getName());
            Thread.sleep(1000);
        }
        log.info("【{}匹配成功】", Thread.currentThread().getName());
    }
}
