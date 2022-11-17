package org.gin.params.illustmanga;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 查询作品评论楼中楼参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:22
 */
@Data
@AllArgsConstructor
public class CommentRepliesParam {
    @JSONField(name = "comment_id")
    Long commentId;
    int page;

}
