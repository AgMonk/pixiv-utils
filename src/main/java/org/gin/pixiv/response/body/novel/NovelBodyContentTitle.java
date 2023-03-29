package org.gin.pixiv.response.body.novel;

import lombok.Getter;
import lombok.Setter;

/**
 * 小说系列的各话标题
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 17:08
 */
@Getter
@Setter
public class NovelBodyContentTitle {
    Boolean available;
    Long id;
    String title;

    @Getter
    @Setter
    public abstract static class List implements java.util.List<NovelBodyContentTitle> {

    }
}   
