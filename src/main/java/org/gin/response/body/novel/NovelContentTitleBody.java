package org.gin.response.body.novel;

import lombok.Data;

/**
 * 小说系列的各篇标题BOdy
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/15 17:05
 */
@Data
public class NovelContentTitleBody {
    Boolean available;
    Long id;
    String title;
}
