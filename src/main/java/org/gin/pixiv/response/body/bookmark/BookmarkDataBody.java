package org.gin.pixiv.response.body.bookmark;

import lombok.Getter;
import lombok.Setter;
import org.gin.pixiv.response.body.common.BookmarkData;

/**
 * @author bx002
 */
@Getter
@Setter
public class BookmarkDataBody {
    BookmarkData bookmarkData;
    Long id;
    Boolean isBookmarkable;
}