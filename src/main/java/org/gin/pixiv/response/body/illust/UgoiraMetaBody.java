package org.gin.pixiv.response.body.illust;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 动图的额外信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 17:35
 */
@Getter
@Setter
public class UgoiraMetaBody {
    @JsonProperty("mime_type")
    String mimeType;
    String originalSrc;
    String src;
    List<Frame> frames;

    @Getter
    @Setter
    static class Frame {
        int delay;
        String file;
    }
}   
