package com.example.cache;

import com.google.common.collect.ImmutableMap;

import com.alibaba.fastjson.TypeReference;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author wangqing
 */
public interface Cache<V> {

    /**
     * get key-value
     * not exist return null
     * @param key
     * @param type
     * @return
     */
    V getIfPresent(final String key, final TypeReference<? extends V> type);

    /**
     * get key-value
     * not exist refresh cache
     * @param key
     * @param valueLoader
     * @param type
     * @return
     */
    V get(final String key, final Callable<? extends V> valueLoader, final TypeReference<? extends V> type) throws Exception;

    /**
     * get key-value
     * not exist refresh cache and set timeout
     * @param key
     * @param valueLoader
     * @param type
     * @param timeout
     * @param timeUnit
     * @return
     */
    V get(final String key, final Callable<? extends V> valueLoader, final TypeReference<? extends V> type, final long timeout, final TimeUnit timeUnit) throws Exception;

    /**
     * batch get key-value
     * @param keys
     * @return
     */
    ImmutableMap<String, V> getAllPresent(final Iterable<String> keys, final TypeReference<? extends V> type);

    /**
     * put key-value
     * @param key
     * @param value
     */
    void put(final String key, final V value);

    /**
     * put key-value and set timeout
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     */
    void put(final String key, final V value, final long timeout, final TimeUnit timeUnit);

    /**
     * batch put key-value
     * @param values
     */
    void putAll(final Map<String, ? extends V> values);

}
