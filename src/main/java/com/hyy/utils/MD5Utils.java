package com.hyy.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.utils
 * @CLASS_NAME: MD5Utils
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 16:35
 * @Emial: 1299694047@qq.com
 */
public class MD5Utils {

    /**
     *  MD5加密类
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String code(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[]byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }


    public static void main(String[] args) {
        System.out.println(code("123456"));
    }
}
