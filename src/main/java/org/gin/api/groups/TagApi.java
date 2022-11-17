package org.gin.api.groups;

import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.OkHttpClient;
import org.gin.api.PixivApi;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.response.PixivResponse;
import org.gin.response.body.tag.SuggestByWordBody;
import org.gin.response.body.tag.TagInfo;
import org.gin.response.fields.PixivTagInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;

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
     * 查询标签信息
     * @param tag              标签
     * @param lang             语言
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < ?>>
     * @since 2022/11/1 9:55
     */
    public static PixivRequest<PixivResponse<TagInfo>> tagInfo(@NonNull String tag, @NonNull String lang,
                                                               @NotNull PixivCookieToken pixivCookieToken,
                                                               @NotNull OkHttpClient client
    ) {
        final HashMap<String, Object> map = new HashMap<>(2);
        map.put("tag", tag);
        map.put("lang", lang);
        return new PixivRequest<>(
                createHttpUrl(map, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/tag/info"),
                client
        );
    }

    /**
     * 查询标签建议(追加标签时使用)
     * @param word             关键字
     * @param lang             语言
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < ?>>
     * @since 2022/11/1 10:15
     */
    public static PixivRequest<PixivResponse<SuggestByWordBody>> suggestByWord(@NonNull String word, @NonNull String lang,
                                                                               @NotNull PixivCookieToken pixivCookieToken,
                                                                               @NotNull OkHttpClient client
    ) {
        final HashMap<String, Object> map = new HashMap<>(2);
        map.put("word", word);
        map.put("content_types_to_count[]", "illust");
        map.put("lang", lang);
        return new PixivRequest<>(
                createHttpUrl(map, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/tags/suggest_by_word"),
                client
        );
    }


}
