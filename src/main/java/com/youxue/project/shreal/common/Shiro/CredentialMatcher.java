package com.youxue.project.shreal.common.Shiro;


import com.youxue.project.shreal.common.exception.BaseException;
import com.youxue.project.shreal.common.exception.code.BaseResponseCode;
import com.youxue.project.shreal.service.RedisService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;

import javax.security.auth.Subject;

/**
 * 认证模块
 */
public class CredentialMatcher  extends SimpleCredentialsMatcher {
    @Lazy
    @Autowired
    private RedisService redisService;

    @Value("${spring.redis.key.prefix.userToken}")
    private String userToken;
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info){
        String utoken =(String) token.getPrincipal();
        if (!redisService.exists(utoken)){
            SecurityUtils.getSubject().logout();
        }
        return true;
    }
}
