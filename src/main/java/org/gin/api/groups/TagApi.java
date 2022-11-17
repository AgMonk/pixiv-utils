package org.gin.api.groups;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.HttpUrl;
import org.gin.api.PixivApi;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.body.tag.SuggestByWordRes;
import org.gin.response.body.tag.TagInfoRes;
import org.gin.response.fields.PixivTagInfoRes;

import static org.gin.request.PixivRequestBody.createJsonBody;

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

    /**
     * 为绘画追加标签
     * @param pid 绘画id
     * @param tag 标签
     * @return org.gin.request.PixivRequest<PixivTagInfoRes>
     * @since 2022/11/17 17:14
     */
    public PixivRequest<PixivTagInfoRes> illustAdd(long pid, String tag) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/tags/illust/%d/add", pid)
                .build();
        return new PixivRequest<>(url, api.getClient(), createJsonBody("tag", tag));
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
    public PixivRequest<TagInfoRes> tagInfo(@NonNull String tag) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/tag/info")
                .addParam("tag", tag)
                .build();
        return new PixivRequest<>(url, api.getClient());
    }

    public void zTest() {

    }


}
