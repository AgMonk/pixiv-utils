package org.gin.request;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.gin.params.PixivParamSearch;
import org.gin.params.PixivParamsBookmarksAdd;
import org.gin.response.callback.*;

import java.util.Collection;
import java.util.HashMap;

/**
 * 异步请求方法
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/11 16:53
 **/
@SuppressWarnings("unused")
public class PixivRequestAsync {
    /**
     * 查询作品详情(异步)
     * @param pid      pid
     * @param cookie   cookie
     * @param callback 响应处理
     */
    public static void detail(long pid, String cookie, DetailCallback callback) {
        final Request request = PixivCommon.createGetRequest(cookie, String.format(PixivUrl.URL_ARTWORK_DETAIL, pid));
        PixivCommon.CLIENT.newCall(request).enqueue(callback);
    }

    /**
     * 查询关注作者最新作品
     * @param page     页码
     * @param cookie   cookie
     * @param callback 响应处理
     */
    public static void followLatest(int page, String cookie, FollowLatestCallback callback) {
        final HttpUrl url = PixivUrl.followLatestUrl(page, "all", "zh");
        final Request request = PixivCommon.createGetRequest(cookie, url);
        PixivCommon.CLIENT.newCall(request).enqueue(callback);
    }

    /**
     * 添加收藏
     * @param param    参数
     * @param cookie   cookie
     * @param token    x-csrf-token
     * @param callback 响应处理
     */
    public static void bmkAdd(PixivParamsBookmarksAdd param, String cookie, String token, BookmarkAddCallback callback) {
        final RequestBody body = PixivCommon.createJsonBody(param);
        final Request request = PixivCommon.createPostRequest(cookie, PixivUrl.URL_BOOKMARKS_ADD, token, body);
        PixivCommon.CLIENT.newCall(request).enqueue(callback);
    }

    /**
     * 删除收藏
     * @param bookmarkId 收藏id
     * @param cookie     cookie
     * @param token      x-csrf-token
     * @param callback   响应处理
     */
    public static void bmkDel(long bookmarkId, String cookie, String token, BasePixivCallback callback) {
        final FormBody body = new FormBody.Builder().add("bookmark_id", String.valueOf(bookmarkId)).build();
        final Request request = PixivCommon.createPostRequest(cookie, PixivUrl.URL_BOOKMARKS_DEL, token, body);
        PixivCommon.CLIENT.newCall(request).enqueue(callback);
    }

    /**
     * 批量删除收藏
     * @param bookmarkIds 收藏id
     * @param cookie      cookie
     * @param token       x-csrf-token
     * @param callback    响应处理
     */
    public static void bmkDel(Collection<Long> bookmarkIds, String cookie, String token, BasePixivCallback callback) {
        final HashMap<String, Collection<Long>> map = new HashMap<>(1) {{
            put("bookmarkIds", bookmarkIds);
        }};
        final RequestBody body = PixivCommon.createJsonBody(map);
        final Request request = PixivCommon.createPostRequest(cookie, PixivUrl.URL_BOOKMARKS_REMOVE, token, body);
        PixivCommon.CLIENT.newCall(request).enqueue(callback);
    }

    /**
     * 搜索作品
     * @param keywords 关键字
     * @param page     页码
     * @param param    其他参数
     * @param cookie   cookie
     * @param callback 响应处理
     */
    public static void search(String keywords, int page, PixivParamSearch param, String cookie, SearchCallback callback) {
        final HttpUrl url = PixivUrl.searchUrl(keywords, page, param);
        final Request request = PixivCommon.createGetRequest(cookie, url);
        PixivCommon.CLIENT.newCall(request).enqueue(callback);
    }

