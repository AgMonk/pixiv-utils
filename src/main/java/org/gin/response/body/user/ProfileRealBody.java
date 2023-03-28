package org.gin.response.body.user;

import lombok.Getter;
import lombok.Setter;
import org.gin.response.body.novel.NovelSeriesBody;
import org.gin.response.fields.MangaSeriesInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用户作品概况 适配插画和漫画为空的情况
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 11:50
 **/
@Getter
@Setter
public class ProfileRealBody {
    /**
     * 插画信息
     */
    List<Long> illusts;
    /**
     * 漫画信息
     */
    List<Long> manga;
    /**
     * 漫画系列信息
     */
    List<MangaSeriesInfo> mangaSeries;
    /**
     * 小说系列信息
     */
    List<NovelSeriesBody> novelSeries;
    /**
     * 小说信息
     */
    List<Long> novels;
    /**
     * 首页精选推荐
     */
    List<Object> pickup;

    public ProfileRealBody(ProfileBody profileBody) {
        this.illusts = new ArrayList<>(profileBody.getIllusts().keySet());
        this.manga = new ArrayList<>(profileBody.getManga().keySet());
        this.novels = new ArrayList<>(profileBody.getNovels().keySet());
        this.mangaSeries = profileBody.getMangaSeries();
        this.novelSeries = profileBody.getNovelSeries();
        this.pickup = profileBody.getPickup();
        Collections.sort(this.illusts);
        Collections.sort(this.manga);
        Collections.sort(this.novels);
    }
}
