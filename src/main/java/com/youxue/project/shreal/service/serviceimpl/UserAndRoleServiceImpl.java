package com.youxue.project.shreal.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youxue.project.shreal.entity.Result;
import com.youxue.project.shreal.entity.UserAndRole;
import com.youxue.project.shreal.mapper.UserAndRoleMapper;
import com.youxue.project.shreal.service.UserAndRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserAndRoleServiceImpl extends ServiceImpl<UserAndRoleMapper, UserAndRole> implements UserAndRoleService {

    @Resource
    private UserAndRoleMapper userAndRoleMapper;
    @Override
    public Result add(UserAndRole userAndRole) {
        Result result = new Result();
        try{
            userAndRole.setUid(userAndRole.getUid());
            userAndRole.setRid(userAndRole.getRid());
            userAndRoleMapper.insert(userAndRole);
            result.setCm(1,"");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setCm(0,"用户角色创建失败！");
            return result;
        }
    }
}
