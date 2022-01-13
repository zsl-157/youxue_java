package com.youxue.project.shreal.controller;

import com.youxue.project.shreal.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "redis测试")
@RestController
@RequestMapping("/api")
public class ApiTestController {
    @Autowired
    private RedisService redisService;
    @Value("${spring.redis.key.prefix.userToken}")
    private String userToken;
    @ApiOperation(value = "redis设置键")
    @GetMapping(value = "/v1")
    public String addRedisKey(){
        String key = "name";
        String name = "张石磊";
        try{
            redisService.setKey(key,name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userToken+"设置成功";
    }

}

