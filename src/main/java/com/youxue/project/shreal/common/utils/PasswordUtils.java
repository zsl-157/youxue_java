package com.youxue.project.shreal.common.utils;


import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密
 */

public class PasswordUtils {
    public static String md5Password(String password){
        byte[] md5PasswordByte = null;
        try {
            MessageDigest passwordDigest = MessageDigest.getInstance("md5");
            md5PasswordByte = passwordDigest.digest(password.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encryptedPassword = new BigInteger(1,md5PasswordByte).toString(16);
        return encryptedPassword;
    }


}
