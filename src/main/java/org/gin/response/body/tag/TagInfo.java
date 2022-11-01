package org.gin.response.body.tag;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/1 10:11
 */
@Data
public class TagInfo {
    @JSONField(alternateNames = "abstract")
    String abs;
    LangInfo en;
    @JSONField(alternateNames = "en_new")
    LangInfo enNew;
    LangInfo ja;
    @JSONField(alternateNames = "ja_new")
    LangInfo jaNew;
    String tag;
    String thumbnail;

    @Data
    static class LangInfo {
        @JSONField(alternateNames = "abstract")
        String abs;
        String tag;
        String url;
    }
}
