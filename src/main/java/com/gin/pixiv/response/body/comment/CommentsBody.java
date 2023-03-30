package com.gin.pixiv.response.body.comment;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 评论区Body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:28
 */
@Getter
@Setter
public class CommentsBody {
    /**
     * 是否还有更多数据
     */
    Boolean hasNext;
    /**
     * 评论发言
     */
    List<CommentReply> comments;
}
