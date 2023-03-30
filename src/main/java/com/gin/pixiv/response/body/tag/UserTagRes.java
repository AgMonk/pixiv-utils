package com.gin.pixiv.response.body.tag;

import com.gin.pixiv.response.PixivResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
