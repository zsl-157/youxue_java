package com.youxue.project.shreal.service;

import com.alibaba.fastjson.JSONObject;
import com.youxue.project.shreal.common.utils.Constraints;
import com.youxue.project.shreal.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Service
public class HttpSessionService {
    @Resource
    private RedisService redisService;
    @Resource
    private HttpServletRequest request;
    @Value("${spring.redis.key.prefix.userToken}")
    private String userToken;

    @Value("${spring.redis.key.expire.userToken}")
    private int expire;


    public String createRandomToken(){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<32;i++){
            //确定当前是数字还是char
            String numOchar = random.nextInt(2)%2==0?"char":"num";
            if ("char".equalsIgnoreCase(numOchar)){
                //确定大小写
                int lowerOupper = random.nextInt(2)%2==0?65:97;
                stringBuilder.append((char) (random.nextInt(26)+lowerOupper));
            }else {
                stringBuilder.append(random.nextInt(10));
            }
        }
        return stringBuilder.toString();
    }


    public String createTokenAndUser(User user){
        String token = createRandomToken()+"#"+user.getUserId();
        JSONObject sessionInfo = new JSONObject();
        sessionInfo.put(Constraints.USERID_KEY,user.getUserId());
        sessionInfo.put(Constraints.USERNAME_KEY,user.getUserName());
        sessionInfo.put(Constraints.ROLES_KEY,user.getRole());
        String key = userToken+token;
        redisService.setKeyExpired(key,sessionInfo.toJSONString(),expire);
        return token;
    }

    public String getUserByToken(String token){
        if (StringUtils.isEmpty(token) || token.contains("#")){
            return "";
        }else{
            return token.substring(token.indexOf("#")+1);
        }
    }

    public String getTokenFromHeader(){
        String token = request.getHeader(Constraints.ACCESS_TOKEN);
        if (StringUtils.isEmpty(token)){
            token = request.getParameter(Constraints.ACCESS_TOKEN);
        }
        return token;
    }



}
