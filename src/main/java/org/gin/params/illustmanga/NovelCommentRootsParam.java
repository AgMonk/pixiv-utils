package org.gin.params.illustmanga;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 查询作品评论(根)参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:22
 */
@Data
public class NovelCommentRootsParam {
    @JSONField(name = "novel_id")
    Long nid;
    int offset;
    int limit;

    public NovelCommentRootsParam(Long nid, int page) {
        this(nid, page, 50);
    }

    public NovelCommentRootsParam(Long nid, int page, int size) {
        this.nid = nid;
        this.offset = (page - 1) * size;
        this.limit = size;
    }


}
