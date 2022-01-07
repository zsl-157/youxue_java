package com.youxue.project.shreal.controller;

import com.youxue.project.shreal.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiTestController {
    @Autowired
    private RedisService redisService;
    @GetMapping(value = "/v1")
    public String addRedisKey(){
        String key = "name";
        String name = "张石磊";
        try{
            redisService.setKey(key,name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "设置成功";
    }

}
