package org.gin.response.fields;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 15:55
 **/
@Getter
@Setter
public class Thumbnails {
    List<ArtworkInfo> illust;
    List<NovelInfo> novel;

}
