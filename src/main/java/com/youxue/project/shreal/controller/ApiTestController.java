package com.youxue.project.shreal.controller;

import com.youxue.project.shreal.service.RedisService;
import com.youxue.project.shreal.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@Api(tags = "redis测试")
@RestController
@RequestMapping("/api")
@Slf4j
public class ApiTestController {
    Logger logger = LoggerFactory.getLogger(ApiTestController.class);
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisService redisService;
    @Value("${spring.redis.key.prefix.userToken}")
    private String userToken;
    @ApiOperation(value = "redis设置键")
    @GetMapping(value = "/v1")
    public String addRedisKey(){
        String key = "name";
        String name = "zsl";
        try{
            redisService.setKey(key,name);
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("设置成功");
        return userToken+"设置成功!";
    }


    @GetMapping(value = "/v2")
    public Map<String,Object> getAlluserTest(){
        return sysUserService.getallTest();
    }

}

