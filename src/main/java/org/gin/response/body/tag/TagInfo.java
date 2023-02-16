package org.gin.response.body.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/1 10:11
 */
@Data
public class TagInfo {
    @JsonProperty("abstract")
    String abs;
    LangInfo en;
    @JsonProperty("en_new")
    LangInfo enNew;
    LangInfo ja;
    @JsonProperty("ja_new")
    LangInfo jaNew;
    String tag;
    String thumbnail;

    @Data
    static class LangInfo {
        @JsonProperty("abstract")
        String abs;
        String tag;
        String url;
    }
}
