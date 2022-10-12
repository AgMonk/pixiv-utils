package org.gin.request;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.gin.callback.BasePixivCallback;
import org.gin.callback.BookmarkAddCallback;
import org.gin.callback.DetailCallback;
import org.gin.callback.FollowLatestCallback;
import org.gin.params.PixivParamsBookmarksAdd;

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

    /**
     * 添加收藏
     * @param param 参数
     * @param cookie cookie
     * @param token x-csrf-token
     * @param callback 响应处理
     */
    public static void bmkAdd(PixivParamsBookmarksAdd param, String cookie, String token, BookmarkAddCallback callback){
        final RequestBody body = RequestBody.create(JSONObject.toJSONString(param), PixivCommon.MEDIA_TYPE_JSON);
        final Request request = PixivCommon.createPostRequest(cookie,PixivUrl.ADD_BOOKMARKS,token, body);
        final Call call = PixivCommon.CLIENT.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 删除收藏
     * @param bookmarkId 收藏id
     * @param cookie cookie
     * @param token x-csrf-token
     * @param callback 响应处理
     */
    public static void bmkDel(long bookmarkId, String cookie, String token, BasePixivCallback callback){
        final FormBody body = new FormBody.Builder().add("bookmark_id", String.valueOf(bookmarkId)).build();
        final Request request = PixivCommon.createPostRequest(cookie,PixivUrl.DEL_BOOKMARKS,token, body);
        final Call call = PixivCommon.CLIENT.newCall(request);
        call.enqueue(callback);
    }

     /**
     * 批量删除收藏
     * @param bookmarkIds 收藏id
     * @param cookie cookie
     * @param token x-csrf-token
     * @param callback 响应处理
     */
    public static void bmkDel(Collection<Long> bookmarkIds, String cookie, String token, BasePixivCallback callback){
        final HashMap<String, Collection<Long>> map = new HashMap<>();
        map.put("bookmarkIds",bookmarkIds);
        final RequestBody body = RequestBody.create(JSONObject.toJSONString(map), PixivCommon.MEDIA_TYPE_JSON);
        final Request request = PixivCommon.createPostRequest(cookie,PixivUrl.REMOVE_BOOKMARKS,token, body);
        final Call call = PixivCommon.CLIENT.newCall(request);
        call.enqueue(callback);
    }



}