    /**
     * 关注用户
     * @param userId   需要关注的用户id
     * @param restrict 是否公开。0 = 公开，1 = 非公开
     * @param cookie   cookie
     * @param token    x-csrf-token
     * @param callback 响应处理
     */
    public static void followUser(long userId, int restrict, String cookie, String token, BasePixivCallback callback) {
        final FormBody body = new FormBody.Builder()
                .add("mode", "add")
                .add("type", "user")
                .add("user_id", String.valueOf(userId))
                .add("restrict", String.valueOf(restrict))
                .add("format", "json")
                .build();
        final Request request = PixivCommon.createPostRequest(cookie, PixivUrl.URL_PHP_BOOKMARK_ADD, token, body);
        PixivCommon.CLIENT.newCall(request).enqueue(callback);
    }

    /**
     * 取关用户
     * @param userId   需要取关的用户id
     * @param cookie   cookie
     * @param token    x-csrf-token
     * @param callback 响应处理
     */
    public static void unFollowUser(long userId, String cookie, String token, RpcGroupCallback callback) {
        final FormBody body = new FormBody.Builder()
                .add("mode", "del")
                .add("type", "bookuser")
                .add("id", String.valueOf(userId))
                .build();
        final Request request = PixivCommon.createPostRequest(cookie, PixivUrl.URL_PHP_RPC_GROUP_SETTING, token, body);
        PixivCommon.CLIENT.newCall(request).enqueue(callback);
    }

    /**
     * 获取用户信息
     * @param userId   用户id
     * @param cookie   cookie
     * @param fullInfo 是否需要获取完整信息
     * @param callback 响应处理
     */
    public static void userInfo(long userId, String cookie, boolean fullInfo, UserInfoCallback callback) {
        final HttpUrl url = PixivUrl.userInfoUrl(userId, fullInfo, "zh");
        final Request request = PixivCommon.createGetRequest(cookie, url);
        PixivCommon.CLIENT.newCall(request).enqueue(callback);
    }

    /**
     * 获取用户作品概况
     * @param userId   用户id
     * @param cookie   cookie
     * @param callback 响应处理
     */
    public static void userProfile(long userId, String cookie, UserProfileCallback callback) {
        final HttpUrl url = PixivUrl.userProfileUrl(userId, "zh");
        final Request request = PixivCommon.createGetRequest(cookie, url);
        PixivCommon.CLIENT.newCall(request).enqueue(callback);
    }

    /**
     * 查询用户的作品信息
     * @param userId   用户id
     * @param ids      需要查询的作品id
     * @param cookie   cookie
     * @param callback 响应处理
     */
    public static void userIllusts(long userId, Collection<Long> ids, String cookie, UserIllustCallback callback) {
        final HttpUrl url = PixivUrl.userIllustUrl(userId, ids, "zh");
        final Request request = PixivCommon.createGetRequest(cookie, url);
        PixivCommon.CLIENT.newCall(request).enqueue(callback);
    }

    /**
     * 查询用户的收藏作品
     * @param userId 用户id
     * @param page   页码
     * @param size   每页数量 最大100
     * @param tag    标签
     * @param rest   `show`公开的，`hide`不公开的(仅自己)
     * @param cookie cookie
     * @param callback 响应处理
     */
    public static void userBookmarks(long userId, int page, int size, String tag, String rest, String cookie, UserBookmarkCallback callback) {
        final HttpUrl url = PixivUrl.userBookmarks(userId, page, size, tag, rest, "zh");
        final Request request = PixivCommon.createGetRequest(cookie, url);
        PixivCommon.CLIENT.newCall(request).enqueue(callback);
    }
    /**
     * 查询用户的收藏作品中使用的标签
     * @param userId 用户id
     * @param cookie cookie
     * @param callback 响应处理
     */
    public static void userBookmarkTags(long userId,  String cookie, UserBookmarkTagsCallback callback) {
        final HttpUrl url = PixivUrl.userBookmarkTags(userId, "zh");
        final Request request = PixivCommon.createGetRequest(cookie, url);
        PixivCommon.CLIENT.newCall(request).enqueue(callback);
    }
}
