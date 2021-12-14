package com.kevinyin.battle.match2;

import com.kevinyin.battle.match1.model.Player;
import lombok.SneakyThrows;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/11/17 11:37
 */
public class GameMatch2Test2 {

    @SneakyThrows
    public static void main(String[] args) {
        GameMatch2 gameMatch2 = new GameMatch2();
        new PlayThread("Thread-" + 1, new Player(1, 1, 123)).start();
        Thread.sleep(500);
        new PlayThread("Thread-" + 2, new Player(2, 2, 185)).start();
    }
}
