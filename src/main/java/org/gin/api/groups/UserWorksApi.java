package org.gin.api.groups;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.HttpUrl;
import org.gin.api.PixivApi;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.PixivResponse;
import org.gin.response.body.tag.UserTag;
import org.gin.response.body.user.CommissionRequestSentBody;
import org.gin.response.body.user.ProfileIllustsBody;
import org.gin.response.body.user.ProfileNovelsBody;
import org.gin.response.body.user.ProfileRealBody;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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
        return new PixivRequest<>(url, api.getClient());
    }

    /**
     * 查询用户发出的约稿
     * @param userId 用户id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UserCommissionBody>>
     * @since 2022/10/15 14:01
     */
    public PixivRequest<PixivResponse<CommissionRequestSentBody>> commissionRequestSent(long userId) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/commission/page/users/%d/request/sent", userId)
                .build();
        return new PixivRequest<>(url, api.getClient());
    }

    /**
     * 查询用户的绘画信息
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.user.ProfileRealBody>>
     * @since 2022/10/15 11:18
     */
    public PixivRequest<PixivResponse<ProfileIllustsBody>> illusts(long userId, @NotNull List<Long> ids) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/profile/illusts", userId)
                .addParam("is_first_page", 1)
                .addParam("work_category", "illustManga")
                .addParam("ids[]", ids)
                .build();
        return new PixivRequest<>(url, api.getClient());
    }

    /**
     * 查询用户的小说中使用的标签
     * @param userId           用户id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UserCommissionBody>>
     * @since 2022/10/15 14:01
     */
    public PixivRequest<PixivResponse<List<UserTag>>> novelTags(long userId) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/novels/tags", userId)
                .build();
        return new PixivRequest<>(url, api.getClient());
    }

    /**
     * 查询用户的小说
     * @param userId           用户id
     * @param param            参数
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.user.ProfileIllustsBody>>
     * @since 2022/10/17 11:10
     */
    public PixivRequest<PixivResponse<ProfileNovelsBody>> novels(long userId, @NotNull List<Long> ids) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/profile/novels", userId)
                .addParam("ids[]", ids)
                .build();
        return new PixivRequest<>(url, api.getClient());
    }

}   
