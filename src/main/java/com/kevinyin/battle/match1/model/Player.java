package com.kevinyin.battle.match1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/11/16 16:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private Integer id;
    private Integer userId;
    private Integer rank;
}
