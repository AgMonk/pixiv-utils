package org.gin.request;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import okhttp3.HttpUrl;
import org.gin.params.PixivParamSearch;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

import static org.gin.request.PixivCommon.DOMAIN_AJAX;

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
     * php接口 bookmark_add
     */
    public static final String PHP_BOOKMARK_ADD = PixivCommon.DOMAIN + "bookmark_add.php";
    /**
     * php接口 rpc_group_setting
     */
    public static final String PHP_RPC_GROUP_SETTING = PixivCommon.DOMAIN + "rpc_group_setting.php";

    /**
     * 用户信息
     */
    public static final String USER_INFO = DOMAIN_AJAX + "user/%d";
    public static final String USER_PROFILE = DOMAIN_AJAX + "user/%d/profile/all";
    public static final String USER_ILLUST = DOMAIN_AJAX + "user/%d/profile/illusts";


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

    /**
     * 生成搜索URL
     * @param param 参数
     * @return URL
     */
    public static HttpUrl searchUrl(String keywords, int page, PixivParamSearch param) {
        final HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(String.format(PixivUrl.SEARCH_ARTWORKS, keywords))).newBuilder();
        builder.addQueryParameter("p", String.valueOf(page));
        addQueryParameter(builder, param);
        return builder.build();
    }

    public static void addQueryParameter(HttpUrl.Builder builder, Object object) {
        final HashMap<String, String> json = JSONObject.parseObject(JSONObject.toJSONString(object), new TypeReference<>() {
        });
        json.forEach(builder::addQueryParameter);
    }

    public static HttpUrl userInfoUrl(long userId, boolean fullInfo, String lang) {
        final HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(String.format(PixivUrl.USER_INFO, userId))).newBuilder();
        builder.addQueryParameter("lang", lang);
        if (fullInfo) {
            builder.addQueryParameter("full", "1");
        }
        return builder.build();
    }

    public static HttpUrl userProfileUrl(long userId, String lang) {
        final HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(String.format(PixivUrl.USER_PROFILE, userId))).newBuilder();
        builder.addQueryParameter("lang", lang);
        return builder.build();
    }

    /**
     * 生成用户作品url
     * @param userId        用户id
     * @param ids 需要查询的作品pid
     * @param lang 预言
     * @return url
     */
    public static HttpUrl userIllustUrl(long userId, Collection<Long> ids, String lang) {
        final HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(String.format(PixivUrl.USER_ILLUST, userId))).newBuilder();
        builder.addQueryParameter("lang", lang);
        builder.addQueryParameter("work_category", "illustManga");
        builder.addQueryParameter("is_first_page", "1");
        for (Long id : ids) {
            builder.addQueryParameter("ids[]", String.valueOf(id));
        }
        return builder.build();
    }
}
