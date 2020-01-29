package com.sys.manage.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * base64工具类
 * @Author tianms
 */
public class Base64Utils {
    /**
     * 字符串加密
     */
    public static final String encode(String base){
        return Base64Utils.encode(base.getBytes());
    }

    /**
     * 对字节数组进行加密
     * @param baseBuff 原字节数组
     * @return 加密后的字符串
     */
    public static final String encode(byte[] baseBuff){
        return new BASE64Encoder().encode(baseBuff);
    }

    /**
     * 字符串解密
     * @param encoder 需要解密的字符串
     * @return 解密后的字符串
     */
    public static final String decode(String encoder){
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] buf = decoder.decodeBuffer(encoder);
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
