package org.gin.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.gin.response.body.BookmarkAddBody;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/17 10:05
 */
@Getter
@Setter
@RequiredArgsConstructor
public class BookmarkAddRes extends PixivResponse<BookmarkAddBody> {

}   
