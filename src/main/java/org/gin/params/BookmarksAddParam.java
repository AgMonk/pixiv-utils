package org.gin.params;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bx002
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmarksAddParam implements Serializable {
    @JSONField(name = "illust_id")
    long pid;
    /**
     * 是否为非公开收藏
     */
    int restrict = 0;
    /**
     * 备注
     */
    String comment = "";
    /**
     * 标签
     */
    List<String> tags = new ArrayList<>();

    public BookmarksAddParam(long pid, List<String> tags) {
        this.pid = pid;
        this.tags = tags;
    }

    public BookmarksAddParam(long pid) {
        this.pid = pid;
    }
}
