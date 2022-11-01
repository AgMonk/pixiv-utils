package org.gin.response.fields;

import lombok.Data;

import java.util.List;

/**
 * 标签字段
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 16:08
 **/
@Data
public class PixivTagInfo {
    Long authorId;
    boolean isLocked;
    List<PixivTag> tags;
    boolean writable;
    Boolean success;
}
