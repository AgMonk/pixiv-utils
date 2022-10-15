package org.gin.response.body;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 查询动图的其他信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:49
 */
@Data
public class UgoiraMetaBody {
    @JSONField(alternateNames = "mime_type")
    String mimeType;
    String originalSrc;
    String src;
    List<Frame> frames;

    @Data
    static class Frame {
        int delay;
        String file;
    }
}
