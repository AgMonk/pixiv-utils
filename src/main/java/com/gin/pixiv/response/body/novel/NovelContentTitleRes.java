package com.gin.pixiv.response.body.novel;

import com.gin.pixiv.response.PixivResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 16:04
 */
@Getter
@Setter
public class NovelContentTitleRes extends PixivResponse<List<NovelContentTitleBody>> {
}
