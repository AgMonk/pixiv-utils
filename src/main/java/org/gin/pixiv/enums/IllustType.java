package org.gin.pixiv.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 作品类型 插画=0 漫画=1 动图=2
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 12:32
 */
@RequiredArgsConstructor
public enum IllustType {
    /**
     * 插画
     */
    illustration(0),
    /**
     * 漫画
     */
    manga(1),
    /**
     * 动图
     */
    ugoira(2),

    ;
    @JsonValue
    final int id;

}
