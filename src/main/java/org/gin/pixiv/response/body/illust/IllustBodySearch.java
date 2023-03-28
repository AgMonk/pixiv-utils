package org.gin.pixiv.response.body.illust;

import lombok.Getter;
import lombok.Setter;
import org.gin.pixiv.response.body.BaseSearchBody;
import org.gin.response.PixivResponse;
import org.gin.response.fields.Popular;

import java.util.List;

/**
 * 绘画作品的搜索结果body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 13:05
 */
@Getter
@Setter
public class IllustBodySearch extends BaseSearchBody {
    IllustManga illustManga;
    Popular popular;

    @Getter
    @Setter
    public static class IllustManga {
        Integer total;
        List<IllustBodySimple> data;
    }

    public static class Res extends PixivResponse<IllustBodySearch> {
    }
}   
