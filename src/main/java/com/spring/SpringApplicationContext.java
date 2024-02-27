package com.spring;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/2/28 12:39 上午
 */


public class SpringApplicationContext {

    private Class configClass;

    public SpringApplicationContext(Class configClass) {
        this.configClass = configClass;
    }

    public Object getBean(String beanName){
        return null;
    }
}
