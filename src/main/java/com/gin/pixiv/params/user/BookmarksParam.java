package com.gin.pixiv.params.user;

import com.gin.pixiv.enums.PixivRest;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

/**
 * 查询用户的收藏作品参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 12:14
 */
@Getter
@Setter
public class BookmarksParam {
    final int offset;
    final int limit;
    final PixivRest rest;
    String tag;

    public BookmarksParam(int page) {
        this(page, 60);
    }

    public BookmarksParam(int page, int size) {
        this(page, size, PixivRest.show);
    }

    public BookmarksParam(int page, int size, @NotNull PixivRest rest) {
        this(page, size, rest, null);
    }

    public BookmarksParam(int page, int size, @NotNull PixivRest rest, String tag) {
        this.limit = size;
        this.offset = (page - 1) * size;
        this.rest = rest;
        this.tag = tag;
    }

    public BookmarksParam untagged() {
        this.tag = "未分類";
        return this;
    }
}
