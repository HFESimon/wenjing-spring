package com.wenjing.service;

import com.spring.annotation.Component;
import com.spring.beans.factory.BeanPostProcessor;
import com.wenjing.annotation.WenjingValue;

import java.lang.reflect.Field;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/2/29 4:11 上午
 */

@Component
public class WenjingValueBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(WenjingValue.class)) {
                field.setAccessible(true);
                try {
                    field.set(bean, field.getAnnotation(WenjingValue.class).value());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }
}
