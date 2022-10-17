package org.gin.params.follow;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 添加关注的参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 15:03
 */
@Data
public class FollowAddParam {
    final String mode = "add";
    final String type = "user";
    final String format = "json";
    @JSONField(name = "user_id")
    long userId;
    String tag;
    int restrict = 0;

    public FollowAddParam(long userId, String tag, int restrict) {
        this.userId = userId;
        this.tag = tag;
        this.restrict = restrict;
    }

    public FollowAddParam(long userId) {
        this.userId = userId;
    }
}
