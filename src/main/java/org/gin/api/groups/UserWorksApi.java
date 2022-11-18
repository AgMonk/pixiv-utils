package org.gin.api.groups;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import org.gin.api.PixivApi;
import org.gin.exception.PixivRequestException;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.PixivResponse;
import org.gin.response.body.tag.UserTag;
import org.gin.response.body.tag.UserTagRes;
import org.gin.response.body.user.*;
import org.gin.response.callback.BaseCallback;
import org.gin.response.convertor.Convertor;
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

    //todo

    /**
     * 查询用户作品概况
     * @param userId 用户id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.user.UserInfoBody>>
     * @since 2022/10/14 17:51
     */
    public PixivRequest<PixivResponse<ProfileRealBody>> all(long userId) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/profile/all", userId)
                .build();
        return new PixivRequest<>(url, api.getClient(), responseBody -> {
            String string = responseBody.string();
            string = Convertor.replaceEmptyArray(string, "manga");
            string = Convertor.replaceEmptyArray(string, "illusts");
            string = Convertor.replaceEmptyArray(string, "novels");
            final PixivResponse<ProfileBody> response = JSONObject.parseObject(string, new TypeReference<PixivResponse<ProfileBody>>() {
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
        final HttpUrl url = new PixivUrl.Builder()
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
        final HttpUrl url = new PixivUrl.Builder()
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
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/profile/illusts", userId)
                .addParam("is_first_page", 1)
                .addParam("work_category", "illustManga")
                .addParam("ids[]", ids)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, ProfileIllustsRes.class));
    }

    /**
     * 查询用户的小说中使用的标签
     * @param userId 用户id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UserCommissionBody>>
     * @since 2022/10/15 14:01
     */
    public PixivRequest<UserTagRes> novelTags(long userId) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/novels/tags", userId)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, UserTagRes.class));
    }

    /**
     * 查询用户的小说
     * @param userId           用户id
     * @param ids  查询的作品id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.user.ProfileIllustsBody>>
     * @since 2022/10/17 11:10
     */
    public PixivRequest<ProfileNovelsRes> novels(long userId, @NotNull List<Long> ids) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/profile/novels", userId)
                .addParam("ids[]", ids)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, ProfileNovelsRes.class));
    }

    public void zTest() throws PixivRequestException, IOException {
        long authorId = 15358167;

        zTestTag(authorId);
        zTestProfile(authorId);
    }

    private void zTestProfile(long authorId) throws PixivRequestException, IOException {
        final ProfileRealBody profileRealBody = all(authorId).sync().getBody();
        final List<Long> illusts = profileRealBody.getIllusts();
        final List<Long> novels = profileRealBody.getNovels();
        System.out.printf("[用户绘画] %s 个\n", illusts.size());
        System.out.printf("[用户小说] %s 个\n", novels.size());

        illusts(authorId, illusts.subList(0, Math.min(3, illusts.size()))).async(new BaseCallback<PixivResponse<ProfileIllustsBody>>() {
            @Override
            public PixivResponse<ProfileIllustsBody> convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), new TypeReference<PixivResponse<ProfileIllustsBody>>() {
                });
            }

            @Override
            public void onSuccess(PixivResponse<ProfileIllustsBody> res) {
                System.out.printf("[用户绘画] ID: %s \n", res.getBody().getWorks().keySet().stream().map(Object::toString).collect(Collectors.joining(",")));
            }
        });

        novels(authorId, novels.subList(0, Math.min(3, illusts.size()))).async(new BaseCallback<PixivResponse<ProfileNovelsBody>>() {
            @Override
            public PixivResponse<ProfileNovelsBody> convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), new TypeReference<PixivResponse<ProfileNovelsBody>>() {
                });
            }

            @Override
            public void onSuccess(PixivResponse<ProfileNovelsBody> res) {
                System.out.printf("[用户小说] ID: %s \n", res.getBody().getWorks().keySet().stream().map(Object::toString).collect(Collectors.joining(",")));
            }
        });


    }

    private void zTestTag(long authorId) {
        illustTags(authorId).async(new BaseCallback<PixivResponse<List<UserTag>>>() {
            @Override
            public PixivResponse<List<UserTag>> convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), new TypeReference<PixivResponse<List<UserTag>>>() {
                });
            }

            @Override
            public void onSuccess(PixivResponse<List<UserTag>> res) {
                System.out.printf("[用户绘画标签] %s 个\n", res.getBody().size());
            }
        });

        novelTags(authorId).async(new BaseCallback<PixivResponse<List<UserTag>>>() {
            @Override
            public PixivResponse<List<UserTag>> convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), new TypeReference<PixivResponse<List<UserTag>>>() {
                });
            }

            @Override
            public void onSuccess(PixivResponse<List<UserTag>> res) {
                System.out.printf("[用户小说标签] %s 个\n", res.getBody().size());
            }
        });
    }

}   
