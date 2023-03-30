package com.gin.pixiv.params.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.pixiv.enums.PixivRestrict;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class AddIllustParam implements Serializable {
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

    public AddIllustParam(long pid, List<String> tags) {
        this.pid = pid;
        this.tags = tags;
    }

    public AddIllustParam(long pid) {
        this.pid = pid;
    }
}
