package org.gin.params.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询用户的收藏作品参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 12:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarksParam {
    /**
     * 语言
     */

    String tag;
    int offset = 0;
    int limit = 100;
    /**
     * `show`公开的，`hide`不公开的(仅自己)
     */
    String rest = "show";

    public BookmarksParam(int page) {
        this(page, 100);
    }

    public BookmarksParam(int page, int size) {
        this.limit = size;
        this.offset = (page - 1) * size;
    }

    public static BookmarksParam untagged() {
        final BookmarksParam param = new BookmarksParam(1);
        param.setTag("未分類");
        return param;
    }
}
