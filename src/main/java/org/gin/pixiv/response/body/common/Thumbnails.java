package org.gin.pixiv.response.body.common;

import lombok.Getter;
import lombok.Setter;
import org.gin.pixiv.response.body.illust.IllustBodySimple;
import org.gin.pixiv.response.body.novel.NovelBodySimple;

import java.util.List;

/**
 * 快照
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 16:44
 */
@Getter
@Setter
public class Thumbnails {
    List<IllustBodySimple> illust;

    List<NovelBodySimple> novel;

    //todo novelDraft
    //todo novelSeries
}
