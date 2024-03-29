package com.gin.pixiv.params.follow;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 添加关注的参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 15:03
 */
@Getter
@Setter
public class FollowDelParam {
    final String mode = "del";
    final String type = "bookuser";
    @JsonProperty("id")
    long userId;

    public FollowDelParam(long userId) {
        this.userId = userId;
    }
}
