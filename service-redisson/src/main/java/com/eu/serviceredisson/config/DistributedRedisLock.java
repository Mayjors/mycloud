package com.eu.serviceredisson.config;

import org.redisson.Redisson;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * 锁的获取和释放
 * @author yuanjie
 * @date 2019/1/31 15:12
 */
public class DistributedRedisLock {
    // 从配置类中获取redisson对象
    private static Redisson redisson = RedissonManager.getRedisson();

    private static final String LOCK_TITLE = "redisLock_";

    /**
     * 加锁
     * @param lockName
     * @return
     */
    public static boolean acquire(String lockName) {
        // 声明key对象
        String key = LOCK_TITLE + lockName;
        // 获取锁对象
        RLock mylock = redisson.getLock(key);
        // 加锁,并设置锁过期时间，防止死锁的产生
        mylock.lock(2, TimeUnit.MINUTES);
        System.out.println("====lock====" + Thread.currentThread().getName());
        // 加锁成功
        return true;
    }

    public static void release(String lockName) {
        // 必须是和加锁时的同一个key
        String key = LOCK_TITLE + lockName;
        // 获取锁对象
        RLock mylock = redisson.getLock(key);
        // 释放锁
        mylock.unlock();
        System.out.println("====unlock====" + Thread.currentThread().getName());
    }
}
