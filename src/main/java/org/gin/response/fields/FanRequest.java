package org.gin.response.fields;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 粉丝约稿信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 15:42
 **/
@Getter
@Setter
public class FanRequest {
    HashMap<String, Serializable> collaborateStatus;
    /**
     * 约稿方
     */
    BaseUserInfo fan;
    /**
     * 约稿请求
     */
    FanRequestInfo request;

    @Getter
    @Setter
    static class FanRequestInfo {
        Long requestId;
    }
}
