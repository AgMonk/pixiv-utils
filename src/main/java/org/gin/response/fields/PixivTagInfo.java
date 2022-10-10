package org.gin.response.fields;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * 标签字段
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 16:08
 **/
@Data
public class PixivTagInfo {
    boolean isLocked;
    List<PixivTag> tags;
    boolean writable;

    @Data
    static class PixivTag {
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
}
