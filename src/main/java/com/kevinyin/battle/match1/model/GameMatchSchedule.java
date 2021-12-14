package com.kevinyin.battle.match1.model;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/11/16 19:33
 */
@Slf4j
public class GameMatchSchedule {

    private static ScheduledExecutorService sec = Executors.newSingleThreadScheduledExecutor();


    static {
        sec.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                matchProcess();
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    private static void matchProcess() {
        GameMatch1 m = GameMatch1.instance();
        List<Room> rooms = m.getAllRooms();
        if (rooms.size() == 0) {
            log.info("========匹配队列无正在进行的房间");
            return;
        }
        List<Room> needMatchRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (System.currentTimeMillis() - room.getCreateTime() > 5000) {
                needMatchRooms.add(room);
            }
        }
        if (needMatchRooms.size() < 2) {
            return;
        }
        List<Room> rooms1 = needMatchRooms.subList(0, needMatchRooms.size() / 2);
        List<Room> rooms2 = needMatchRooms.subList(needMatchRooms.size() / 2, needMatchRooms.size());
        int size = Math.min(rooms1.size(), rooms2.size());
        for (int i = 0; i < size; i++) {
            Room room1 = rooms1.get(i);
            Room room2 = rooms2.get(i);
            room1.setPlayer2(room2.getPlayer1());
            room2.setPlayer2(room1.getPlayer1());
            m.removeRoom(room1);
            m.removeRoom(room2);
        }
    }

}
