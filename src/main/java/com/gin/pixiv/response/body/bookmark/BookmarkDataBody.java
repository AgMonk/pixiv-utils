package com.gin.pixiv.response.body.bookmark;

import com.gin.pixiv.response.body.common.BookmarkData;
import lombok.Getter;
import lombok.Setter;

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