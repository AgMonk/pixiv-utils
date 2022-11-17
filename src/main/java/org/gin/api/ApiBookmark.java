package org.gin.api;

import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import org.gin.params.SimpleParam;
import org.gin.params.bookmark.AddNovelParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.response.PixivResponse;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import static org.gin.request.PixivRequestBody.createFormBody;
import static org.gin.request.PixivRequestBody.createJsonBody;
import static org.gin.request.PixivUrl.createHttpUrl;

/**
 * 收藏相关接口
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 14:04
 */
public class ApiBookmark {



    /**
     * 收藏小说
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < java.lang.Long>>
     * @since 2022/10/17 11:52
     */
    public static PixivRequest<PixivResponse<Long>> addNovel(@NonNull AddNovelParam param,
                                                             @NonNull PixivCookieToken pixivCookieToken,
                                                             @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                createHttpUrl(Pixiv.DOMAIN + "/ajax/novels/bookmarks/add")
                , createJsonBody(param)
                , client, pixivCookieToken);
    }

    /**
     * 删除收藏小说
     * @param bookmarkId       收藏id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < java.lang.String>>
     * @since 2022/10/17 13:20
     */
    public static PixivRequest<PixivResponse<String>> delNovel(long bookmarkId,
                                                               @NotNull SimpleParam param,
                                                               @NonNull PixivCookieToken pixivCookieToken,
                                                               @NonNull OkHttpClient client
    ) {
        final HashMap<String, Long> body = new HashMap<>(2);
        body.put("book_id", bookmarkId);
        body.put("del", 1L);
        return new PixivRequest<>(
                createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/novels/bookmarks/delete")
                , createFormBody(body)
                , client, pixivCookieToken);
    }

    /**
     * 删除收藏小说(批量)
     * @param param            参数
     * @param bookmarkIds      收藏id
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < java.lang.String>>
     * @since 2022/10/15 14:33
     */
    public static PixivRequest<PixivResponse<String>> delNovels(Collection<Long> bookmarkIds,
                                                                @NotNull SimpleParam param,
                                                                @NonNull PixivCookieToken pixivCookieToken,
                                                                @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/novels/bookmarks/remove")
                , createJsonBody("bookmarkIds", bookmarkIds)
                , client, pixivCookieToken);
    }
}
