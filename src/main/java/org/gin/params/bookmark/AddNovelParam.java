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
public class AddNovelParam implements Serializable {
    /**
     * 备注
     */
    String comment = "";
    @JsonProperty("novel_id")
    long nid;
    /**
     * 是否为非公开收藏
     */

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
