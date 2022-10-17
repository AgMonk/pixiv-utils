package org.gin.response.body;

import lombok.Data;
import org.gin.response.fields.NovelInfo;
import org.gin.response.fields.TagTranslation;

import java.util.HashMap;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/12 17:39
 **/
@Data
public class NovelSearchBody {
    Novel novel;
    List<String> relatedTags;
    HashMap<String, TagTranslation> tagTranslation;


    @Data
    static class Novel {
        List<NovelInfo> data;
        Integer total;
    }

}
