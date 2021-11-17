package com.kevinyin.lock.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.apache.commons.lang3.math.NumberUtils;

public class RedisLock {

    private RedisClient redisClient;
    private StatefulRedisConnection<String, String> connection;

    public RedisLock() {
        this.redisClient = RedisClient.create("redis://192.168.124.5:6379/0");
        this.connection = redisClient.connect();

    }

    /**
     * @param lockKey
     * @param expireTime 单位毫秒
     * @return
     */
    public boolean lock(String lockKey, long expireTime) {
        RedisCommands<String, String> syncCommands = connection.sync();
        long currentTime = System.currentTimeMillis();
        long newExpireTime = currentTime + expireTime;
        if (syncCommands.setnx(lockKey, String.valueOf(newExpireTime))) {
            return true;
        }
        long oldExpireTime = NumberUtils.toLong(syncCommands.get(lockKey), 0);
        if (oldExpireTime > currentTime) {
            return false;
        }
        long reOldExpireTime = NumberUtils.toLong(syncCommands.getset(lockKey, String.valueOf(newExpireTime)), -1);
        return reOldExpireTime == oldExpireTime;
    }

    public void unLock(String lockKey) {
        RedisCommands<String, String> syncCommands = connection.sync();
        syncCommands.del(lockKey);
    }

    public void shutdown() {
        connection.close();
        redisClient.shutdown();
    }
}
