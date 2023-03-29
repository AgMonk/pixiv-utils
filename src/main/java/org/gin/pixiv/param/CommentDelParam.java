package org.gin.pixiv.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 删除评论参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 15:26
 */
@Getter
@RequiredArgsConstructor
public class CommentDelParam {
    /**
     * 作品id(pid或nid)
     */
    @JsonProperty("i_id")
    final long id;
    /**
     * 评论id
     */
    @JsonProperty("del_id")
    final long commentId;
}   
