package org.gin.pixiv.api;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import org.gin.pixiv.call.PixivCallStandard;
import org.gin.pixiv.enums.ParamType;
import org.gin.pixiv.main.PixivClient;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrlBuilder;
import org.gin.response.body.tag.SuggestByWordBody;
import org.gin.response.body.tag.SuggestByWordRes;
import org.gin.response.body.tag.TagInfoRes;
import org.gin.response.convertor.Convertor;
import org.gin.response.fields.PixivTagInfo;
import org.gin.response.fields.PixivTagInfoRes;
import org.gin.utils.JsonUtils;
import org.gin.utils.MapUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 标签API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 17:44
 */
@RequiredArgsConstructor
public class TagApi {
    private final PixivClient client;


    /**
     * 为绘画追加标签
     * @param pid 绘画id
     * @param tag 标签
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.response.fields.PixivTagInfo>
     * @since 2023/3/29 17:57
     */
    public PixivCallStandard<PixivTagInfo> postIllustAdd(long pid, @NotNull String tag) {
        return client.standard(ParamType.JSON, String.format("/ajax/tags/illust/%d/add", pid), PixivTagInfo.class, MapUtils.singleEntry("tag", tag));
    }

    /**
     * 查询标签建议(追加标签时使用)
     * @param keyword 关键字
     * @return org.gin.request.PixivRequest<org.gin.response.body.tag.SuggestByWordRes>
     * @since 2022/11/17 16:59
     */
    public PixivRequest<SuggestByWordRes> suggestByWord(@NonNull String keyword) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/tags/suggest_by_word")
                .addParam("content_types_to_count[]", "illust")
                .addParam("word", keyword)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, SuggestByWordRes.class));
    }

    /**
     * 查询标签信息
     * @param tag 标签
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < ?>>
     * @since 2022/11/1 9:55
     */
    public PixivRequest<TagInfoRes> tagInfo(@NonNull String tag) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/tag/info")
                .addParam("tag", tag)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, TagInfoRes.class));
    }

    public void zTest() throws IOException {
        zTestSuggest();
        zTestTagInfo();
        zTestIllustAdd();
    }

    private void zTestIllustAdd() throws IOException {
        long pid = 102177911;
        String tag = "RO635(ドールズフロントライン)";

        final PixivTagInfoRes res = postIllustAdd(pid, tag).sync();
        JsonUtils.printJson(res);
    }

    private void zTestSuggest() throws IOException {
        final SuggestByWordRes res = suggestByWord("RO635").sync();
        final List<SuggestByWordBody.Candidate> candidates = res.getBody().getCandidates();
        final String join = candidates.stream().map(SuggestByWordBody.Candidate::getTagName).collect(Collectors.joining(","));
        System.out.println("join = " + join);
    }

    private void zTestTagInfo() throws IOException {
        final TagInfoRes res = tagInfo("RO635(ドールズフロントライン)").sync();
        JsonUtils.printJson(res);
    }


}
