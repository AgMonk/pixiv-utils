package org.gin.response.body.comment;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 发表评论的body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/17 14:40
 */
@Data
public class PostCommentBody {
    String comment;
    @JsonProperty("comment_id")
    Long commentId;
    @JsonProperty("parent_id")
    Long parentId;
    @JsonProperty("stamp_id")
    Integer stampId;
    @JsonProperty("user_id")
    Long userId;
    @JsonProperty("user_name")
    String userName;
}
