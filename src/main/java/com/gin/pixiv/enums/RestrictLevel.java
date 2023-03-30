package com.gin.pixiv.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 限制级别
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 12:53
 */
@RequiredArgsConstructor
public enum RestrictLevel {
    /**
     * 常规
     */
    normal(0),
    /**
     * r18
     */
    r18(1),
    /**
     * r18g
     */
    r18g(2),

    ;
    @JsonValue
    final int id;
}
