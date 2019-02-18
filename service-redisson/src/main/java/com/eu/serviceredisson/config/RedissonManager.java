package com.eu.serviceredisson.config;

import org.redisson.Redisson;
import org.redisson.config.Config;

/**
 * redisson配置
 * @author yuanjie
 * @date 2019/1/31 15:04
 */
public class RedissonManager {

    private static Config config = new Config();
    // 声明redisoson对象
    private static Redisson redisson = null;

    static {
        config.useSingleServer().setAddress("127.0.0.1:6379");
        redisson = (Redisson) Redisson.create(config);
    }

    // 获取redisson对象的方法
    public static Redisson getRedisson() {
        return redisson;
    }
}
