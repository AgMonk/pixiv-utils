package org.gin.request;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.gin.callback.BasePixivCallback;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 14:19
 **/
public class PixivRequest {
    /**
     * 查询作品详情
     * @param pid       pid
     * @param callback 响应处理
     * @param cookie cookie
     */
    public static void details(long pid, BasePixivCallback callback, String cookie) {
        final Request request = new Request.Builder()
                .url(PixivCommon.DOMAIN_AJAX + "illust/" + pid)
                .header("cookie",cookie)
                .build();
        final Call call = PixivCommon.CLIENT.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 查询关注作者最新作品
     * @param page 页码
     * @param callback 响应处理
     * @param cookie cookie
     */
    public static void followLatest(int page,BasePixivCallback callback,String cookie){
        final HttpUrl url =  HttpUrl.parse(PixivCommon.DOMAIN_AJAX + "follow_latest/illust").newBuilder()
                .addQueryParameter("mode","all")
                .addQueryParameter("lang","zh")
                .addQueryParameter("p", String.valueOf(page))
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .header("cookie",cookie)
                .build();
        final Call call = PixivCommon.CLIENT.newCall(request);
        call.enqueue(callback);
    }
}
