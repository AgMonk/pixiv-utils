package org.gin.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 17:51
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleParam {
    /**
     * 语言
     */
    String lang = "zh";
}
