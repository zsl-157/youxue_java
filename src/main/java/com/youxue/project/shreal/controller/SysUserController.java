package com.youxue.project.shreal.controller;


import com.youxue.project.shreal.entity.Result;
import com.youxue.project.shreal.entity.User;
import com.youxue.project.shreal.entity.User;
import com.youxue.project.shreal.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Api(tags = "用户相关操作Controller")
@RestController
@RequestMapping("/api")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @GetMapping("/v1/register")
    public Result<User> register(@ModelAttribute User userEntity){
        Result<User> result = sysUserService.register(userEntity);
        return result;
    }
    @GetMapping("/v1/login")//便于测试
    //@PostMapping("/login")
    public Result<User> login(@ModelAttribute User userEntity,HttpServletRequest request){
        Result<User> result = sysUserService.login(userEntity, request);
        return result;
    }
    //登录成功后请求接口获取用户信息
    @GetMapping("/v2/user/{username}")
    public Result getInfoByUsername(@PathVariable String username){
        Result result = sysUserService.getOneUserByUserName(username);
        return result;
    }
    //获取用户列表
    @GetMapping("/v2/users")
    public Result getAllUsers(@RequestParam int pageNum,@RequestParam int rows,@RequestParam int offset){
        Result result = sysUserService.getAllUsers(pageNum,rows,offset);
        return result;
    }

}
