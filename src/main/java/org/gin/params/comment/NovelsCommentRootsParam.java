package org.gin.params.comment;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询小说评论(根)参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:22
 */
@Getter
@Setter
public class NovelsCommentRootsParam {
    @JsonProperty("novel_id")
    Long nid;
    int offset;
    int limit;

    public NovelsCommentRootsParam(Long nid, int page) {
        this(nid, page, 50);
    }

    public NovelsCommentRootsParam(Long nid, int page, int size) {
        this.nid = nid;
        this.offset = (page - 1) * size;
        this.limit = size;
    }


}
