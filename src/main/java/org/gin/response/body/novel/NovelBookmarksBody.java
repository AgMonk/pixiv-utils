package org.gin.response.body.novel;

import lombok.Getter;
import lombok.Setter;
import org.gin.response.fields.NovelInfo;

import java.util.List;

/**
 * 用户收藏body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 14:35
 **/
@Getter
@Setter
public class NovelBookmarksBody {
    Long total;
    List<NovelInfo> works;
}
