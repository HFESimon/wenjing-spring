package com.wenjing.service;

import com.spring.annotation.Component;
import com.spring.beans.factory.BeanNameAware;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/2/28 12:38 上午
 */

@Component
public class OrderService implements OrderInterface, BeanNameAware {

    /**
     * beanName
     */
    private String beanName;

    @Override
    public void test() {
        System.out.println("test");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
