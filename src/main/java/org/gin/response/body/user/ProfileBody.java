package org.gin.response.body.user;

import lombok.Data;
import org.gin.response.body.novel.NovelSeriesBody;
import org.gin.response.fields.MangaSeriesInfo;

import java.util.HashMap;
import java.util.List;

/**
 * 用户作品概况 body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 11:50
 **/
@Data
public class ProfileBody {
    /**
     * 插画信息
     */
    HashMap<Long, Object> illusts;
    /**
     * 漫画信息
     */
    HashMap<Long, Object> manga;
    /**
     * 小说系列信息
     */
    List<NovelSeriesBody> novelSeries;
    /**
     * 漫画系列信息
     */
    List<MangaSeriesInfo> mangaSeries;
    HashMap<Long, Object> novels;
    /**
     * 首页精选推荐
     */
    List<Object> pickup;

}
