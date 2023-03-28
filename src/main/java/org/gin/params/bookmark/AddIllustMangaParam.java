package org.gin.params.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gin.emuns.PixivRestrict;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bx002
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddIllustMangaParam implements Serializable {
    @JsonProperty(value = "illust_id")
    long pid;
    /**
     * 是否为非公开收藏
     */
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
