package org.gin.params;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import org.gin.emuns.Stamp;

/**
 * 评论参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/17 14:23
 */
@Getter
public class CommentParam {
    final String type;
    @JSONField(name = "illust_id")
    final Long pid;
    @JSONField(name = "author_user_id")
    final Long authorUserId;
    /**
     * 评论到楼中楼
     */
    @JSONField(name = "parent_id")
    final Long parentId;
    final String comment;
    @JSONField(name = "stamp_id")
    final Integer stampId;


    /**
     * 文字评论
     * @param comment      评论内容
     * @param pid          作品id
     * @param authorUserId 作者id
     * @param parentId     父楼id
     * @since 2022/10/17 14:35
     */
    public CommentParam(String comment, Long pid, Long authorUserId, Long parentId) {
        this.type = "comment";
        this.pid = pid;
        this.authorUserId = authorUserId;
        this.parentId = parentId;
        this.comment = comment;
        this.stampId = null;
    }

    /**
     * 表情贴图评论
     * @param stamp        表情贴图
     * @param pid          作品id
     * @param authorUserId 作者id
     * @param parentId     父楼id
     * @since 2022/10/17 14:35
     */
    public CommentParam(Stamp stamp, Long pid, Long authorUserId, Long parentId) {
        this.type = "stamp";
        this.pid = pid;
        this.authorUserId = authorUserId;
        this.parentId = parentId;
        this.comment = null;
        this.stampId = stamp.getId();
    }
}
