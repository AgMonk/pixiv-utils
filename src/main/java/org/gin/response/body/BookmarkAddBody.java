package org.gin.response.body;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/12 14:20
 **/
@Data
public class BookmarkAddBody {
    /**
     * 收藏id 用于移除收藏
     */
    @JSONField(alternateNames = "last_bookmark_id")
    Long lastBookmarkId;

    @JSONField(alternateNames = "stacc_status_id")
    Long statusId;
}
