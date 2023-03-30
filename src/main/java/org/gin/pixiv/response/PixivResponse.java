package org.gin.pixiv.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 15:25
 **/
@Getter
@Setter
public class PixivResponse<T> {
    T body;
    boolean error = false;
    String message = "";
}
