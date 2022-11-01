package org.gin.response.fields;

import java.util.HashMap;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/1 10:08
 */
public class PixivTag {
    boolean deletable;
    boolean locked;
    /**
     * 标签原名
     */
    String tag;
    /**
     * 翻译
     */
    HashMap<String, String> translation;
}