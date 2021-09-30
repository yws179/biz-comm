package com.github.yws179.business.common.annotation.desensitized;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.github.yws179.business.common.utils.desensitize.DesensitizedType;
import com.github.yws179.business.common.utils.desensitize.DesensitizedUtils;

import java.io.IOException;

/**
 * Jackson 序列化器 - 脱敏字段
 * @author weisen.yan
 * @date 2021/7/26
 */
public class DesensitizedFieldSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private DesensitizedType desensitizedType;

    public DesensitizedFieldSerializer() {}

    public DesensitizedFieldSerializer(DesensitizedType desensitizedType) {
        this.desensitizedType = desensitizedType;
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (desensitizedType != null) {
            gen.writeString(DesensitizedUtils.desensitized(value, desensitizedType));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        if (property != null) {
            DesensitizedField desensitizedField = property.getAnnotation(DesensitizedField.class);
            if (desensitizedField != null) {
                return new DesensitizedFieldSerializer(desensitizedField.type());
            }
        }
        return null;
    }
}
