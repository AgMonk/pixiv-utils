package org.gin.params.bookmark;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gin.emuns.PixivRestrict;
import org.gin.emuns.PixivRestrictSerializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bx002
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNovelParam implements Serializable {
    /**
     * 备注
     */
    String comment = "";
    @JSONField(name = "novel_id")
    long nid;
    /**
     * 是否为非公开收藏
     */
    @JSONField(serializeUsing = PixivRestrictSerializer.class)
    PixivRestrict restrict = PixivRestrict.TRUE;
    /**
     * 标签
     */
    List<String> tags = new ArrayList<>();

    public AddNovelParam(long nid, List<String> tags) {
        this.nid = nid;
        this.tags = tags;
    }

    public AddNovelParam(long nid) {
        this.nid = nid;
    }
}
