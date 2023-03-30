package com.gin.pixiv.api;

import com.gin.pixiv.call.PixivCallStandard;
import com.gin.pixiv.call.PixivCallType;
import com.gin.pixiv.callback.StandardCallback;
import com.gin.pixiv.callback.TypeCallback;
import com.gin.pixiv.enums.ParamType;
import com.gin.pixiv.main.PixivClient;
import com.gin.pixiv.params.follow.FollowAddParam;
import com.gin.pixiv.params.follow.FollowDelParam;
import com.gin.pixiv.params.user.UserRecommendParam;
import com.gin.pixiv.response.body.user.UserInfoBody;
import com.gin.pixiv.response.body.user.UserRecommendBody;
import com.gin.utils.JsonUtils;
import com.gin.utils.MapUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 用户API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/30 09:35
 */
@RequiredArgsConstructor
public class UserApi {
    private final PixivClient client;

    /**
     * 推荐用户
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.pixiv.response.body.user.UserRecommendBody>
     * @since 2023/3/30 9:39
     */
    public PixivCallStandard<UserRecommendBody> getRecommend(long userId, @NotNull UserRecommendParam param) {
        return client.standard(String.format("/ajax/user/%d/recommends", userId), UserRecommendBody.class, param);
    }

    /**
     * 查询用户信息
     * @param userId   用户id
     * @param fullData 是否请求完整数据
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.pixiv.response.body.user.UserInfoBody>
     * @since 2023/3/30 10:18
     */
    public PixivCallStandard<UserInfoBody> getUserInfo(long userId, boolean fullData) {
        return client.standard("/ajax/user/" + userId, UserInfoBody.class, MapUtils.singleEntry("full", fullData ? 1 : 0));
    }

    /**
     * 关注用户
     * @param param 参数
     * @return org.gin.pixiv.call.PixivCallType<java.lang.String>
     * @since 2023/3/30 9:39
     */
    public PixivCallType<String> postFollow(@NonNull FollowAddParam param) {
        return client.type(ParamType.FORM, "/bookmark_add.php", String.class, param);
    }

    /**
     * 取消关注
     * @param userId 用户id
     * @return org.gin.pixiv.call.PixivCallType<java.lang.String>
     * @since 2023/3/30 9:47
     */
    public PixivCallType<String> postFollowCancel(long userId) {
        return client.type(ParamType.FORM, "/rpc_group_setting.php", String.class, new FollowDelParam(userId));
    }

    public void zTest() {
        long userId = 20670838L;

        zTestUserInfo(userId);
        zTestFollow(userId);
        zTestRecommend(userId);
    }

    private void zTestFollow(long userId) {
        postFollowCancel(userId).async(new TypeCallback<String>() {
            @Override
            public void onSuccess(String body) {
                System.out.println("已取消关注 uid :" + userId);
                postFollow(new FollowAddParam(userId)).async(new TypeCallback<String>() {
                    @Override
                    public void onSuccess(String body) {
                        System.out.println("已关注 uid :" + userId);
                    }
                });
            }
        });
    }

    private void zTestRecommend(long userId) {
        getRecommend(userId, new UserRecommendParam()).async(new StandardCallback<UserRecommendBody>() {
            @Override
            public void onSuccess(UserRecommendBody body) {
                final List<UserRecommendBody.RecommendUser> recommendUsers = body.getRecommendUsers();
                final UserRecommendBody.RecommendUser recommendUser = recommendUsers.get(0);
                System.out.printf("[推荐用户] uid: %d , 作品 %s \n", recommendUser.getUserId(), recommendUser.getIllustIds());
            }
        });
    }

    private void zTestUserInfo(long userId) {
        getUserInfo(userId, true).async(new StandardCallback<UserInfoBody>() {
            @Override
            public void onSuccess(UserInfoBody body) {
                JsonUtils.printJson(body);
            }
        });
    }

}   
