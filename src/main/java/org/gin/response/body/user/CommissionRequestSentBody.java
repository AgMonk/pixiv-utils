package org.gin.response.body.user;

import lombok.Getter;
import lombok.Setter;
import org.gin.response.fields.CommissionRequest;
import org.gin.response.fields.TagTranslation;
import org.gin.response.fields.Thumbnails;

import java.util.HashMap;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 15:48
 **/
@Getter
@Setter
public class CommissionRequestSentBody {
    RequestInfo page;
    List<CommissionRequest> requests;
    HashMap<String, TagTranslation> tagTranslation;
    Thumbnails thumbnails;
    List<UserInfoBody> users;

    @Getter
    @Setter
    static class RequestInfo {
        /**
         * 发出的约稿图数量
         */
        Long sentArtworkCount;
        /**
         * 约稿图请求id
         */
        List<Long> sentArtworkRequestIds;
        /**
         * 发出的约稿小说数量
         */
        Long sentNovelCount;
        /**
         * 约稿小说请求id
         */
        List<Long> sentNovelRequestIds;
    }
}
