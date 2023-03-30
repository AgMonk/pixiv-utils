package com.gin.pixiv.params.comment;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询作品评论楼中楼参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:22
 */
@Getter
@Setter
@AllArgsConstructor
public class CommentRepliesParam {
    @JsonProperty("comment_id")
    Long commentId;
    int page;

}
