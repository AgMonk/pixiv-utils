package org.gin.params.user;

import lombok.Data;

/**
 * 查询用户信息参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 17:41
 **/
@Data
public class UserInfoParam {
    /**
     * 是否返回更多字段
     */
    String full = "1";
    String lang = "zh";
}
