package org.gin.request;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import okhttp3.*;
import org.gin.params.PixivParamsBookmarksAdd;
import org.gin.response.PixivResponse;
import org.gin.response.RpcGroupResponse;
import org.gin.response.body.BookmarkAddBody;
import org.gin.response.callback.BasePixivCallback;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

/**
 * 同步请求方法
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 14:19
 **/
public class PixivRequestSync {

    /**
     * 添加收藏
     * @param param  参数
     * @param cookie cookie
     * @param token  x-csrf-token
     */
    public static PixivResponse<BookmarkAddBody> bmkAdd(PixivParamsBookmarksAdd param, String cookie, String token) throws IOException {
        final RequestBody body = PixivCommon.createJsonBody(param);
        final Request request = PixivCommon.createPostRequest(cookie, PixivUrl.URL_BOOKMARKS_ADD, token, body);
        final ResponseBody responseBody = getResponseBody(request);
        return JSONObject.parseObject(responseBody.string(), new TypeReference<>() {
        });
    }

    /**
     * 删除收藏
     * @param bookmarkId 收藏id
     * @param cookie     cookie
     * @param token      x-csrf-token
     */
    public static PixivResponse<JSONObject> bmkDel(long bookmarkId, String cookie, String token) throws IOException {
        final FormBody body = new FormBody.Builder().add("bookmark_id", String.valueOf(bookmarkId)).build();
        final Request request = PixivCommon.createPostRequest(cookie, PixivUrl.URL_BOOKMARKS_DEL, token, body);
        final ResponseBody responseBody = getResponseBody(request);
        return JSONObject.parseObject(responseBody.string(), new TypeReference<>() {
        });
    }

    private static ResponseBody getResponseBody(Request request) throws IOException {
        final Call call = PixivCommon.CLIENT.newCall(request);
        final Response response = call.execute();
        return BasePixivCallback.handle(call, response);
    }

    /**
     * 批量删除收藏
     * @param bookmarkIds 收藏id
     * @param cookie      cookie
     * @param token       x-csrf-token
     */
    public static PixivResponse<JSONObject> bmkDel(Collection<Long> bookmarkIds, String cookie, String token) throws IOException {
        final HashMap<String, Collection<Long>> map = new HashMap<>(1){{put("bookmarkIds", bookmarkIds);}};
        final RequestBody body = PixivCommon.createJsonBody(map);
        final Request request = PixivCommon.createPostRequest(cookie, PixivUrl.URL_BOOKMARKS_REMOVE, token, body);
        final ResponseBody responseBody = getResponseBody(request);
        return JSONObject.parseObject(responseBody.string(), new TypeReference<>() {
        });
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
                .add("mode","add")
                .add("type","user")
                .add("user_id", String.valueOf(userId))
                .add("restrict", String.valueOf(restrict))
                .add("format","json")
                .build();
        final Request request = PixivCommon.createPostRequest(cookie,PixivUrl.URL_PHP_BOOKMARK_ADD,token, body);
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
                .add("mode","del")
                .add("type","bookuser")
                .add("id", String.valueOf(userId))
                .build();
        final Request request = PixivCommon.createPostRequest(cookie,PixivUrl.URL_PHP_RPC_GROUP_SETTING,token, body);
        final ResponseBody responseBody = getResponseBody(request);
        return JSONObject.parseObject(responseBody.string(), RpcGroupResponse.class);
    }

}
