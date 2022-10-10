package org.gin.request;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 14:19
 **/
public class PixivArtworkRequest {

    public static void details(long pid, Callback callback, String cookie) {
        final Request request = new Request.Builder()
                .url(PixivCommon.DOMAIN_AJAX + "illust/" + pid)
                .header("cookie",cookie)
                .build();
        final Call call = PixivCommon.CLIENT.newCall(request);
        call.enqueue(callback);
    }
}
