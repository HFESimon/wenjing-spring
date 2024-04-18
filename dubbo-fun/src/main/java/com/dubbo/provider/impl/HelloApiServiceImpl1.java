package com.dubbo.provider.impl;

import com.dubbo.provider.api.HelloApiService;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 12:33 上午
 */
public class HelloApiServiceImpl1 implements HelloApiService {

    @Override
    public String sayHello(String name) {
        return "execute api serviceImpl1 " + name;
    }
}
