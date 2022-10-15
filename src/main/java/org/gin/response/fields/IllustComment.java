package org.gin.response.fields;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 评论区发言
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:29
 */
@Data
public class IllustComment {
    String comment;
    LocalDateTime commentDate;
    Long commentParentId;
    Long commentUserId;
    Boolean editable;
    Boolean hasReplies;
    Long id;
    /**
     * 用户头像
     */
    @JSONField(alternateNames = "img")
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
