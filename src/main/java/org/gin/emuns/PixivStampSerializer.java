package org.gin.emuns;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Type;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/17 13:35
 */
@Getter
@Setter
@RequiredArgsConstructor
public class PixivStampSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) {
        PixivStamp stamp = (PixivStamp) o;
        jsonSerializer.out.writeInt(stamp.id);
    }
}
