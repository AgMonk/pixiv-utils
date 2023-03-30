package org.gin.pixiv.params.comment;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询绘画评论(根)参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:22
 */
@Getter
@Setter
public class IllustsCommentRootsParam {
    @JsonProperty("illust_id")
    Long pid;
    int offset;
    int limit;

    public IllustsCommentRootsParam(Long pid, int page) {
        this(pid, page, 50);
    }

    public IllustsCommentRootsParam(Long pid, int page, int size) {
        this.pid = pid;
        this.offset = (page - 1) * size;
        this.limit = size;
    }


}
