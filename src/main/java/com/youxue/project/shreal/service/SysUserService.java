package com.youxue.project.shreal.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.youxue.project.shreal.entity.*;
import com.youxue.project.shreal.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface SysUserService extends IService<User> {
    Result register(User userEntity);

    Result login(User userEntity, HttpServletRequest request);

    Result getOneUserByUserName(String userName);

    Result getAllUsers(int pageNum,int rows,int offset);

    Map<String,Object> getallTest();
}
