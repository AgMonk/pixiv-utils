package org.gin.response.body.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户的收藏作品中使用的标签body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 15:19
 **/
@Getter
@Setter
public class CommonBookmarkTagsBody {
    @JsonProperty("private")
    List<Tag> privateTag;
    @JsonProperty("public")
    List<Tag> publicTag;
    Boolean tooManyBookmark;
    Boolean tooManyBookmarkTags;

    @Getter
    @Setter
    static class Tag {
        @JsonProperty("cnt")
        long count;
        String tag;
    }
}
