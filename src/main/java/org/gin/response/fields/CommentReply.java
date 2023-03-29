package org.gin.response.fields;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 评论区发言
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:29
 */
@Getter
@Setter
public class CommentReply {
    String comment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime commentDate;
    Long commentParentId;
    Long commentUserId;
    Boolean editable;
    Boolean hasReplies;
    Long id;
    /**
     * 用户头像
     */
    @JsonProperty("img")
    String userAvatar;
    Boolean isDeletedUser;
    /**
     * 纯表回复
     */
    Integer stampId;
    Long userId;
    String userName;

    /*以下为楼中楼字段*/

    Long replyToUserId;
}
