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
public class AddIllustMangaParam implements Serializable {
    @JSONField(name = "illust_id")
    long pid;
    /**
     * 是否为非公开收藏
     */
    @JSONField(serializeUsing = PixivRestrictSerializer.class)
    PixivRestrict restrict = PixivRestrict.TRUE;
    /**
     * 备注
     */
    String comment = "";
    /**
     * 标签
     */
    List<String> tags = new ArrayList<>();

    public AddIllustMangaParam(long pid, List<String> tags) {
        this.pid = pid;
        this.tags = tags;
    }

    public AddIllustMangaParam(long pid) {
        this.pid = pid;
    }
}
