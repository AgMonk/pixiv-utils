package com.gin.pixiv.response.body.common;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 收藏数据
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 15:29
 **/
@Getter
@Setter
public class BookmarkData {
    /**
     * 收藏id
     */
    Long id;
    /**
     * 私有收藏
     */
    @JsonProperty("private")
    boolean pri;
}
