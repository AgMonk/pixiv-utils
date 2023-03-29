package org.gin.response.body.novel;

import lombok.Getter;
import lombok.Setter;
import org.gin.response.fields.NovelSeriesContent;

import java.util.List;

/**
 * 小说系列的各篇基础信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/15 17:08
 */
@Getter
@Setter
public class NovelSeriesContentBody {
    List<NovelSeriesContent> seriesContents;
}
