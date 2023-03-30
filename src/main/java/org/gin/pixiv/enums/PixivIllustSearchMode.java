package org.gin.pixiv.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 绘画的检索模式
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/18 16:13
 */
public enum PixivIllustSearchMode {
    /**
     * 默认，标签部分一致
     */
    DEFAULT("s_tag"),
    /**
     * 标签，完全一致
     */
    FULL("s_tag_full"),
    /**
     * 标题、说明文字
     */
    TC("s_tc");
    final String value;

    PixivIllustSearchMode(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
