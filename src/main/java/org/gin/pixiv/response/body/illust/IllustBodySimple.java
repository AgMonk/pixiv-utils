package org.gin.pixiv.response.body.illust;

import lombok.Getter;
import lombok.Setter;
import org.gin.response.PixivResponse;

import java.util.List;

/**
 * 绘画作品的简略信息(来自搜索或关注列表)
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 12:40
 */
@Getter
@Setter
public class IllustBodySimple extends BaseIllustBody {

    /**
     * 作品含有的图片数量
     */
    Integer pageCount;
    /**
     * 头像链接
     */
    String profileImageUrl;
    /**
     * 标签
     */
    List<String> tags;
    /**
     * 图片url
     */
    String url;

    @Getter
    @Setter
    public static class Res extends PixivResponse<IllustBodySimple> {

    }
}   
