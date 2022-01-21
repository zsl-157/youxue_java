package com.youxue.project.shreal.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.youxue.project.shreal.entity.*;
import com.youxue.project.shreal.entity.User;


public interface SysUserService extends IService<User> {
    Result register(User userEntity);

    void login(User userEntity);

}
