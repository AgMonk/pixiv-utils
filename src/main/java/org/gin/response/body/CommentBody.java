package org.gin.response.body;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 发表评论的body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/17 14:40
 */
@Data
public class CommentBody {
    String comment;
    @JSONField(alternateNames = "comment_id")
    Long commentId;
    @JSONField(alternateNames = "parent_id")
    Long parentId;
    @JSONField(alternateNames = "stamp_id")
    Integer stampId;
    @JSONField(alternateNames = "user_id")
    Long userId;
    @JSONField(alternateNames = "user_name")
    String userName;
}
