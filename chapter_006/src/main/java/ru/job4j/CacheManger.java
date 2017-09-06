package ru.job4j;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * FileCacheManager class.
 * @author agavrikov
 * @since 28.08.2017
 * @version 1
 */
public abstract class CacheManger<K, V> {

    /**
     * Method for adding key/value in map.
     * @param key key
     * @param value value
     */
    public abstract void add(K key, V value);

    /**
     * Method for get value by key from map.
     * @param key key
     * @return content
     */
    public abstract V get(K key);

}
