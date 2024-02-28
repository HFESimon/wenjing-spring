package com.wenjing.service;

import com.spring.annotation.Component;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/2/28 12:38 上午
 */

@Component
public class OrderService implements OrderInterface {

    @Override
    public void test() {
        System.out.println("test");
    }
}
