package org.gin.response.body.user;

import lombok.Getter;
import lombok.Setter;
import org.gin.response.fields.NovelInfo;

import java.util.HashMap;

/**
 * 用户作品Body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 14:01
 **/
@Getter
@Setter
public class ProfileNovelsBody {
    HashMap<Long, NovelInfo> works;
}
