package org.gin.pixiv.api;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.gin.pixiv.call.PixivCallStandard;
import org.gin.pixiv.enums.ParamType;
import org.gin.pixiv.main.PixivClient;
import org.gin.response.body.tag.SuggestByWordBody;
import org.gin.response.body.tag.TagInfo;
import org.gin.response.fields.PixivTagInfo;
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
     * 查询标签建议(追加标签或搜索时使用)
     * @param keyword 关键字
     * @return {@link org.gin.pixiv.call.PixivCallStandard<org.gin.response.body.tag.SuggestByWordBody>}
     * @since 2023/3/30 9:22
     */
    public PixivCallStandard<SuggestByWordBody> getSuggestByWord(@NonNull String keyword) {
        return client.standard("/ajax/tags/suggest_by_word", SuggestByWordBody.class, MapUtils.singleEntry("word", keyword));
    }

    /**
     * 查询标签信息
     * @param tag 标签
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < ?>>
     * @since 2022/11/1 9:55
     */
    public PixivCallStandard<TagInfo> getTagInfo(@NonNull String tag) {
        return client.standard("/ajax/tag/info", TagInfo.class, MapUtils.singleEntry("tag", tag));
    }

    public void zTest() throws IOException {
        zTestSuggest();
        zTestTagInfo();
        zTestIllustAdd();
    }

    private void zTestIllustAdd() throws IOException {
        long pid = 102177911;
        String tag = "RO635(ドールズフロントライン)";

        final PixivTagInfo res = postIllustAdd(pid, tag).sync();
        JsonUtils.printJson(res);
    }

    private void zTestSuggest() throws IOException {
        final SuggestByWordBody res = getSuggestByWord("RO635").sync();
        final List<SuggestByWordBody.Candidate> candidates = res.getCandidates();
        final String join = candidates.stream().map(SuggestByWordBody.Candidate::getTagName).collect(Collectors.joining(","));
        System.out.println("join = " + join);
    }

    private void zTestTagInfo() throws IOException {
        final TagInfo res = getTagInfo("RO635(ドールズフロントライン)").sync();
        JsonUtils.printJson(res);
    }


}
