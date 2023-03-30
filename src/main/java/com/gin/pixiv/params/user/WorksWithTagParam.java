package com.gin.pixiv.params.user;

import lombok.Getter;

/**
 * 带有指定Tag的用户作品参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/19 09:52
 */
@Getter
public class WorksWithTagParam {
    final String tag;
    final int offset;
    final int limit;

    public WorksWithTagParam(String tag, int page, int size) {
        this.tag = tag;
        this.offset = Math.max((page - 1), 0) * size;
        this.limit = size;
    }
}
