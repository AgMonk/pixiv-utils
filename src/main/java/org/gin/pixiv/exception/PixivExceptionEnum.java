package org.gin.pixiv.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常枚举
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 11:00
 **/
@Getter
@AllArgsConstructor
public enum PixivExceptionEnum {
    /**
     * 异常枚举
     */

    URL_CREATE(4000, "URL创建异常"),
    TOKEN_NOT_FOUND(4001, "未能获取到Token"),

    ;


    final int code;
    final String message;
}
