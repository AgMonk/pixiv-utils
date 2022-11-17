package org.gin.params.comment;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.gin.emuns.PixivCommentType;
import org.gin.emuns.PixivStamp;
import org.gin.emuns.PixivStampSerializer;

/**
 * 评论参数(相同部分)
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/17 14:23
 */
@Getter
@Setter
public class CommentParam {
    final PixivCommentType type;

    @JSONField(name = "author_user_id")
    final Long authorUserId;
    /**
     * 评论到楼中楼
     */
    @JSONField(name = "parent_id")
    final Long parentId;
    final String comment;
    @JSONField(name = "stamp_id", serializeUsing = PixivStampSerializer.class)
    final PixivStamp stamp;

    @JSONField(name = "illust_id")
    Long pid;
    @JSONField(name = "novel_id")
    Long nid;


    /**
     * 文字评论
     * @param authorUserId 作品作者id
     * @param parentId     如果是楼中楼，添加该项
     * @param comment      评论内容
     */
    public CommentParam(Long authorUserId, Long parentId, String comment) {
        this.type = PixivCommentType.comment;
        this.authorUserId = authorUserId;
        this.parentId = parentId;
        this.comment = comment;
        this.stamp = null;
    }

    /**
     * 表情评论
     * @param authorUserId 作品作者id
     * @param parentId     如果是楼中楼，添加该项
     * @param stamp        表情贴图
     */
    public CommentParam(Long authorUserId, Long parentId, PixivStamp stamp) {
        this.type = PixivCommentType.stamp;
        this.authorUserId = authorUserId;
        this.parentId = parentId;
        this.comment = null;
        this.stamp = stamp;
    }
}
