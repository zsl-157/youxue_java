package com.youxue.project.shreal.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.youxue.project.shreal.entity.*;
import com.youxue.project.shreal.entity.User;

import java.util.List;


public interface SysUserService extends IService<User> {
    Result register(User userEntity);

    Result login(User userEntity);

    User getOneUserByUserName(String userName);

    Result getAllUsers(int pageNum,int rows,int offset);


}
