package com.github.yws179.business.common.annotation.desensitized;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.yws179.business.common.utils.desensitize.DesensitizedType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 敏感字段注解
 * 用于标记Json序列化时，需要进行脱敏处理的字段
 * <a href="https://github.com/yws179/business-common-library/blob/master/docs/Desensitize%20Utils.md">查看文档</a>
 * @author weisen.yan
 * @date 2021/7/23
 */
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizedFieldSerializer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface DesensitizedField {

    DesensitizedType type();

}
