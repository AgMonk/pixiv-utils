package org.gin.pixiv.api;

import lombok.RequiredArgsConstructor;
import org.gin.params.user.WorksWithTagParam;
import org.gin.pixiv.call.PixivCallStandard;
import org.gin.pixiv.call.PixivCallType;
import org.gin.pixiv.callback.StandardCallback;
import org.gin.pixiv.callback.TypeCallback;
import org.gin.pixiv.main.PixivClient;
import org.gin.pixiv.response.body.common.BaseWorkBody;
import org.gin.pixiv.response.body.illust.IllustBodyFromUser;
import org.gin.pixiv.response.body.illust.IllustBodySimple;
import org.gin.pixiv.response.body.novel.NovelBodyFromUser;
import org.gin.response.body.tag.UserTagRes;
import org.gin.response.body.user.CommissionRequestSentBody;
import org.gin.response.body.user.ProfileAllBody;
import org.gin.response.body.user.ProfileIllustsBody;
import org.gin.response.body.user.ProfileNovelsBody;
import org.gin.utils.JsonUtils;
import org.gin.utils.MapUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户作品Api
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/30 11:13
 */
@RequiredArgsConstructor
public class UserProfileApi {
    private final PixivClient client;

    /**
     * 查询用户作品概况
     * @param userId 用户id
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.response.body.user.ProfileAllBody>
     * @since 2023/3/30 12:07
     */
    public PixivCallStandard<ProfileAllBody> getAll(long userId) {
        return client.standard(String.format("/ajax/user/%d/profile/all", userId), ProfileAllBody.class);
    }

    /**
     * 查询用户发出的约稿
     * @param userId 用户id
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.response.body.user.CommissionRequestSentBody>
     * @since 2023/3/30 14:22
     */
    public PixivCallStandard<CommissionRequestSentBody> getCommissionRequestSent(long userId) {
        return client.standard(String.format("/ajax/commission/page/users/%d/request/sent", userId), CommissionRequestSentBody.class);
    }

    /**
     * 查询用户的绘画中使用的标签
     * @param userId 用户id
     * @return org.gin.pixiv.call.PixivCallType<org.gin.response.body.tag.UserTagRes>
     * @since 2023/3/30 14:23
     */
    public PixivCallType<UserTagRes> getIllustTags(long userId) {
        return client.type(String.format("/ajax/user/%d/illusts/tags", userId), UserTagRes.class);
    }

    /**
     * 查询用户的绘画信息
     * @param userId 用户id
     * @param ids    查询的作品id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.user.ProfileRealBody>>
     * @since 2022/10/15 11:18
     */
    public PixivCallStandard<ProfileIllustsBody> getIllusts(long userId, @NotNull List<Long> ids) {
        final HashMap<String, Object> param = MapUtils.singleEntry("ids[]", ids);
        param.put("is_first_page", 1);
        param.put("work_category", "illustManga");
        return client.standard(String.format("/ajax/user/%d/profile/illusts", userId), ProfileIllustsBody.class, param);
    }

    /**
     * 查询带有指定标签的用户插画
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.pixiv.response.body.illust.IllustBodyUserBookmark>
     * @since 2023/3/30 14:35
     */
    public PixivCallStandard<IllustBodyFromUser> getIllustsWithTag(long userId, @NotNull WorksWithTagParam param) {
        return client.standard(String.format("/ajax/user/%d/illusts/tag", userId), IllustBodyFromUser.class, param);
    }

    /**
     * 查询用户的漫画中使用的标签
     * @param userId 用户id
     * @return org.gin.pixiv.call.PixivCallType<org.gin.response.body.tag.UserTagRes>
     * @since 2023/3/30 14:37
     */
    public PixivCallType<UserTagRes> getMangaTags(long userId) {
        return client.type(String.format("/ajax/user/%d/manga/tags", userId), UserTagRes.class);
    }

    /**
     * 查询带有指定标签的用户漫画
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.pixiv.response.body.illust.IllustBodyFromUser>
     * @since 2023/3/30 14:50
     */
    public PixivCallStandard<IllustBodyFromUser> getMangasWithTag(long userId, @NotNull WorksWithTagParam param) {
        return client.standard(String.format("/ajax/user/%d/manga/tag", userId), IllustBodyFromUser.class, param);
    }

    /**
     * 查询用户的小说中使用的标签
     * @param userId 用户id
     * @return org.gin.pixiv.call.PixivCallType<org.gin.response.body.tag.UserTagRes>
     * @since 2023/3/30 14:50
     */
    public PixivCallType<UserTagRes> getNovelTags(long userId) {
        return client.type(String.format("/ajax/user/%d/novels/tags", userId), UserTagRes.class);
    }

