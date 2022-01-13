package com.youxue.project.shreal.common.Shiro;

import com.alibaba.fastjson.JSONObject;
import com.youxue.project.shreal.common.exception.BaseException;
import com.youxue.project.shreal.common.exception.code.BaseResponseCode;
import com.youxue.project.shreal.common.utils.Constraints;
import com.youxue.project.shreal.service.RedisService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;

import java.util.Collection;

public class CustomReam extends AuthorizingRealm {

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
        if (sessionInfoObj.get(Constraints.ROLE_KEY) != null){
            simpleAuthorizationInfo.addRoles((Collection<String>) sessionInfoObj.get(Constraints.ROLE_KEY));
        }
        if (sessionInfoObj.get(Constraints.PERMISSION_KEY) != null){
            simpleAuthorizationInfo.addStringPermissions((Collection<String>) sessionInfoObj.get(Constraints.PERMISSION_KEY));
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(),authenticationToken.getPrincipal(),getName());
    }
}
