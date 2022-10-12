package org.gin.request;

import okhttp3.HttpUrl;

import java.util.Objects;

import static org.gin.request.PixivCommon.*;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/11 17:27
 **/
public class PixivUrl {
    /**
     * 作品详情接口 cookie可选
     */
    public static final String URL_ILLUST_DETAIL = DOMAIN_AJAX + "illust/%d";
    /**
     * 查询关注作者最新作品的URL 需要cookie
     */
    public static final String URL_FOLLOW_LATEST = DOMAIN_AJAX + "follow_latest/illust";
    /**
     * 获取收藏作品接口 需要cookie
     */
    public static final String URL_BOOKMARKS_GET = DOMAIN_AJAX + "user/%d/illusts/bookmarks";
    /**
     * 添加收藏 需要cookie、x-csrf-token
     * body传参
     */
    public static final String ADD_BOOKMARKS = DOMAIN_AJAX + "illusts/bookmarks/add";
    /**
     * 移除收藏 需要cookie x-csrf-token
     * 表单传参
     */
    public static final String DEL_BOOKMARKS = DOMAIN_AJAX + "illusts/bookmarks/delete";
    /**
     * 批量移除收藏 需要cookie x-csrf-token
     * 表单传参
     */
    public static final String REMOVE_BOOKMARKS = DOMAIN_AJAX + "illusts/bookmarks/remove";

    /**
     * 搜索作品
     */
    public static final String SEARCH_ARTWORKS = DOMAIN_AJAX + "search/artworks/%s";


    /**
     * 生成查询关注作者最新作品的URL
     * @param page 页码
     * @param mode 模式
     * @param lang 语言
     * @return URL
     */
    public static HttpUrl followLatestUrl(int page, String mode, String lang) {
        return Objects.requireNonNull(HttpUrl.parse(PixivUrl.URL_FOLLOW_LATEST)).newBuilder()
                .addQueryParameter("mode", mode)
                .addQueryParameter("lang", lang)
                .addQueryParameter("p", String.valueOf(page))
                .build();
    }
}
