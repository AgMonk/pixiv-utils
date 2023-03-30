package org.gin.pixiv.response.body.tag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.gin.pixiv.response.PixivResponse;

import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/18 11:50
 */
@Getter
@Setter
@RequiredArgsConstructor
public class UserTagRes extends PixivResponse<List<UserTag>> {

}   
