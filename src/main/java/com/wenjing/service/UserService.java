package com.wenjing.service;

import com.spring.annotation.Autowired;
import com.spring.annotation.Component;
import com.spring.annotation.Scope;
import com.spring.beans.BeanDefinition;
import com.spring.beans.factory.InitializingBean;
import com.wenjing.annotation.WenjingValue;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/2/28 12:38 上午
 */

@Component("userService")
@Scope(BeanDefinition.ScopeType.PROTOTYPE)
public class UserService implements InitializingBean {

//    @Autowired
//    private OrderService orderService;

    @WenjingValue("test")
    private String name;

    public void test() {
        System.out.println(name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("initializing bean");
    }
}
