package com.youxue.project.shreal.common.Shiro;


import com.youxue.project.shreal.service.RedisService;


import org.apache.shiro.mgt.SecurityManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


@Configuration
public class ShiroConfiguration {
    @Autowired
    private RedisService redisService;
    @Lazy
    @Autowired
    private RedisService redisDb;




}
