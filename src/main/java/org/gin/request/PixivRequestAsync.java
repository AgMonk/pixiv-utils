package org.gin.request;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.gin.callback.BasePixivCallback;
import org.gin.callback.DetailCallback;
import org.gin.callback.FollowLatestCallback;
import org.gin.params.PixivParamsBookmarksAdd;

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
        final Request request = PixivCommon.createGetRequest(cookie,String.format(PixivUrl.URL_ILLUST_DETAIL,pid));
        final Call call = PixivCommon.CLIENT.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 查询关注作者最新作品
     * @param page     页码
     * @param cookie   cookie
     * @param callback 响应处理
     */
    public static void followLatest(int page, String cookie, FollowLatestCallback callback) {
        final HttpUrl url = PixivUrl.followLatestUrl(page,"all","zh");
        final Request request = PixivCommon.createGetRequest(cookie,url);
        final Call call = PixivCommon.CLIENT.newCall(request);
        call.enqueue(callback);
    }

    public static void bmkAdd(PixivParamsBookmarksAdd param, String cookie, String token, BasePixivCallback callback){
        final RequestBody body = RequestBody.create(JSONObject.toJSONString(param), PixivCommon.MEDIA_TYPE_JSON);
        final Request request = PixivCommon.createPostRequest(cookie,PixivUrl.ADD_BOOKMARKS,token, body);
        final Call call = PixivCommon.CLIENT.newCall(request);
        call.enqueue(callback);
    }
}
