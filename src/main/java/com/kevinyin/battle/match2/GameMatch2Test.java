package com.kevinyin.battle.match2;

import com.kevinyin.battle.match1.model.Player;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/11/17 11:37
 */
public class GameMatch2Test {

    @SneakyThrows
    public static void main(String[] args) {
        GameMatch2 gameMatch2 = new GameMatch2();
        for (int i = 0; i < 20; i++) {
            new PlayThread("Thread-" + i, new Player(i, i, RandomUtils.nextInt(100, 200))).start();
            Thread.sleep(500);
        }
    }
}
