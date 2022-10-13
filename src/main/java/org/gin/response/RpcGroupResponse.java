package org.gin.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 10:33
 **/
@Data
public class RpcGroupResponse {
    @JSONField(alternateNames = "user_id")
    Long userId;
    String type;
}
