package org.gin.pixiv.response.body.user;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.gin.pixiv.enums.WorkType;
import org.gin.pixiv.response.body.illust.IllustBodySimple;
import org.gin.pixiv.response.body.novel.NovelBodySimple;
import org.gin.pixiv.response.body.novel.NovelSeriesBody;
import org.gin.utils.JsonUtils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户作品概况 body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 11:50
 **/
@Getter
@Setter
public class ProfileAllBody {
    /**
     * 插画信息
     */
    LinkedHashMap<Long, Object> illusts;
    /**
     * 漫画信息
     */
    LinkedHashMap<Long, Object> manga;
    /**
     * 小说信息
     */
    LinkedHashMap<Long, Object> novels;
    /**
     * 小说系列信息
     */
    List<NovelSeriesBody> novelSeries;
    /**
     * 漫画系列信息
     */
    List<MangaSeriesInfo> mangaSeries;
    /**
     * 首页精选推荐, 实际类型可能为 {@link  IllustBodySimple} 或 {@link NovelBodySimple}
     */
    @JsonDeserialize(using = PickupDeserializer.class)
    List<Object> pickup;


    public static class PickupDeserializer extends JsonDeserializer<List<Object>> {
        @Override
        public List<Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            final List<LinkedHashMap<String, Object>> treeNode = jsonParser.getCodec().readValue(jsonParser,
                                                                                                 new TypeReference<List<LinkedHashMap<String, Object>>>() {
                                                                                                 });
            return treeNode.stream()
                    .map(node -> {
                        final String type = node.get("type").toString();
                        try {
                            if (WorkType.ILLUST.equals(type)) {
                                return JsonUtils.parseObject(node, IllustBodySimple.class);
                            }
                            if (WorkType.NOVEL.equals(type)) {
                                return JsonUtils.parseObject(node, NovelBodySimple.class);
                            }
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
    }
}
