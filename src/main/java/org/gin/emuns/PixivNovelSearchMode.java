package org.gin.emuns;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 绘画的检索模式
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/18 16:13
 */
public enum PixivNovelSearchMode {
    /**
     * 默认
     */
    DEFAULT("s_tag"),
    /**
     * 标签、部分一致
     */
    TAG_ONLY("s_tag_only"),
    /**
     * 标签，完全一致
     */
    FULL("s_tag_full"),
    /**
     * 正文
     */
    TC("s_tc");
    final String value;

    PixivNovelSearchMode(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
