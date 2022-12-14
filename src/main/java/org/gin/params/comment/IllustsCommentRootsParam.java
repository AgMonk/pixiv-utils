package org.gin.params.comment;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 查询绘画评论(根)参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:22
 */
@Data
public class IllustsCommentRootsParam {
    @JSONField(name = "illust_id")
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
