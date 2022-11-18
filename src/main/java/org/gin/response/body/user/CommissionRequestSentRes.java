package org.gin.response.body.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.gin.response.PixivResponse;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/18 11:50
 */
@Getter
@Setter
@RequiredArgsConstructor
public class CommissionRequestSentRes extends PixivResponse<CommissionRequestSentBody> {

}   
