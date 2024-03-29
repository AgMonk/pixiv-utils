package com.gin.pixiv.response.body.novel;

import com.gin.pixiv.response.body.common.BaseWorkBody;
import lombok.Getter;
import lombok.Setter;

/**
 * 小说body的公共字段
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 13:00
 */
@Getter
@Setter
public class BaseNovelBody extends BaseWorkBody {
    Integer bookmarkCount;
    /**
     * 头像链接
     */
    String profileImageUrl;
    /**
     * 阅读时长
     */
    Integer readingTime;
    String url;
    /**
     * 字数(?)
     */
    Integer wordCount;
}
