package org.gin.response.fields;

import lombok.Data;

import java.util.HashMap;

/**
 * 标签
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 16:09
 **/
@Data
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
    HashMap<String,String> translation;
}
