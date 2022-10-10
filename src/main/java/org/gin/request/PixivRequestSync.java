package org.gin.request;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import okhttp3.*;
import org.gin.callback.BasePixivCallback;
import org.gin.callback.FollowLatestCallback;
import org.gin.response.PixivResponse;
import org.gin.response.body.ArtworkBody;

import java.io.IOException;

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
        final Request request = PixivCommon.createGetRequest(cookie,String.format(PixivUrl.URL_ILLUST_DETAIL,pid));
        final Call call = PixivCommon.CLIENT.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = BasePixivCallback.handle(call, response);
        return JSONObject.parseObject(responseBody.string(), new TypeReference<>() {});
    }

    /**
     * 查询关注作者最新作品
     * @param page     页码
     * @param callback 响应处理
     * @param cookie   cookie
     */
    public static void followLatest(int page, FollowLatestCallback callback, String cookie) {
        final HttpUrl url = PixivUrl.followLatestUrl(page,"all","zh");
        final Request request = PixivCommon.createGetRequest(cookie,url);
        final Call call = PixivCommon.CLIENT.newCall(request);
        call.enqueue(callback);
    }

}
