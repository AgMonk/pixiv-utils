package org.gin.response.body.user;

import lombok.Data;
import org.gin.response.fields.Thumbnails;

import java.util.List;

/**
 * 用户推荐
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/24 09:03
 */
@Data
public class UserRecommendBody {
    Thumbnails thumbnails;
    List<RecommendUser> recommendUsers;
    List<UserInfoBody> users;

    @Data
    public static class RecommendUser {
        Long userId;
        List<Long> illustIds;
        List<Long> novelIds;
    }
}
