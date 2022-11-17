package org.gin;

import com.alibaba.fastjson.JSONObject;
import okhttp3.ResponseBody;
import org.gin.api.PixivApi;
import org.gin.api.groups.IllustApi;
import org.gin.exception.PixivRequestException;
import org.gin.request.PixivCookieToken;
import org.gin.response.body.illustmanga.IllustMangaRes;
import org.gin.response.callback.BaseCallback;
import org.gin.response.convertor.Convertor;

import java.io.IOException;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 17:22
 */
public class Demo {
    public static void main(String[] args) throws PixivRequestException, IOException {
        final String sessionId = "aaaaaaaaaaaaaaa";
        final String token = "bbbbbbbbb";
        final PixivCookieToken cookieToken = new PixivCookieToken(sessionId, token);

        //常规用法
        final PixivApi pixivApi = new PixivApi.Builder()
                //如果想要使用自定义的 client
//                .setClient(new OkHttpClient.Builder().build())
                //如果想要连接某个反代
//                .setDomain("http://localhost:8080/api")
                //如果想要修改提示信息使用的语言
//                .setLang("en")
                .setCookieToken(cookieToken)
                .build();

        //第一次启动 ，未获取到token的用法
//        final PixivApi pixivApi = new PixivApi.Builder()
//                .setSessionId(sessionId)
//                .build();


        final IllustApi illustApi = pixivApi.getIllustApi();
        final long pid = 99147997L;

        //异步
        illustApi.detail(pid).async(new BaseCallback<IllustMangaRes>() {
            @Override
            public void onSuccess(IllustMangaRes res) {
                final IllustMangaRes.IllustMangaBody body = res.getBody();
            }

            @Override
            public IllustMangaRes convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), IllustMangaRes.class);
            }
        });

        //同步
        final IllustMangaRes res = illustApi.detail(pid).sync(body -> Convertor.common2(body, IllustMangaRes.class));
        final IllustMangaRes.IllustMangaBody body = res.getBody();

    }
}
