package com.gin.utils;

/**
 * 对象工具类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 10:47
 */
public class ObjectUtils {

    public static boolean isEmpty(Object obj) {
        return obj == null || "".equals(obj) || "".equals(obj.toString().trim());
    }

}   
