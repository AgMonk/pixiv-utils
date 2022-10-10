package org.gin.response;

import lombok.Data;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 15:25
 **/
@Data
public class PixivResponse<T> {
    String message;
    boolean error;
    T body;
}
