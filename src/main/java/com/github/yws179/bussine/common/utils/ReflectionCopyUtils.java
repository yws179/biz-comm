package com.github.yws179.bussine.common.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.yws179.bussine.common.annotation.CopyIgnore;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weisen yan
 */
public class ReflectionCopyUtils {

	public static void copyJsonProperties(Object source, Object target) {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		List<String> ignoreProperties = new ArrayList<String>();
		Class<?> clazz = target.getClass();
		while (clazz != null) {
			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(JsonIgnore.class) || field.isAnnotationPresent(CopyIgnore.class)) {
					ignoreProperties.add(field.getName());
				}
			}
			clazz = clazz.getSuperclass();
		}

		BeanUtils.copyProperties(source, target, ignoreProperties.toArray(new String[ignoreProperties.size()]));
	}
}
