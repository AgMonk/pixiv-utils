package org.gin.response.body.illustmanga;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.gin.response.PixivResponse;

import java.util.List;

/**
 * 查询动图的其他信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:49
 */
@Getter
@Setter
public class UgoiraMetaRes extends PixivResponse<UgoiraMetaRes.UgoiraMetaBody> {


    @Getter
    @Setter
    public static class UgoiraMetaBody {
        @JSONField(alternateNames = "mime_type")
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
}
