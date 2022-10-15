package org.gin.request;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.gin.response.RpcGroupResponse;
import org.gin.response.callback.BasePixivCallback;

import java.io.IOException;

/**
 * 同步请求方法
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 14:19
 **/
public class PixivRequestSync {

    private static ResponseBody getResponseBody(Request request) throws IOException {
        final Call call = PixivCommon.CLIENT.newCall(request);
        final Response response = call.execute();
        return BasePixivCallback.handle(call, response);
    }


    /**
     * 关注用户
     * @param userId   需要关注的用户id
     * @param restrict 是否公开。0 = 公开，1 = 非公开
     * @param cookie   cookie
     * @param token    x-csrf-token
     */
    public static void followUser(long userId,int restrict, String cookie, String token) throws IOException {
        final FormBody body = new FormBody.Builder()
                .add("mode", "add")
                .add("type", "user")
                .add("user_id", String.valueOf(userId))
                .add("restrict", String.valueOf(restrict))
                .add("format", "json")
                .build();
        final Request request = PixivCommon.createPostRequest(cookie, Pixiv.URL_PHP_BOOKMARK_ADD, token, body);
        getResponseBody(request);
    }

    /**
     * 取关用户
     * @param userId 需要取关的用户id
     * @param cookie cookie
     * @param token  x-csrf-token
     */
    public static RpcGroupResponse unFollowUser(long userId, String cookie, String token) throws IOException {
        final FormBody body = new FormBody.Builder()
                .add("mode", "del")
                .add("type", "bookuser")
                .add("id", String.valueOf(userId))
                .build();
        final Request request = PixivCommon.createPostRequest(cookie, Pixiv.URL_PHP_RPC_GROUP_SETTING, token, body);
        final ResponseBody responseBody = getResponseBody(request);
        return JSONObject.parseObject(responseBody.string(), RpcGroupResponse.class);
    }

}
