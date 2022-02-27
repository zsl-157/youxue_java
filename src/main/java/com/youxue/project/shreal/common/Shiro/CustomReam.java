package com.youxue.project.shreal.common.Shiro;

import com.alibaba.fastjson.JSONObject;
import com.youxue.project.shreal.common.exception.BaseException;
import com.youxue.project.shreal.common.exception.code.BaseResponseCode;
import com.youxue.project.shreal.common.utils.Constraints;
import com.youxue.project.shreal.entity.User;
import com.youxue.project.shreal.service.RedisService;
import com.youxue.project.shreal.service.SysUserService;
import com.youxue.project.shreal.service.serviceimpl.SysUserImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.util.ByteUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;



public class CustomReam extends AuthorizingRealm {
    @Autowired
    private SysUserImpl sysUser;
    @Value("${spring.redis.key.prefix.userToken}")
    private String userToken;
    @Lazy
    @Autowired
    private RedisService redisService;
    @Lazy
    @Autowired
    private RedisService redisDb;

    @Override
    @SuppressWarnings("unchecked")
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String sessionInfo = redisDb.getValue(userToken+principalCollection.getPrimaryPrincipal());
        if(StringUtils.isEmpty(sessionInfo)){
            throw new BaseException(BaseResponseCode.TOKEN_ERROR);
        }
        JSONObject sessionInfoObj = JSONObject.parseObject(sessionInfo);
        if (sessionInfoObj == null){
            throw new BaseException(BaseResponseCode.TOKEN_ERROR);
        }
        if (sessionInfoObj.get(Constraints.ROLES_KEY) != null){
            simpleAuthorizationInfo.addRoles((Collection<String>) sessionInfoObj.get(Constraints.ROLES_KEY));
        }
        if (sessionInfoObj.get(Constraints.PERMISSIONS_KEY) != null){
            simpleAuthorizationInfo.addStringPermissions((Collection<String>) sessionInfoObj.get(Constraints.PERMISSIONS_KEY));
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName =(String) authenticationToken.getPrincipal();
        User user = sysUser.getOneUserByUserName(userName);
        if (!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUserName(),user.getEncryptedPassword(),ByteSource.Util.bytes(""),getName());
        }
        else {
            return new SimpleAuthenticationInfo("","", ByteSource.Util.bytes(""),getName());
        }

    }
}
