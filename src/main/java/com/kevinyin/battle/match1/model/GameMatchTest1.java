package com.kevinyin.battle.match1.model;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/11/16 19:31
 */
@Slf4j
public class GameMatchTest1 {

    @SneakyThrows
    public static void main(String[] args) {
        GameMatch1 m = GameMatch1.instance();
        GameMatchSchedule schedule = new GameMatchSchedule();
        Player player1 = new Player(1, 1, 122);
        Player player2 = new Player(2, 1, 131);
        Player player3 = new Player(3, 1, 232);
        Player player4 = new Player(4, 1, 182);
        log.info("rank1={}", m.matchRank(player1));
        log.info("rank2={}", m.matchRank(player2));
        log.info("rank3={}", m.matchRank(player3));
        new PlayMatchThread("play1", player1, m).start();
        Thread.sleep(2000);
        new PlayMatchThread("play2", player2, m).start();
        new PlayMatchThread("play3", player3, m).start();
        new PlayMatchThread("player4", player4, m).start();
    }
}
