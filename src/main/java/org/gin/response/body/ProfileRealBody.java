package org.gin.response.body;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.gin.response.fields.MangaSeriesInfo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户作品概况 适配插画和漫画为空的情况
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 11:50
 **/
@Data
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
     * 首页精选推荐
     */
    List<Object> pickup;

    public ProfileRealBody(ProfileBody profileBody) {
        final Object ill = profileBody.getIllusts();
        if (ill instanceof JSONObject) {
            this.illusts = ((JSONObject) ill).keySet().stream().map(Long::parseLong).sorted().collect(Collectors.toList());
        }

        final Object man = profileBody.getManga();
        if (man instanceof JSONObject) {
            this.manga = ((JSONObject) man).keySet().stream().map(Long::parseLong).sorted().collect(Collectors.toList());
        }
        this.mangaSeries = profileBody.getMangaSeries();
        this.pickup = profileBody.getPickup();
    }
}
