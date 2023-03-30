package com.gin.pixiv.response.body.tag;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 标签字段
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 16:08
 **/
@Getter
@Setter
public class PixivTagInfo {
    Long authorId;
    boolean isLocked;
    List<PixivTag> tags;
    boolean writable;
    Boolean success;

    @Getter
    @Setter
    public static class PixivTag {
        boolean deletable;
        boolean locked;
        /**
         * 标签原名
         */
        String tag;
        /**
         * 翻译
         */
        LinkedHashMap<String, String> translation;
    }
}