    /**
     * 查询用户的小说
     * @param userId 用户id
     * @param ids    查询的作品id
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.response.body.user.ProfileNovelsBody>
     * @since 2023/3/30 14:55
     */
    public PixivCallStandard<ProfileNovelsBody> getNovels(long userId, @NotNull List<Long> ids) {
        return client.standard(String.format("/ajax/user/%d/profile/novels", userId), ProfileNovelsBody.class, MapUtils.singleEntry("ids[]", ids));
    }

    /**
     * 查询带有指定标签的用户小说
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.request.PixivRequest<org.gin.response.body.user.ProfileIllustsRes>
     * @since 2022/11/19 9:54
     */
    public PixivCallStandard<NovelBodyFromUser> getNovelsWithTag(long userId, @NotNull WorksWithTagParam param) {
        return client.standard(String.format("/ajax/user/%d/novels/tag", userId), NovelBodyFromUser.class, param);
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
        getCommissionRequestSent(uid).async(new StandardCallback<CommissionRequestSentBody>() {
            @Override
            public void onSuccess(CommissionRequestSentBody body) {
                final List<IllustBodySimple> illust = body.getThumbnails().getIllust();
                final String ids = illust.stream().map(IllustBodySimple::getId).map(Object::toString).collect(Collectors.joining(","));
                System.out.printf("[用户约稿] uid: %d pid: %s", uid, ids);
                JsonUtils.printJson(illust);
            }
        });
    }

    private void zTestProfile(long authorId) throws IOException {
        final ProfileAllBody profileRealBody = getAll(authorId).sync();
        final List<Long> illusts = new ArrayList<>(profileRealBody.getIllusts().keySet());
        final List<Long> novels = new ArrayList<>(profileRealBody.getNovels().keySet());
        System.out.printf("[用户绘画] %s 个\n", illusts.size());
        System.out.printf("[用户小说] %s 个\n", novels.size());

        getIllusts(authorId, illusts.subList(0, Math.min(3, illusts.size())))
                .async(new StandardCallback<ProfileIllustsBody>() {
                    @Override
                    public void onSuccess(ProfileIllustsBody body) {
                        System.out.println("用户绘画: " + body.getIllusts().values().stream().map(BaseWorkBody::getId).map(String::valueOf).collect(
                                Collectors.joining(",")));
                    }
                });

        getNovels(authorId, novels.subList(0, Math.min(3, illusts.size())))
                .async(new StandardCallback<ProfileNovelsBody>() {
                    @Override
                    public void onSuccess(ProfileNovelsBody body) {
                        System.out.println("用户小说: " + body.getNovels().values().stream().map(BaseWorkBody::getId).map(String::valueOf).collect(
                                Collectors.joining(",")));
                    }
                });


    }

    private void zTestTag(long authorId) {
        getIllustTags(authorId).async(new TypeCallback<UserTagRes>() {
            @Override
            public void onSuccess(UserTagRes body) {
                System.out.printf("[用户插画标签] %s 个\n", body.getBody().size());
            }
        });
        getNovelTags(authorId).async(new TypeCallback<UserTagRes>() {
            @Override
            public void onSuccess(UserTagRes body) {
                System.out.printf("[用户小说标签] %s 个\n", body.getBody().size());
            }
        });
        getMangaTags(authorId).async(new TypeCallback<UserTagRes>() {
            @Override
            public void onSuccess(UserTagRes body) {
                System.out.printf("[用户漫画标签] %s 个\n", body.getBody().size());
            }
        });
    }

    private void zTestWithTag(long authorId) {

        final WorksWithTagParam param = new WorksWithTagParam("ドールズフロントライン", 1, 10);
        getIllustsWithTag(authorId, param).async(new StandardCallback<IllustBodyFromUser>() {
            @Override
            public void onSuccess(IllustBodyFromUser body) {
                JsonUtils.printJson(body.getIllusts());
            }
        });
        getMangasWithTag(authorId, param).async(new StandardCallback<IllustBodyFromUser>() {
            @Override
            public void onSuccess(IllustBodyFromUser body) {
                JsonUtils.printJson(body.getIllusts());
            }
        });
        getNovelsWithTag(authorId, param).async(new StandardCallback<NovelBodyFromUser>() {
            @Override
            public void onSuccess(NovelBodyFromUser body) {
                JsonUtils.printJson(body.getNovels());
            }
        });
    }


}   
