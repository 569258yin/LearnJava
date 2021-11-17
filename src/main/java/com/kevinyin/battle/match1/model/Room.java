package com.kevinyin.battle.match1.model;

import lombok.Data;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/11/16 16:42
 */
@Data
public class Room {
    private Long id;

    private Player player1;
    private Player player2;

    private Integer rank;
    private Long createTime;

    public boolean matchSuccess() {
        return player1 != null && player2 != null;
    }

}
