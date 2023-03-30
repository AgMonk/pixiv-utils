package org.gin.pixiv.response.body.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/12 14:20
 **/
@Getter
@Setter
public class BookmarkAddBody {
    /**
     * 收藏id 用于移除收藏
     */
    @JsonProperty(value = "last_bookmark_id")
    Long lastBookmarkId;

    @JsonProperty(value = "stacc_status_id")
    Long statusId;
}
