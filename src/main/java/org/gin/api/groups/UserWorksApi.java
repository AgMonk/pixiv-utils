package org.gin.api.groups;

import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.OkHttpClient;
import org.gin.api.PixivApi;
import org.gin.params.SimpleParam;
import org.gin.params.user.ProfileIllustsParam;
import org.gin.params.user.ProfileNovelsParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.response.PixivResponse;
import org.gin.response.body.tag.UserTag;
import org.gin.response.body.user.CommissionRequestSentBody;
import org.gin.response.body.user.ProfileIllustsBody;
import org.gin.response.body.user.ProfileNovelsBody;
import org.gin.response.body.user.ProfileRealBody;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static org.gin.request.PixivUrl.createHttpUrl;

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
     * @param userId           用户id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.user.UserInfoBody>>
     * @since 2022/10/14 17:51
     */
    public static PixivRequest<PixivResponse<ProfileRealBody>> all(long userId,
                                                                   @NotNull SimpleParam param,
                                                                   @NotNull PixivCookieToken pixivCookieToken,
                                                                   @NotNull OkHttpClient client) {
        return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/user/%d/profile/all", userId)
                , client);
    }

    /**
     * 查询用户的绘画信息
     * @param userId           用户id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.user.ProfileRealBody>>
     * @since 2022/10/15 11:18
     */
    public static PixivRequest<PixivResponse<ProfileIllustsBody>> illusts(long userId,
                                                                          @NotNull ProfileIllustsParam param,
                                                                          @NotNull PixivCookieToken pixivCookieToken,
                                                                          @NotNull OkHttpClient client) {
        return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/user/%d/profile/illusts", userId)
                , client);
    }

    /**
     * 查询用户的小说
     * @param userId           用户id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.user.ProfileIllustsBody>>
     * @since 2022/10/17 11:10
     */
    public static PixivRequest<PixivResponse<ProfileNovelsBody>> novels(long userId,
                                                                        @NotNull ProfileNovelsParam param,
                                                                        @NotNull PixivCookieToken pixivCookieToken,
                                                                        @NotNull OkHttpClient client) {
        return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/user/%d/profile/novels", userId), client);
    }

    /**
     * 查询用户发出的约稿
     * @param userId           用户id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UserCommissionBody>>
     * @since 2022/10/15 14:01
     */
    public static PixivRequest<PixivResponse<CommissionRequestSentBody>> commissionRequestSent(long userId,
                                                                                               @NotNull SimpleParam param,
                                                                                               @NotNull PixivCookieToken pixivCookieToken,
                                                                                               @NotNull OkHttpClient client) {
        return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/commission/page/users/%d/request/sent", userId), client);
    }


    /**
     * 查询用户的小说中使用的标签
     * @param userId           用户id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UserCommissionBody>>
     * @since 2022/10/15 14:01
     */
    public static PixivRequest<PixivResponse<List<UserTag>>> novelTags(long userId,
                                                                       @NotNull SimpleParam param,
                                                                       @NotNull PixivCookieToken pixivCookieToken,
                                                                       @NotNull OkHttpClient client) {
        return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue)
                , Pixiv.DOMAIN + "/ajax/user/%d/novels/tags", userId), client);
    }

}   
