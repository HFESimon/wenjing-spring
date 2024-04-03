package com.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description: 标记为 Spring Bean, Spring启动时会扫描到该注解并实例化到Spring容器中
 * @since 2024/2/28 12:45 上午
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {

    String value() default "";
}
