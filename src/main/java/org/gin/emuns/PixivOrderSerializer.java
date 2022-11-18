package org.gin.emuns;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.lang.reflect.Type;

/**
 * 排序规则
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 14:47
 */
public class PixivOrderSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) {
        PixivOrder workLang = (PixivOrder) o;
        jsonSerializer.out.writeString(workLang.name);
    }
}

