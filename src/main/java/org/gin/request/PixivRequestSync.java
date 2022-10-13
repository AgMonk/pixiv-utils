package org.gin.request;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import okhttp3.*;
import org.gin.callback.BasePixivCallback;
import org.gin.params.PixivParamSearch;
import org.gin.params.PixivParamsBookmarksAdd;
import org.gin.response.PixivResponse;
import org.gin.response.RpcGroupResponse;
import org.gin.response.body.*;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

/**
 * 同步请求方法
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 14:19
 **/
@SuppressWarnings("unused")
public class PixivRequestSync {

    /**
     * 查询作品详情(同步)
     * @param pid    pid
     * @param cookie cookie
     */
    @SuppressWarnings("UnusedReturnValue")
    public static PixivResponse<ArtworkBody> detail(long pid, String cookie) throws IOException {
        final Request request = PixivCommon.createGetRequest(cookie, String.format(PixivUrl.URL_ILLUST_DETAIL, pid));
        final ResponseBody responseBody = getResponseBody(request);
        return JSONObject.parseObject(responseBody.string(), new TypeReference<>() {
        });
    }

    /**
     * 查询关注作者最新作品
     * @param page   页码
     * @param cookie cookie
     */
    public static PixivResponse<FollowLatestBody> followLatest(int page, String cookie) throws IOException {
        final HttpUrl url = PixivUrl.followLatestUrl(page, "all", "zh");
        final Request request = PixivCommon.createGetRequest(cookie, url);
        final ResponseBody responseBody = getResponseBody(request);
        return JSONObject.parseObject(responseBody.string(), new TypeReference<>() {
        });
    }

    /**
     * 添加收藏
     * @param param  参数
     * @param cookie cookie
     * @param token  x-csrf-token
     */
    public static PixivResponse<BookmarkAddBody> bmkAdd(PixivParamsBookmarksAdd param, String cookie, String token) throws IOException {
        final RequestBody body = PixivCommon.createJsonBody(param);
        final Request request = PixivCommon.createPostRequest(cookie, PixivUrl.ADD_BOOKMARKS, token, body);
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
        final Request request = PixivCommon.createPostRequest(cookie, PixivUrl.DEL_BOOKMARKS, token, body);
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
        final Request request = PixivCommon.createPostRequest(cookie, PixivUrl.REMOVE_BOOKMARKS, token, body);
        final ResponseBody responseBody = getResponseBody(request);
        return JSONObject.parseObject(responseBody.string(), new TypeReference<>() {
        });
    }

    /**
     * 搜索作品
     * @param keywords 关键字
     * @param page     页码
     * @param param    其他参数
     * @param cookie   cookie
     */
    public static  PixivResponse<SearchBody> search(String keywords, int page, PixivParamSearch param, String cookie) throws IOException {
        final HttpUrl url = PixivUrl.searchUrl(keywords,page,param);
        final Request request = PixivCommon.createGetRequest(cookie,url);
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
        final Request request = PixivCommon.createPostRequest(cookie,PixivUrl.PHP_BOOKMARK_ADD,token, body);
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
        final Request request = PixivCommon.createPostRequest(cookie,PixivUrl.PHP_RPC_GROUP_SETTING,token, body);
        final ResponseBody responseBody = getResponseBody(request);
        return JSONObject.parseObject(responseBody.string(), RpcGroupResponse.class);
    }

    /**
     * 获取用户信息
     * @param userId 用户id
     * @param cookie cookie
     * @param fullInfo 是否需要获取完整信息
     */
    public static PixivResponse<UserInfoBody> userInfo(long userId, String cookie, boolean fullInfo) throws IOException {
        final HttpUrl url = PixivUrl.userInfoUrl(userId,fullInfo,"zh");
        final Request request = PixivCommon.createGetRequest(cookie,url);
        final ResponseBody responseBody = getResponseBody(request);
        return JSONObject.parseObject(responseBody.string(), new TypeReference<>() {
        });
    }

    /**
     * 获取用户作品概况
     * @param userId 用户id
     * @param cookie cookie
     */
    public static PixivResponse<ProfileBody> userProfile(long userId, String cookie) throws IOException {
        final HttpUrl url = PixivUrl.userProfileUrl(userId,"zh");
        final Request request = PixivCommon.createGetRequest(cookie,url);
        final ResponseBody responseBody = getResponseBody(request);
        return JSONObject.parseObject(responseBody.string(), new TypeReference<>() {
        });
    }

    /**
     * 查询用户的作品信息
     * @param userId 用户id
     * @param ids  需要查询的作品id
     * @param cookie cookie
     */
    public static PixivResponse<UserIllustBody> userIllusts(long userId, Collection<Long> ids, String cookie) throws IOException {
        final HttpUrl url = PixivUrl.userIllustUrl(userId,ids,"zh");
        final Request request = PixivCommon.createGetRequest(cookie,url);
        final ResponseBody responseBody = getResponseBody(request);
        return JSONObject.parseObject(responseBody.string(), new TypeReference<>() {
        });
    }
}
