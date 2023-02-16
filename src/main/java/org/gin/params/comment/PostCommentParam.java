package org.gin.params.comment;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.gin.emuns.PixivCommentType;
import org.gin.emuns.PixivStamp;

/**
 * 评论参数(相同部分)
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/17 14:23
 */
@Getter
public class PostCommentParam {
    final PixivCommentType type;

    @JsonProperty("author_user_id")
    final Long authorUserId;
    /**
     * 评论到楼中楼
     */
    @JsonProperty("parent_id")
    final Long parentId;
    final String comment;
    @JsonProperty("stamp_id")
    final PixivStamp stamp;

    /**
     * 文字评论
     * @param authorUserId 作品作者id
     * @param parentId     如果是楼中楼，添加该项
     * @param comment      评论内容
     */
    private PostCommentParam(Long authorUserId, Long parentId, String comment) {
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
    private PostCommentParam(Long authorUserId, Long parentId, PixivStamp stamp) {
        this.type = PixivCommentType.stamp;
        this.authorUserId = authorUserId;
        this.parentId = parentId;
        this.comment = null;
        this.stamp = stamp;
    }

    /**
     * 绘画评论参数
     * @author : ginstone
     * @version : v1.0.0
     * @since : 2022/11/17 13:29
     */
    @Getter
    @Setter
    public static class Illust extends PostCommentParam {
        @JsonProperty("illust_id")
        final Long pid;

        /**
         * 文字评论
         * @param authorUserId 作品作者id
         * @param parentId     如果是楼中楼，添加该项
         * @param comment      评论内容
         */
        public Illust(Long pid, Long authorUserId, Long parentId, String comment) {
            super(authorUserId, parentId, comment);
            this.pid = pid;
        }

        /**
         * 表情评论
         * @param authorUserId 作品作者id
         * @param parentId     如果是楼中楼，添加该项
         * @param stamp        表情贴图
         */
        public Illust(Long pid, Long authorUserId, Long parentId, PixivStamp stamp) {
            super(authorUserId, parentId, stamp);
            this.pid = pid;
        }
    }


    /**
     * 小说评论参数
     * @author : ginstone
     * @version : v1.0.0
     * @since : 2022/11/17 13:42
     */
    @Getter
    @Setter
    public static class Novel extends PostCommentParam {
        @JsonProperty("novel_id")
        final Long nid;

        /**
         * 文字评论
         * @param authorUserId 作品作者id
         * @param parentId     如果是楼中楼，添加该项
         * @param comment      评论内容
         */
        public Novel(Long nid, Long authorUserId, Long parentId, String comment) {
            super(authorUserId, parentId, comment);
            this.nid = nid;
        }

        /**
         * 表情评论
         * @param authorUserId 作品作者id
         * @param parentId     如果是楼中楼，添加该项
         * @param stamp        表情贴图
         */
        public Novel(Long nid, Long authorUserId, Long parentId, PixivStamp stamp) {
            super(authorUserId, parentId, stamp);
            this.nid = nid;
        }
    }
}
