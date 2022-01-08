package com.youxue.project.shreal.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class RedisService {
    private final StringRedisTemplate redisTemplate;
    public RedisService(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    public boolean exists(String key){
        return redisTemplate.hasKey(key);
    }
    public long getExpire(String key){
        return redisTemplate.getExpire(key);
    }
    public String getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void setKey(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public void del(String key){
        if(this.exists(key)){
            redisTemplate.delete(key);
        }
    }

    public void setKeyExpired(String key,String value,long seconds){
        redisTemplate.opsForValue().set(key,value);
        redisTemplate.expire(key,seconds, TimeUnit.SECONDS);
    }

}
