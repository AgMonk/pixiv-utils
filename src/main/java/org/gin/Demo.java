package org.gin;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.gin.api.ApiIllustManga;
import org.gin.exception.PixivException;
import org.gin.exception.PixivRequestException;
import org.gin.params.SimpleParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.response.PixivResponse;
import org.gin.response.body.BookmarkDataBody;
import org.gin.response.callback.BaseCallback;
import org.gin.response.convertor.Convertor;

import java.io.IOException;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 17:22
 */
public class Demo {
    public static void main(String[] args) {
        final String sessionId = "xxxxxxxxxxxxxxxxxxxxxx";
        final String token = "xxxxxxxxxxxx";
        final PixivCookieToken cookieToken = new PixivCookieToken(sessionId, token);
        final OkHttpClient client = Pixiv.CLIENT;


        final PixivRequest<PixivResponse<BookmarkDataBody>> request =
                ApiIllustManga.bookmarkData(101950923L, new SimpleParam(), cookieToken, client);

        //异步
        request.async(new BaseCallback<PixivResponse<BookmarkDataBody>>() {
            @Override
            public PixivResponse<BookmarkDataBody> convert(ResponseBody responseBody) throws IOException {
                return Convertor.common(responseBody, BookmarkDataBody.class);
            }

            @Override
            public void onPixivException(PixivException e) {
                e.printStackTrace();
            }

            @Override
            public void onSuccess(PixivResponse<BookmarkDataBody> res) {

            }
        });

        //同步
        try {
            final PixivResponse<BookmarkDataBody> response =
                    request.sync(body -> Convertor.common(body, BookmarkDataBody.class));
        } catch (PixivRequestException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
