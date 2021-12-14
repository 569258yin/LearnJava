package com.kevinyin.battle.match1.model;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/11/16 18:15
 */
@Slf4j
public final class GameMatch1 {

    private GameMatch1() {
    }

    private static final GameMatch1 INSTANCE = new GameMatch1();

    public static GameMatch1 instance() {
        return INSTANCE;
    }

    private static final Map<Integer, LinkedList<Room>> ROOM_MAP = new ConcurrentHashMap<>();
    private static final Map<Long, Room> All_MAP = new ConcurrentHashMap<>();

    public Room match(Player player) {
        int rank = matchRank(player);
        LinkedList<Room> roomList = ROOM_MAP.get(rank);
        if (roomList == null) {
            Room room = buildMyRoom(player, rank);
            roomList = new LinkedList<>();
            roomList.push(room);
            ROOM_MAP.put(rank, roomList);
            All_MAP.put(room.getId(), room);
            return room;
        }
        Room room = roomList.pop();
        All_MAP.remove(room.getId());
        room.setPlayer2(player);
        return room;
    }


    public List<Room> getAllRooms() {
        return new ArrayList<>(All_MAP.values());
    }

    public void removeRoom(Room room) {
        All_MAP.remove(room.getId());
        LinkedList<Room> roomList = ROOM_MAP.get(room.getPlayer1().getRank());
        if (roomList != null) {
            while (roomList.iterator().hasNext()) {
                Room next = roomList.iterator().next();
                if (next.getId().equals(room.getId())) {
                    roomList.iterator().remove();
                }
            }
        }
    }

    private Room buildMyRoom(Player player, Integer rank) {
        Room room = new Room();
        room.setId(System.currentTimeMillis());
        room.setPlayer1(player);
        room.setRank(rank);
        room.setCreateTime(System.currentTimeMillis());
        return room;
    }

    public Integer matchRank(Player player) {
        Integer rank = player.getRank();
        int s = (rank / 10) % 10;
        if (s % 2 != 0) {
            s = s - 1;
        }
        int b = (rank / 100) % 10;
        return b * 100 + s * 10;
    }


}
