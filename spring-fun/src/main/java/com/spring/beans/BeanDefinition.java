package com.spring.beans;

import lombok.Data;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description: Bean定义
 * @since 2024/2/28 2:11 上午
 */

@Data
public class BeanDefinition {

    /**
     * 先这么写吧
     */
    public static class ScopeType {
        public static final String SINGLETON = "singleton";
        public static final String PROTOTYPE = "prototype";
    }

    /**
     * 类型
     */
    private Class type;

    /**
     * 作用域 singleton, prototype
     */
    private String scope;

    /**
     * 是否懒加载
     */
    private boolean isLazy;
}
