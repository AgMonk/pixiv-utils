package org.gin.request;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.gin.params.PixivParamsBookmarksAdd;
import org.gin.response.callback.BasePixivCallback;
import org.gin.response.callback.BookmarkAddCallback;
import org.gin.response.callback.RpcGroupCallback;

import java.util.Collection;
import java.util.HashMap;

/**
 * 异步请求方法
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/11 16:53
 **/

public class PixivRequestAsync {


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

}
