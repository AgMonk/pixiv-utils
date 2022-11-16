package org.gin.response.fields;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 12:19
 */
@Getter
@Setter
public class Popular {
    /**
     * 长期的
     */
    List<ArtworkInfo> permanent;
    /**
     * 最近的
     */
    List<ArtworkInfo> recent;
}
