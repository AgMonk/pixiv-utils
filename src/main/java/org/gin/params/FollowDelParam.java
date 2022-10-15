package org.gin.params;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 添加关注的参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 15:03
 */
@Data
public class FollowDelParam {
    final String mode = "del";
    final String type = "bookuser";
    @JSONField(name = "id")
    long userId;

    public FollowDelParam(long userId) {
        this.userId = userId;
    }
}
