package com.example.cache;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author wangqing
 */
public class RedisCache<V> extends AbstractCache<V> {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisCache(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected String internalGet(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    protected void internalPut(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    protected void internalPut(String key, String value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

}
