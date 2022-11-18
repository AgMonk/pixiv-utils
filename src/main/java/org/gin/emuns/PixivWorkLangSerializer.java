package org.gin.emuns;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.lang.reflect.Type;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 15:40
 */
public class PixivWorkLangSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) {
        PixivWorkLang workLang = (PixivWorkLang) o;
        jsonSerializer.out.writeString(workLang.name);
    }
}
