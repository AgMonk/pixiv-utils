package org.gin.pixiv.response.body.novel;

import lombok.Getter;
import lombok.Setter;
import org.gin.pixiv.response.PixivResponse;

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
