package org.gin.pixiv.response.body;

import lombok.Getter;
import lombok.Setter;
import org.gin.response.fields.TagTranslation;

import java.util.HashMap;
import java.util.List;

/**
 * 搜索结果body对象中的公共字段
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 13:04
 */
@Getter
@Setter
public class BaseSearchBody {
    /**
     * 相关标签
     */
    List<String> relatedTags;
    /**
     * 标签翻译
     */
    HashMap<String, TagTranslation> tagTranslation;
}   
