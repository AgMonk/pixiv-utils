package org.gin.api.groups;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.HttpUrl;
import org.gin.api.PixivApi;
import org.gin.params.user.WorksWithTagParam;
import org.gin.pixiv.response.body.illust.IllustBodySimple;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrlBuilder;
import org.gin.response.PixivResponse;
import org.gin.response.body.illustmanga.IllustMangaBookmarksRes;
import org.gin.response.body.novel.NovelBookmarksRes;
import org.gin.response.body.tag.UserTagRes;
import org.gin.response.body.user.*;
import org.gin.response.convertor.Convertor;
import org.gin.utils.JsonUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户作品API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/17 16:49
 */
@Getter
@Setter
@RequiredArgsConstructor
public class UserWorksApi {
    private final PixivApi api;


    /**
     * 查询用户作品概况
     * @param userId 用户id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.user.UserInfoBody>>
     * @since 2022/10/14 17:51
     */
    public PixivRequest<PixivResponse<ProfileRealBody>> all(long userId) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/user/%d/profile/all", userId)
                .build();
        return new PixivRequest<>(url, api.getClient(), responseBody -> {
            String string = responseBody.string();
            string = Convertor.replaceEmptyArray(string, "manga");
            string = Convertor.replaceEmptyArray(string, "illusts");
            string = Convertor.replaceEmptyArray(string, "novels");
            final PixivResponse<ProfileAllBody> response = JsonUtils.MAPPER.readValue(string, new TypeReference<PixivResponse<ProfileAllBody>>() {
            });
            final ProfileRealBody body = new ProfileRealBody(response.getBody());
            final PixivResponse<ProfileRealBody> res = new PixivResponse<>();
            res.setBody(body);
            return res;
        });
    }

    /**
     * 查询用户发出的约稿
     * @param userId 用户id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UserCommissionBody>>
     * @since 2022/10/15 14:01
     */
    public PixivRequest<CommissionRequestSentRes> commissionRequestSent(long userId) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/commission/page/users/%d/request/sent", userId)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, CommissionRequestSentRes.class));
    }

    /**
     * 查询用户的绘画中使用的标签
     * @param userId 用户id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UserCommissionBody>>
     * @since 2022/10/15 14:01
     */
    public PixivRequest<UserTagRes> illustTags(long userId) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/user/%d/illusts/tags", userId)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, UserTagRes.class));
    }

    /**
     * 查询用户的绘画信息
     * @param userId 用户id
     * @param ids    查询的作品id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.user.ProfileRealBody>>
     * @since 2022/10/15 11:18
     */
    public PixivRequest<ProfileIllustsRes> illusts(long userId, @NotNull List<Long> ids) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/user/%d/profile/illusts", userId)
                .addParam("is_first_page", 1)
                .addParam("work_category", "illustManga")
                .addParam("ids[]", ids)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, ProfileIllustsRes.class));
    }

    /**
     * 查询带有指定标签的用户插画
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.request.PixivRequest<org.gin.response.body.user.ProfileIllustsRes>
     * @since 2022/11/19 9:54
     */
    public PixivRequest<IllustMangaBookmarksRes> illustsWithTag(long userId, @NotNull WorksWithTagParam param) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/user/%d/illusts/tag", userId)
                .setParams(param)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, IllustMangaBookmarksRes.class));
    }

    /**
     * 查询用户的漫画中使用的标签
     * @param userId 用户id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UserCommissionBody>>
     * @since 2022/10/15 14:01
     */
    public PixivRequest<UserTagRes> mangaTags(long userId) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/user/%d/manga/tags", userId)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, UserTagRes.class));
    }

    /**
     * 查询带有指定标签的用户漫画
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.request.PixivRequest<org.gin.response.body.user.ProfileIllustsRes>
     * @since 2022/11/19 9:54
     */
    public PixivRequest<IllustMangaBookmarksRes> mangasWithTag(long userId, @NotNull WorksWithTagParam param) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/user/%d/manga/tag", userId)
                .setParams(param)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, IllustMangaBookmarksRes.class));
    }

    /**
     * 查询用户的小说中使用的标签
     * @param userId 用户id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UserCommissionBody>>
     * @since 2022/10/15 14:01
     */
    public PixivRequest<UserTagRes> novelTags(long userId) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/user/%d/novels/tags", userId)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, UserTagRes.class));
    }

    /**
     * 查询用户的小说
     * @param userId 用户id
     * @param ids    查询的作品id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.user.ProfileIllustsBody>>
     * @since 2022/10/17 11:10
     */
    public PixivRequest<ProfileNovelsRes> novels(long userId, @NotNull List<Long> ids) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/user/%d/profile/novels", userId)
                .addParam("ids[]", ids)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, ProfileNovelsRes.class));
    }

    /**
     * 查询带有指定标签的用户小说
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.request.PixivRequest<org.gin.response.body.user.ProfileIllustsRes>
     * @since 2022/11/19 9:54
     */
    public PixivRequest<NovelBookmarksRes> novelsWithTag(long userId, @NotNull WorksWithTagParam param) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/user/%d/novels/tag", userId)
                .setParams(param)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, NovelBookmarksRes.class));
    }

    public void zTest() throws IOException {
        long authorId = 15358167;

        zTestTag(authorId);
        zTestProfile(authorId);
        zTestCommission();
        zTestWithTag(authorId);
    }

    private void zTestCommission() {
        final long uid = 67898338L;
        commissionRequestSent(uid).async(res -> {
            final List<IllustBodySimple> illust = res.getBody().getThumbnails().getIllust();
            final String ids = illust.stream().map(IllustBodySimple::getId).map(Object::toString).collect(Collectors.joining(","));
            System.out.printf("[用户约稿] uid: %d pid: %s", uid, ids);
        });
    }

    private void zTestProfile(long authorId) throws IOException {
        final ProfileRealBody profileRealBody = all(authorId).sync().getBody();
        final List<Long> illusts = profileRealBody.getIllusts();
        final List<Long> novels = profileRealBody.getNovels();
        System.out.printf("[用户绘画] %s 个\n", illusts.size());
        System.out.printf("[用户小说] %s 个\n", novels.size());

        illusts(authorId, illusts.subList(0, Math.min(3, illusts.size())))
                .async(res -> System.out.printf("[用户绘画] ID: %s \n"
                        , res.getBody().getIllusts().keySet().stream().map(Object::toString).collect(Collectors.joining(","))));

        novels(authorId, novels.subList(0, Math.min(3, illusts.size())))
                .async(res -> System.out.printf("[用户小说] ID: %s \n"
                        , res.getBody().getNovels().keySet().stream().map(Object::toString).collect(Collectors.joining(","))));


    }

    private void zTestTag(long authorId) {
        illustTags(authorId).async(res -> System.out.printf("[用户插画标签] %s 个\n", res.getBody().size()));
        novelTags(authorId).async(res -> System.out.printf("[用户小说标签] %s 个\n", res.getBody().size()));
        mangaTags(authorId).async(res -> System.out.printf("[用户漫画标签] %s 个\n", res.getBody().size()));
    }

    private void zTestWithTag(long authorId) {

        final WorksWithTagParam param = new WorksWithTagParam("ドールズフロントライン", 1, 10);
        illustsWithTag(authorId, param).async(res -> JsonUtils.printJson(res.getBody().getWorks()));
        mangasWithTag(authorId, param).async(res -> JsonUtils.printJson(res.getBody().getWorks()));
        novelsWithTag(authorId, param).async(res -> JsonUtils.printJson(res.getBody().getWorks()));
    }

}   
