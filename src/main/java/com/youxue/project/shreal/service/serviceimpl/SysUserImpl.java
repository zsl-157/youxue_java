package com.youxue.project.shreal.service.serviceimpl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youxue.project.shreal.common.exception.BaseException;
import com.youxue.project.shreal.common.utils.PasswordUtils;
import com.youxue.project.shreal.entity.Result;
import com.youxue.project.shreal.entity.User;
import com.youxue.project.shreal.mapper.SysUserMapper;
import com.youxue.project.shreal.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class SysUserImpl extends ServiceImpl<SysUserMapper,User> implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public Result<User> register(User userEntity) {
        Result<User> result = new Result<>();
        User user = sysUserMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName,userEntity.getUserName()));
        if (user != null){
            System.out.println(user.getUserName());
            result.setCm(0,"用户已存在");
            log.error("用户已存在,注册失败");
            return result;
        }
        if(!StringUtils.isEmpty(userEntity.getPhoneNumber())){
            userEntity.setPhoneNumber(userEntity.getPhoneNumber());
        }
        userEntity.setUserName(userEntity.getUserName());
        userEntity.setPassword(userEntity.getPassword());
        String encryptPassword = PasswordUtils.md5Password(userEntity.getPassword());
        //userEntity.setPassword();
        try{
            sysUserMapper.insert(userEntity);
        }catch (Exception e){
            e.printStackTrace();
            log.error("注册用户失败");
            result.setCm(0,"注册失败");
            return result;
        }
        result.setCmd(1,"注册成功",userEntity);
        return result;
    }


    @Override
    public void login(User userEntity){
        Result<User> result = new Result<>();
        User user = sysUserMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName,userEntity.getUserName()));
        if (user == null){
            if(!StringUtils.isEmpty(userEntity.getPhoneNumber())){
                userEntity.setPhoneNumber(userEntity.getPhoneNumber());
            }
            userEntity.setUserName(userEntity.getUserName());
            userEntity.setPassword(userEntity.getPassword());
            String encryptPassword = PasswordUtils.md5Password(userEntity.getPassword());
            //userEntity.setPassword();
            try{
                sysUserMapper.insert(userEntity);
            }catch (Exception e){
                e.printStackTrace();
                log.error("注册用户失败");
                result.setCm(0,"注册失败");

            }
            result.setCmd(1,"注册成功,将自动跳转到首页",userEntity);
        }
        else{


        }

    }
}
