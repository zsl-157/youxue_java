package com.youxue.project.shreal.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youxue.project.shreal.entity.Result;
import com.youxue.project.shreal.entity.Role;
import com.youxue.project.shreal.mapper.RoleMapper;
import com.youxue.project.shreal.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Override
    public void addRole(Role role) {
        try{
            role.setRname(role.getRname());
            roleMapper.insert(role);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRole() {

    }
}
