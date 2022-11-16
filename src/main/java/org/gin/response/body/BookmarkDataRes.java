package org.gin.response.body;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.gin.response.PixivResponse;
import org.gin.response.fields.BookmarkData;


/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 17:16
 */
@Getter
@Setter
public class BookmarkDataRes extends PixivResponse<BookmarkDataRes.BookmarkDataBody> {
    @Data
    public static class BookmarkDataBody {
        BookmarkData bookmarkData;
        Long id;
        Boolean isBookmarkable;
    }
}
