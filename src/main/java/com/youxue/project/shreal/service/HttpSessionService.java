package com.youxue.project.shreal.service;

import com.youxue.project.shreal.entity.User;

import java.util.Random;

public class HttpSessionService {
    public static String createRandomToken(){
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

    public static void main(String[] args) {
        HttpSessionService httpSessionService = new HttpSessionService();
        String s = httpSessionService.createRandomToken();
        System.out.println(s);

    }
    public static String createTokenAndUser(User user){
        String token = HttpSessionService.createRandomToken();

        return "";
    }
}
