package com.youxue.project.shreal.common.utils;


import org.apache.shiro.crypto.hash.Md5Hash;
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
        Md5Hash md5Hash = new Md5Hash(password,"",1024);
        String result = md5Hash.toHex();
        return result;

    }


}
