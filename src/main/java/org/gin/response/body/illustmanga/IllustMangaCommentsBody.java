package org.gin.response.body.illustmanga;

import lombok.Data;
import org.gin.response.fields.IllustComment;

import java.util.List;

/**
 * 评论区Body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:28
 */
@Data
public class IllustMangaCommentsBody {
    /**
     * 是否还有更多数据
     */
    Boolean hasNext;
    /**
     * 评论发言
     */
    List<IllustComment> comments;
}
