package org.gin.api.groups;

import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import org.gin.api.PixivApi;
import org.gin.params.follow.FollowAddParam;
import org.gin.params.follow.FollowDelParam;
import org.gin.params.user.UserRecommendParam;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.body.user.UserInfoRes;
import org.gin.response.body.user.UserRecommendBody;
import org.gin.response.body.user.UserRecommendRes;
import org.gin.response.callback.BaseCallback;
import org.gin.response.convertor.Convertor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import static org.gin.request.PixivRequestBody.createFormBody;

/**
 * 用户API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 17:22
 */
@RequiredArgsConstructor
public class UserApi {
    private final PixivApi api;

    /**
     * 关注用户
     * @param param 参数
     * @return org.gin.request.PixivRequest<java.lang.String>
     * @since 2022/11/16 17:28
     */
    public PixivRequest<String> follow(@NonNull FollowAddParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/bookmark_add.php")
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, String.class), createFormBody(param));
    }

    /**
     * 推荐用户
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.request.PixivRequest<UserRecommendRes>
     * @since 2022/11/16 17:27
     */
    public PixivRequest<UserRecommendRes> recommend(long userId, @NotNull UserRecommendParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/recommends", userId)
                .setParams(param)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, UserRecommendRes.class));
    }

    /**
     * 取消关注
     * @param userId 用户id
     * @return org.gin.request.PixivRequest<java.lang.String>
     * @since 2022/10/17 15:49
     */
    public PixivRequest<String> unfollow(long userId) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/rpc_group_setting.php")
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, String.class), createFormBody(new FollowDelParam(userId)));
    }

    /**
     * 查询用户信息
     * @param userId   用户id
     * @param fullData 是否查询完整信息
     * @return org.gin.request.PixivRequest<org.gin.response.body.user.UserInfoRes>
     * @since 2022/11/16 17:25
     */
    public PixivRequest<UserInfoRes> userInfo(long userId, boolean fullData) {

        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/" + userId)
                .addParam("full", fullData ? 1 : 0)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, UserInfoRes.class));
    }


    public void zTest() {
        long userId = 20670838L;

        zTestUserInfo(userId);
        zTestFollow(userId);
        zTestRecommend(userId);
    }

    private void zTestFollow(long userId) {
        unfollow(userId).async(new BaseCallback<String>() {
            @Override
            public String convert(ResponseBody responseBody) throws IOException {
                return responseBody.string();
            }

            @Override
            public void onSuccess(String res) {
                System.out.println("已取消关注 uid :" + userId);
                follow(new FollowAddParam(userId)).async(new BaseCallback<String>() {
                    @Override
                    public String convert(ResponseBody responseBody) throws IOException {
                        return responseBody.string();
                    }

                    @Override
                    public void onSuccess(String res) {
                        System.out.println("已关注 uid :" + userId);
                    }
                });
            }
        });
    }

    private void zTestRecommend(long userId) {
        recommend(userId, new UserRecommendParam()).async(new BaseCallback<UserRecommendRes>() {
            @Override
            public UserRecommendRes convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), UserRecommendRes.class);
            }

            @Override
            public void onSuccess(UserRecommendRes res) {
                final List<UserRecommendBody.RecommendUser> recommendUsers = res.getBody().getRecommendUsers();
                final UserRecommendBody.RecommendUser recommendUser = recommendUsers.get(0);
                System.out.printf("[推荐用户] uid: %d , 作品 %s \n", recommendUser.getUserId(), recommendUser.getIllustIds());

            }
        });
    }

    private void zTestUserInfo(long userId) {
        userInfo(userId, true).async(new BaseCallback<UserInfoRes>() {
            @Override
            public UserInfoRes convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), UserInfoRes.class);
            }

            @Override
            public void onSuccess(UserInfoRes res) {
                final String name = res.getBody().getName();
                System.out.printf("[用户信息] 用户 :%s 昵称：%s \n", userId, name);
            }
        });
    }
}
