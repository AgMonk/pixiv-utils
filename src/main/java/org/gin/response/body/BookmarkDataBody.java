package org.gin.response.body;

import lombok.Data;
import org.gin.response.fields.BookmarkData;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 17:16
 */
@Data
public class BookmarkDataBody {
    BookmarkData bookmarkData;
    Long id;
    Boolean isBookmarkable;
}
