package org.gin.pixiv.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 是否为AI作画图
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 12:28
 */
@RequiredArgsConstructor
public enum AiType {
    /**
     * 在AI作画大量出现前投稿的作品
     */
    NOT_AI(0),
    /**
     * 不是AI作品(不可靠)
     */
    NOT_AI_1(1),
    /**
     * 是AI作品
     */
    IS_AI(2),

    ;
    @JsonValue
    final int id;
}
