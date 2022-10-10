package org.gin.response.fields;

import lombok.Data;

/**
 * 基础用户信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 15:46
 **/
@Data
public class BaseUserInfo {
    /**
     * 头像链接
     */
    String profileImg;
    /**
     * 用户id
     */
    Long userId;
    /**
     * 用户昵称
     */
    String userName;
}
