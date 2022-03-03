package com.youxue.project.shreal.controller;

import com.youxue.project.shreal.service.HttpSessionService;
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
    private HttpSessionService httpSessionService;
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
    @ApiOperation(value = "redis设置键")
    @GetMapping(value = "/getval")
    public String getRedisValue(){
        String key = "name";
        String value = null;
        try{
            value = redisService.getValue(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("获取成功");
        return "获取成功!！"+value;
    }
    @ApiOperation(value = "redis设置键过期时间")
    @GetMapping(value = "/keyexp")
    public String addKeyExpire(){
        String key = "name1";
        String name = "大厦";
        Long s = null;
        try{

            redisService.setKeyExpired(key,name,100);

        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("设置成功");
        return userToken+"设置成功!";
    }
    @ApiOperation(value = "redis获取键过期时间")
    @GetMapping(value = "/getkeyExp")
    public long getKeyExp(){
        String key = "name1";
        Long s = null;
        try{
            s=redisService.getExpire(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("获取成功！");
        return s;
    }
    @GetMapping("/g")
    public String g(){
        return httpSessionService.getSession();
    }

    @GetMapping(value = "/v2")
    public Map<String,Object> getAlluserTest(){
        return sysUserService.getallTest();
    }

}

