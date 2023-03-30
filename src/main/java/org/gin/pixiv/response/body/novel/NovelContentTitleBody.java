package org.gin.pixiv.response.body.novel;

import lombok.Getter;
import lombok.Setter;

/**
 * 小说系列的各篇标题Body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/15 17:05
 */
@Getter
@Setter
public class NovelContentTitleBody {
    Boolean available;
    Long id;
    String title;
}
