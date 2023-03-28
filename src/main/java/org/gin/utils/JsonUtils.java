package org.gin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.gin.response.PixivResponse;

import java.util.HashMap;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/17 17:25
 */
@Getter
@Setter
@RequiredArgsConstructor
public class JsonUtils {
    public final static ObjectMapper MAPPER = new ObjectMapper();

    static {
//        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //美化输出
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        //反序列化时 空串识别为 null
        MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        //true - 遇到没有的属性就报错 false - 没有的属性不会管，不会报错
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 支持 ZonedDateTime
        MAPPER.registerModule(new JavaTimeModule());
    }


    /**
     * 把对象转换为HashMap
     * @param obj 对象 推荐使用HashMap ，传入null的字段会传递空串
     * @return HashMap
     */
    public static HashMap<String, Object> jsonToMap(Object obj) {
        try {
            return MAPPER.readValue(MAPPER.writeValueAsString(obj), new TypeReference<HashMap<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            return new HashMap<>(0);
        }
    }

    public static String obj2Str(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static <T> T parse(String s, Class<T> clazz) throws JsonProcessingException {
        return MAPPER.readValue(s, clazz);
    }

    public static <T> T parseRes(String s, Class<T> clazz) throws JsonProcessingException {
        final JavaType javaType = JsonUtils.MAPPER.getTypeFactory().constructParametricType(PixivResponse.class, clazz);
        final PixivResponse<T> response = JsonUtils.MAPPER.readValue(s, javaType);
        return response.getBody();
    }


    public static void printJson(Object o) {
        try {
            System.out.println(MAPPER.writeValueAsString(o));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
