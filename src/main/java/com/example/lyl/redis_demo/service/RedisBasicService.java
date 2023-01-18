package com.example.lyl.redis_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Basic Operation Of Redis
 */
@Service
public class RedisBasicService {

    private RedisTemplate stringObjectRedisTemplate;

    @Autowired
    public RedisBasicService(RedisTemplate stringObjectRedisTemplate) {
        this.stringObjectRedisTemplate = stringObjectRedisTemplate;
    }

    //======================== String ===============================
    public Object getString(String key){
        return key==null ? null : stringObjectRedisTemplate.opsForValue().get(key);
    }

    public void setString(String key , Object object){
        stringObjectRedisTemplate.opsForValue().set("stringKey", "StringValue", Duration.ofSeconds(30L));
    }


    //======================= Hash ===================================
    public Object getHash(String key, String hashKey){
        return stringObjectRedisTemplate.opsForHash().get(key, hashKey);
    }

    public Map<Object, Object> getHash(String key) {
        return stringObjectRedisTemplate.opsForHash().entries(key);
    }

    public void setHash(String key, Map map) {
        stringObjectRedisTemplate.opsForHash().putAll(key, map);
        stringObjectRedisTemplate.expire(key , 30L, TimeUnit.SECONDS);
    }


    //====================== List ========================================
    public List<Object> getList(String key) {
        return stringObjectRedisTemplate.opsForList().range(key, 0, -1);
    }

    public void setList(String key, List<Object> list){
        stringObjectRedisTemplate.opsForList().rightPushAll(key,list);
        stringObjectRedisTemplate.expire(key, 1, TimeUnit.HOURS);
    }


}
