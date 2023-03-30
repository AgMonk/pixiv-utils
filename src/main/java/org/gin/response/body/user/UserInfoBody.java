package org.gin.response.body.user;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 10:52
 **/
@Getter
@Setter
public class UserInfoBody {
    /**
     * 是否接受约稿
     */
    Boolean acceptRequest;
    /**
     * 头像URL
     */
    String image;
    /**
     * 头像URL(大)
     */
    String imageBig;
    /**
     * 自己是否已关注
     */
    Boolean isFollowed;
    /**
     * 昵称
     */
    String name;
    /**
     * 用户id
     */
    Long userId;
    /**
     * 完整信息
     */
    @JsonUnwrapped
    UserInfoBodyFullData fullData;
}
