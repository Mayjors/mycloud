package com.eu.manager.test.lru;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author yuanjie
 * @date 2019/1/22 16:36
 */
public class LRUCache2<K, V> {

    private final int MAX_CACHE_SIZE;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    LinkedHashMap<K, V> map;

    public LRUCache2(int max_cache_size) {
        MAX_CACHE_SIZE = max_cache_size;
        // 根据max_cache_size和加载因子计算hashmap的capactiy, +1确保当达到上限时不会触发hashmap的扩容
        int capacity = (int) Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTOR) +1;
        map = new LinkedHashMap(capacity, DEFAULT_LOAD_FACTOR, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
//                return super.removeEldestEntry(eldest);
                return size() > MAX_CACHE_SIZE;
            }
        };
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void remove(K key) {
        map.remove(key);
    }

    public synchronized Set<Map.Entry<K, V>> getAll() {
        return map.entrySet();
    }

    public synchronized int size() {
        return map.size();
    }

    public synchronized void clear() {
        map.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            sb.append(String.format("%s : %s", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
