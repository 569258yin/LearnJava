package com.kevinyin.battle.match2;

import com.kevinyin.battle.match1.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/11/17 11:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchPoolPlayerInfo {

    private Player player;
    //开始匹配时间
    private long startMatchTime;


    public MatchPoolPlayerInfo(Player player, int rank) {
        super();
        this.player = player;
        this.startMatchTime = System.currentTimeMillis();
    }

    public int getPlayerId() {
        return player.getId();
    }

    public int getRank() {
        return player.getRank();
    }

    public long getStartMatchTime() {
        return startMatchTime;
    }
}
