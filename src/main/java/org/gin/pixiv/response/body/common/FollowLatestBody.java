package org.gin.pixiv.response.body.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 关注作者最新作品body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 16:52
 */
@Getter
@Setter
public class FollowLatestBody {
    Page page;
    Thumbnails thumbnails;
    Object tagTranslation;

    @Getter
    @Setter
    static class Page {
        List<Long> ids;
        Boolean isLastPage;
    }
}   
