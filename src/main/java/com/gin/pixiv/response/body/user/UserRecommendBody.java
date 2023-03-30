package com.gin.pixiv.response.body.user;

import com.gin.pixiv.response.body.common.Thumbnails;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户推荐
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/24 09:03
 */
@Getter
@Setter
public class UserRecommendBody {
    Thumbnails thumbnails;
    List<RecommendUser> recommendUsers;
    List<UserInfoBody> users;

    @Getter
    @Setter
    public static class RecommendUser {
        Long userId;
        List<Long> illustIds;
        List<Long> novelIds;
    }
}
