package org.gin.response.fields;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 收藏数据
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 15:29
 **/
@Data
public class BookmarkData {
    /**
     * 收藏id
     */
    Long id;
    /**
     * 私有收藏
     */
    @JSONField(alternateNames = "private")
    boolean pri;
}
