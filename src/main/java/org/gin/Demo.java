package org.gin;

import okhttp3.Call;
import org.gin.api.PixivApi;
import org.gin.api.groups.IllustApi;
import org.gin.exception.PixivException;
import org.gin.exception.PixivRequestException;
import org.gin.request.PixivCookieToken;
import org.gin.response.body.illustmanga.IllustMangaRes;
import org.gin.response.callback.PixivCallback;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 17:22
 */
public class Demo {
    public static void main(String[] args) {
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
        illustApi.detail(pid).async(new PixivCallback<IllustMangaRes>() {
            /**
             * 请求失败的处理方法
             * @param call call
             * @param e    异常
             */
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                PixivCallback.super.onFailure(call, e);
            }

            /**
             * 触发Pixiv异常时的处理方法
             * @param e 异常
             */
            @Override
            public void onPixivException(PixivException e) {
                PixivCallback.super.onPixivException(e);
            }

            @Override
            public void onSuccess(IllustMangaRes res) throws PixivException, IOException {
                final IllustMangaRes.IllustMangaBody body = res.getBody();

            }
        });

        //同步
        try {
            final IllustMangaRes res = illustApi.detail(pid).sync();
            final IllustMangaRes.IllustMangaBody body = res.getBody();
        } catch (PixivRequestException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
