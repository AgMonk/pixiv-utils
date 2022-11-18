package org.gin.request;

import com.alibaba.fastjson.serializer.SerializerFeature;
import okhttp3.HttpUrl;
import org.gin.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * HttpUrl工具类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 15:21
 */
public class PixivUrl {

    public static class Builder {
        HttpUrl httpUrl;
        HashMap<String, Object> params = new HashMap<>();
        List<SerializerFeature> features = new ArrayList<>();

        /**
         * 添加Feature
         * @param serializerFeature serializerFeature
         * @return this.
         */
        public Builder addFeature(SerializerFeature serializerFeature) {
            this.features.add(serializerFeature);
            return this;
        }

        /**
         * 添加参数
         * @param key   key
         * @param value value
         * @return this
         */
        public Builder addParam(String key, Object value) {
            this.params.put(key, value);
            return this;
        }

        public HttpUrl build() {
            if (this.httpUrl == null) {
                throw new RuntimeException("必须提供URL");
            }
            final HttpUrl.Builder builder = this.httpUrl.newBuilder();
            this.params.forEach((k, v) -> {
                if (v instanceof Collection<?>) {
                    //如果value 是集合，添加多个同名参数
                    ((Collection<?>) v).forEach(i -> {
                        builder.addQueryParameter(k, String.valueOf(i));
                    });
                } else if (v == null) {
                    if (features.contains(SerializerFeature.WriteMapNullValue)) {
                        //如果value是null 且传入了该值则把null值写成空串
                        builder.addQueryParameter(k, "");
                    }
                } else {
                    //常规写入
                    builder.addQueryParameter(k, v.toString());
                }
            });
            return builder.build();
        }

        /**
         * 设置参数map
         * @param o 对象
         * @return this
         */
        public Builder setParams(Object o) {
            this.params = JsonUtils.jsonToMap(o);
            return this;
        }

        public Builder setUrl(String urlTemplate, Object... args) {
            this.httpUrl = HttpUrl.parse(String.format(urlTemplate, args));
            return this;
        }
    }
}
