package com.wenjing.test;

import com.spring.WjSpringApplicationContext;
import com.wenjing.config.AppConfig;
import com.wenjing.service.OrderInterface;
import com.wenjing.service.UserService;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/2/28 12:38 上午
 */

public class MainTest {

    public static void main(String[] args) {

        // 扫描 --> 创建单例 Bean
        WjSpringApplicationContext wjSpringApplicationContext = new WjSpringApplicationContext(AppConfig.class);

        UserService userService = (UserService) wjSpringApplicationContext.getBean("userService");
        userService.test();

        OrderInterface orderInterface = (OrderInterface) wjSpringApplicationContext.getBean("orderService");
        orderInterface.test();
    }
}
