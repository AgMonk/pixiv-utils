package org.gin.response.body.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/1 10:11
 */
@Getter
@Setter
public class TagInfo {
    /**
     * 解释
     */
    @JsonProperty("abstract")
    String description;
    LangInfo en;
    @JsonProperty("en_new")
    LangInfo enNew;
    LangInfo ja;
    @JsonProperty("ja_new")
    LangInfo jaNew;
    String tag;
    /**
     * 头像(封面)缩略图地址
     */
    @JsonProperty("thumbnail")
    String thumbnailUrl;

    @Getter
    @Setter
    static class LangInfo {
        @JsonProperty("abstract")
        String description;
        String tag;
        String url;
    }
}
