package com.wenjing.service;

import com.spring.annotation.Autowired;
import com.spring.annotation.Component;
import com.spring.annotation.Scope;
import com.spring.base.BeanDefinition;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/2/28 12:38 上午
 */

@Component("userService")
@Scope(BeanDefinition.ScopeType.PROTOTYPE)
public class UserService {

    @Autowired
    private OrderService orderService;

    public void test() {
        System.out.println(orderService);
    }
}
