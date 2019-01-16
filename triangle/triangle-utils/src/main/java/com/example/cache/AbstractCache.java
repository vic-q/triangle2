package com.example.cache;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author wangqing
 */
public abstract class AbstractCache<V> implements Cache<V> {

    @Override
    public V getIfPresent(final String key, final TypeReference<? extends V> type) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(type);
        return fromJson(internalGet(key), type);
    }

    @Override
    public V get(final String key, final Callable<? extends V> valueLoader, final TypeReference<? extends V> type) throws Exception {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(valueLoader);
        Preconditions.checkNotNull(type);

        final String jsonValue = internalGet(key);
        V value = fromJson(jsonValue, type);
        if (value != null) {
            return value;
        }

        value = valueLoader.call();
        if (value != null) {
            put(key, value);
        }
        return value;
    }

    @Override
    public V get(final String key, final Callable<? extends V> valueLoader, final TypeReference<? extends V> type, final long timeout, final TimeUnit timeUnit) throws Exception {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(valueLoader);
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(timeUnit);

        final String jsonValue = internalGet(key);
        V value = fromJson(jsonValue, type);
        if (value != null) {
            return value;
        }

        value = valueLoader.call();
        if (value != null) {
            put(key, value, timeout, timeUnit);
        }
        return value;
    }

    @Override
    public ImmutableMap<String, V> getAllPresent(final Iterable<String> keys, final TypeReference<? extends V> type) {
        final Map<String, V> result = Maps.newHashMap();
        keys.forEach(key -> {
            final V value = getIfPresent(key, type);
            if (value != null) {
                result.put(key, value);
            }
        });
        return ImmutableMap.<String, V>builder().putAll(result).build();
    }

    @Override
    public void put(final String key, final V value) {
        Preconditions.checkNotNull(key);
        internalPut(key, toJson(value));
    }

    @Override
    public void put(final String key, final V value, final long timeout, final TimeUnit timeUnit) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(timeUnit);
        internalPut(key, toJson(value), timeout, timeUnit);
    }

    @Override
    public void putAll(final Map<String, ? extends V> values) {
        final Map<String, ? extends V> copyValues = values;
        copyValues.forEach((key, value) -> put(key, value));
    }

    protected abstract String internalGet(final String key);

    protected abstract void internalPut(final String key, final String value);

    protected abstract void internalPut(final String key, final String value, final long timeout, final TimeUnit timeUnit);

    private String toJson(final V value) {
        return JSONObject.toJSONString(value);
    }

    private V fromJson(final String jsonValue, final TypeReference<? extends V> type) {
        return JSONObject.parseObject(jsonValue, type);
    }
}
