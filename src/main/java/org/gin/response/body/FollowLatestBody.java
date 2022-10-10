package org.gin.response.body;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.gin.response.fields.ArtworkInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 17:01
 **/
@Data
public class FollowLatestBody {
    Page page;
    Thumbnails thumbnails;
    HashMap<String,TagTranslation> tagTranslation;

    @Data
    static class Page {
        List<Long> ids;
    }

    @Data
    static class Thumbnails {
        List<ArtworkInfo> illust;
    }

    @Data
    static class TagTranslation {
        String en;
        String ko;
        String zh;
        @JSONField(alternateNames = "zh_tw")
        String zhTw;
    }
}
