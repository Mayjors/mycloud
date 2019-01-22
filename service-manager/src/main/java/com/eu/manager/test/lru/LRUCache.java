package com.eu.manager.test.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yuanjie
 * @date 2019/1/22 16:28
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int MAX_CACHE_SIZE;

    public LRUCache(int max_cache_size) {
        super((int) (Math.ceil(max_cache_size / 0.75)) +1, 0.75f, true);
        MAX_CACHE_SIZE = max_cache_size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_CACHE_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : entrySet()) {
            sb.append(String.format("%s : %s", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
