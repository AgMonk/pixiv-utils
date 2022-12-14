package org.gin.emuns;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.lang.reflect.Type;

/**
 * ζεΊθ§ε
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 14:47
 */
public class PixivIllustSearchModeSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) {
        PixivIllustSearchMode workLang = (PixivIllustSearchMode) o;
        jsonSerializer.out.writeString(workLang.value);
    }
}

