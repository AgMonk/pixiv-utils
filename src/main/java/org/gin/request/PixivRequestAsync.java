package org.gin.request;

import okhttp3.FormBody;
import okhttp3.Request;
import org.gin.response.callback.BasePixivCallback;
import org.gin.response.callback.RpcGroupCallback;

/**
 * 异步请求方法
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/11 16:53
 **/

public class PixivRequestAsync {


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
        final Request request = PixivCommon.createPostRequest(cookie, Pixiv.URL_PHP_BOOKMARK_ADD, token, body);
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
        final Request request = PixivCommon.createPostRequest(cookie, Pixiv.URL_PHP_RPC_GROUP_SETTING, token, body);
        PixivCommon.CLIENT.newCall(request).enqueue(callback);
    }

}
