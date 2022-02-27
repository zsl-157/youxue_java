package com.youxue.project.shreal.common.utils;


import java.util.Random;

public class  HttpUtils {
    public static String getRandomToken() {
        Random random = new Random();
        StringBuilder randomStr = new StringBuilder();

        // 根据length生成相应长度的随机字符串
        for (int i = 0; i < 32; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";

            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                randomStr.append((char) (random.nextInt(26) + temp));
            } else {
                randomStr.append(random.nextInt(10));
            }
        }

        return randomStr.toString();
    }
}
