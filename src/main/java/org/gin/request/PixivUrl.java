package org.gin.request;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import okhttp3.HttpUrl;
import org.gin.params.PixivParamSearch;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/11 17:27
 **/
public class PixivUrl {
    public static final String DOMAIN = "https://www.pixiv.net/";
    public static final String DOMAIN_AJAX = DOMAIN + "ajax/";
    /**
     * 作品详情接口 cookie可选
     */
    public static final String URL_ARTWORK_DETAIL = DOMAIN_AJAX + "illust/%d";
    /**
     * 搜索作品
     */
    public static final String URL_ARTWORK_SEARCH = DOMAIN_AJAX + "search/artworks/%s";
    /**
     * 添加收藏 需要cookie、x-csrf-token
     * body传参
     */
    public static final String URL_BOOKMARKS_ADD = DOMAIN_AJAX + "illusts/bookmarks/add";
    /**
     * 移除收藏 需要cookie x-csrf-token
     * 表单传参
     */
    public static final String URL_BOOKMARKS_DEL = DOMAIN_AJAX + "illusts/bookmarks/delete";
    /**
     * 批量移除收藏 需要cookie x-csrf-token
     * 表单传参
     */
    public static final String URL_BOOKMARKS_REMOVE = DOMAIN_AJAX + "illusts/bookmarks/remove";
    /**
     * 查询关注作者最新作品的URL 需要cookie
     */
    public static final String URL_FOLLOW_LATEST = DOMAIN_AJAX + "follow_latest/illust";
    /**
     * 获取收藏作品接口 需要cookie
     */
    public static final String URL_USER_BOOKMARKS = DOMAIN_AJAX + "user/%d/illusts/bookmarks";
    /**
     * 查询用户发出约稿的作品
     */
    public static final String URL_USER_COMMISSION = DOMAIN_AJAX + "commission/page/users/%d/request/sent";
    /**
     * 查询用户的收藏作品中使用的标签
     */
    public static final String URL_USER_BOOKMARKS_TAGS = DOMAIN_AJAX + "user/%d/illusts/bookmark/tags";
    public static final String URL_USER_ILLUST = DOMAIN_AJAX + "user/%d/profile/illusts";
    /**
     * 用户信息
     */
    public static final String URL_USER_INFO = DOMAIN_AJAX + "user/%d";
    public static final String URL_USER_PROFILE = DOMAIN_AJAX + "user/%d/profile/all";
    /**
     * php接口 bookmark_add
     */
    public static final String URL_PHP_BOOKMARK_ADD = DOMAIN + "bookmark_add.php";
    /**
     * php接口 rpc_group_setting
     */
    public static final String URL_PHP_RPC_GROUP_SETTING = DOMAIN + "rpc_group_setting.php";

    /**
     * 生成查询关注作者最新作品的URL
     * @param page 页码
     * @param mode 模式
     * @param lang 语言
     * @return URL
     */
    public static HttpUrl followLatestUrl(int page, String mode, String lang) {
        return Objects.requireNonNull(HttpUrl.parse(PixivUrl.URL_FOLLOW_LATEST)).newBuilder().addQueryParameter("mode", mode).addQueryParameter("lang", lang).addQueryParameter("p", String.valueOf(page)).build();
    }

    /**
     * 生成搜索URL
     * @param param 参数
     * @return URL
     */
    public static HttpUrl searchUrl(String keywords, int page, PixivParamSearch param) {
        final HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(String.format(PixivUrl.URL_ARTWORK_SEARCH, keywords))).newBuilder();
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
        final HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(String.format(PixivUrl.URL_USER_INFO, userId))).newBuilder();
        builder.addQueryParameter("lang", lang);
        if (fullInfo) {
            builder.addQueryParameter("full", "1");
        }
        return builder.build();
    }

    public static HttpUrl userProfileUrl(long userId, String lang) {
        final HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(String.format(PixivUrl.URL_USER_PROFILE, userId))).newBuilder();
        builder.addQueryParameter("lang", lang);
        return builder.build();
    }

    /**
     * 生成用户作品url
     * @param userId 用户id
     * @param ids    需要查询的作品pid
     * @param lang   预言
     * @return url
     */
    public static HttpUrl userIllustUrl(long userId, Collection<Long> ids, String lang) {
        final HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(String.format(PixivUrl.URL_USER_ILLUST, userId))).newBuilder();
        builder.addQueryParameter("lang", lang);
        builder.addQueryParameter("work_category", "illustManga");
        builder.addQueryParameter("is_first_page", "1");
        for (Long id : ids) {
            builder.addQueryParameter("ids[]", String.valueOf(id));
        }
        return builder.build();
    }

    /**
     * 查询用户的收藏作品
     * @param userId 用户id
     * @param page   页码
     * @param size   每页数量 最大100
     * @param tag    标签
     * @param rest   `show`公开的，`hide`不公开的(仅自己)
     * @param lang   语言，简中为 `zh`
     * @return url
     */
    public static HttpUrl userBookmarks(long userId, int page, int size, String tag, String rest, String lang) {
        final HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(String.format(PixivUrl.URL_USER_BOOKMARKS, userId))).newBuilder();
        builder.addQueryParameter("lang", lang);
        builder.addQueryParameter("tag", tag == null ? "" : tag);
        builder.addQueryParameter("offset", String.valueOf((page - 1) * size));
        builder.addQueryParameter("limit", String.valueOf(Math.min(size, 100)));
        builder.addQueryParameter("rest", rest);
        return builder.build();
    }

    /**
     * 查询用户的收藏作品中使用的标签
     * @param userId 用户id
     * @param lang   语言，简中为 `zh`
     * @return url
     */
    public static HttpUrl userBookmarkTags(long userId, String lang) {
        final HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(String.format(PixivUrl.URL_USER_BOOKMARKS_TAGS, userId))).newBuilder();
        builder.addQueryParameter("lang", lang);
        return builder.build();
    }

   /**
    *  查询用户发出约稿的作品
    * @param userId 用户id
    * @param lang 语言 
    * @return okhttp3.HttpUrl
    * @author bx002
    */
    public static HttpUrl userCommissionRequestSent(long userId, String lang) {
        final HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(String.format(PixivUrl.URL_USER_COMMISSION, userId))).newBuilder();
        builder.addQueryParameter("lang", lang);
        return builder.build();
    }

}
