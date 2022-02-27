package com.youxue.project.shreal.controller;


import com.youxue.project.shreal.entity.Result;
import com.youxue.project.shreal.entity.User;
import com.youxue.project.shreal.entity.User;
import com.youxue.project.shreal.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "用户相关操作Controller")
@RestController
@RequestMapping("/api")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @GetMapping("/register")
    public Result<User> register(@ModelAttribute User userEntity){
        Result<User> result = sysUserService.register(userEntity);
        return result;
    }
    @GetMapping("/login")//便于测试
    //@PostMapping("/login")
    public Result<User> login(@ModelAttribute User userEntity){
        Result<User> result = sysUserService.login(userEntity);
        return result;
    }
    @GetMapping("/user/{username}")
    public Result getInfoByUsername(@PathVariable String username){
        return new Result();
    }


}
