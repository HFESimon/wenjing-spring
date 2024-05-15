package com.algorithm.design;

import java.util.LinkedHashMap;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/15 9:55 上午
 */
public class LRUCache<E, U> {

    LinkedHashMap<E, U> cache;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<>();
    }

    public void put(E key, U value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            refresh(key);
        }
        if (cache.size() >= capacity) {
            cache.remove(cache.keySet().iterator().next());
            cache.put(key, value);
        }
        cache.put(key, value);
    }

    public U get(E key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        refresh(key);
        return cache.get(key);
    }

    private void refresh(E key) {
        if (!cache.containsKey(key)) {
            return;
        }
        U remove = cache.remove(key);
        cache.put(key, remove);
    }
}
