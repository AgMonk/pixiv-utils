package org.gin.utils;

import java.util.HashMap;

/**
 * Map工具类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 16:54
 */
public class MapUtils {
    public static <K, V> HashMap<K, V> singleEntry(K k, V v) {
        final HashMap<K, V> map = new HashMap<>(1);
        map.put(k, v);
        return map;
    }

}   
