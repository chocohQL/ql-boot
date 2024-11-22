package com.qilicoh.boot.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@SuppressWarnings(value = { "unchecked", "rawtypes" })
public class RedisClient {
    @Resource
    private RedisTemplate redisTemplate;

    public <T> void setJson(String key, T value) {
        set(key, JSON.toJSONString(value));
    }

    public <T> void setJson(String key, T value, long seconds) {
        set(key, JSON.toJSONString(value), seconds);
    }

    public <T> T getJson(String key, Class<T> clazz) {
        return JSON.parseObject(get(key).toString(), clazz);
    }

    public <T> List<T> getJsonList(String key, Class<T> clazz) {
        return JSON.parseArray(redisTemplate.opsForValue().get(key).toString(), clazz);
    }

    public <T> void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> void set(String key, T value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    public <T> T get(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public Long delete(List<String> keys) {
        return redisTemplate.delete(keys);
    }

    public Boolean expire(String key, long seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public Long decrease(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    public <T> Long setList(String key, List<T> list) {
        return redisTemplate.opsForList().rightPushAll(key, list);
    }

    public <T> List<T> getList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    public Long listSize(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    public Long listPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    public <T> void setMap(String key, Map<String, T> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public <T> Map<String, T> getMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public Object hashGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    public void hashSet(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public void hashDelete(String key, Object... hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    public Boolean hasHashKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }
}
