package com.wenjing.service;

import com.spring.annotation.Component;
import com.spring.beans.factory.BeanPostProcessor;
import com.wenjing.annotation.WenjingValue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/2/29 3:30 上午
 */

@Component
public class WjBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (beanName.equals("orderService")) {
            Object proxyInstance = Proxy.newProxyInstance(WjBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // 切面
                    System.out.println("aop 逻辑");

                    return method.invoke(bean, args);
                }
            });
            return proxyInstance;
        }
        return bean;
    }
}
