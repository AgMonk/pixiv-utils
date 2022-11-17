package org.gin.api.groups;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.gin.api.PixivApi;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.PixivResponse;
import org.gin.response.body.tag.SuggestByWordRes;
import org.gin.response.body.tag.TagInfo;
import org.gin.response.fields.PixivTagInfo;

import static org.gin.request.PixivRequestBody.createJsonBody;
import static org.gin.request.PixivUrl.createHttpUrl;

/**
 * 标签接口
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/1 09:51
 */
@Getter
@Setter
@RequiredArgsConstructor
public class TagApi {
    private final PixivApi api;

    //todo

    /**
     * 为绘画追加标签
     * @param pid              作品id
     * @param tag              标签
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.BookmarkAddBody>>
     * @since 2022/11/1 9:53
     */
    public static PixivRequest<PixivResponse<PixivTagInfo>> illustAdd(long pid, String tag,
                                                                      @NonNull PixivCookieToken pixivCookieToken,
                                                                      @NonNull OkHttpClient client
    ) {

        return new PixivRequest<>(
                createHttpUrl(Pixiv.DOMAIN + "/ajax/tags/illust/%d/add", pid)
                , client, createJsonBody("tag", tag)
        );
    }

    /**
     * 查询标签建议(追加标签时使用)
     * @param keyword 关键字
     * @return org.gin.request.PixivRequest<org.gin.response.body.tag.SuggestByWordRes>
     * @since 2022/11/17 16:59
     */
    public PixivRequest<SuggestByWordRes> suggestByWord(@NonNull String keyword) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/tags/suggest_by_word")
                .addParam("content_types_to_count[]", "illust")
                .addParam("word", keyword)
                .build();
        return new PixivRequest<>(url, api.getClient());
    }

    /**
     * 查询标签信息
     * @param tag 标签
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < ?>>
     * @since 2022/11/1 9:55
     */
    public PixivRequest<PixivResponse<TagInfo>> tagInfo(@NonNull String tag) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/tag/info")
                .addParam("tag", tag)
                .build();
        return new PixivRequest<>(url, api.getClient());
    }


}
