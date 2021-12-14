package com.kevinyin.battle.match2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/11/17 11:41
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MatchRoom {
    private Long id;
    private List<MatchPoolPlayerInfo> plays;

    public MatchPoolPlayerInfo getPlays(Integer playId) {
        if (plays == null) {
            return null;
        }
        return plays.stream().filter(r -> r.getPlayerId() != playId).collect(Collectors.toList()).get(0);
    }
}
