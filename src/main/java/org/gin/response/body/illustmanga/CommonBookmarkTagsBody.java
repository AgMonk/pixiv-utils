package org.gin.response.body.illustmanga;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 用户的收藏作品中使用的标签body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 15:19
 **/
@Data
public class CommonBookmarkTagsBody {
    @JSONField(alternateNames = "private")
    List<Tag> privateTag;
    @JSONField(alternateNames = "public")
    List<Tag> publicTag;
    Boolean tooManyBookmark;
    Boolean tooManyBookmarkTags;

    @Data
    static class Tag {
        @JSONField(alternateNames = "cnt")
        long count;
        String tag;
    }
}
