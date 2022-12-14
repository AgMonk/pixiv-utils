package org.gin.response.body.illustmanga;

import lombok.Data;
import org.gin.response.fields.ArtworkInfo;

import java.util.List;

/**
 * 用户收藏body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 14:35
 **/
@Data
public class IllustMangaBookmarksBody {
    Long total;
    List<ArtworkInfo> works;
}
