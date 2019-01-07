package com.eu.mutildatabase.config;

/**
 * 保存一个线程安全的DatabaseType容器
 * @author yuanjie
 * @date 2019/1/7 17:57
 */
public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }
}
