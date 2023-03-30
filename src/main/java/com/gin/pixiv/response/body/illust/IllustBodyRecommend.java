package com.gin.pixiv.response.body.illust;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 推荐作品body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 17:15
 */
@Getter
@Setter
public class IllustBodyRecommend {
    List<IllustBodySimple> illusts;

}   
