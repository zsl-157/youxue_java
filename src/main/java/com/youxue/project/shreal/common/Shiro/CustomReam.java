package com.youxue.project.shreal.common.Shiro;

import com.youxue.project.shreal.service.RedisService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;

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

        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
