package com.kevinyin.battle.match2;

import com.kevinyin.battle.match1.model.Player;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/11/16 19:27
 */
@Slf4j
public class PlayThread extends Thread {

    private Player player;

    public PlayThread(String threadName, Player player) {
        super(threadName);
        this.player = player;
    }

    @SneakyThrows
    @Override
    public void run() {
        GameMatch2.putPlayerIntoMatchPool(player, player.getRank());
        boolean w = true;
        while (w) {
            log.info("玩家{}正在进行匹配中....", Thread.currentThread().getName());
            Thread.sleep(1000);
            MatchRoom room = GameMatch2.getSuccessRoom(player.getId());
            if (room != null) {
                MatchPoolPlayerInfo play2 = room.getPlays(player.getId());
                log.info("++++++++玩家{}匹配成功,rank={}，对手为{},rank={}++++++++++", player.getId(), player.getRank(), play2.getPlayerId(), play2.getPlayer().getRank());
                w = false;
                GameMatch2.removePlayerFromMatchPool(player.getId());
            }
        }
    }
}
