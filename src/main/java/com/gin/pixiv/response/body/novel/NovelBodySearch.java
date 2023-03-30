package com.gin.pixiv.response.body.novel;

import com.gin.pixiv.response.body.search.BaseSearchBody;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 小说搜索body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 12:58
 */
@Getter
@Setter
public class NovelBodySearch extends BaseSearchBody {
    Novel novel;

    @Getter
    @Setter
    public static class Novel {
        List<NovelBodySimple> data;
        Integer total;
    }

}   
