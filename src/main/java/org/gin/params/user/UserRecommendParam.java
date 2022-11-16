package org.gin.params.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/24 09:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRecommendParam {
    Integer userNum = 20;
    Integer workNum = 3;
    Boolean isR18 = true;
}
