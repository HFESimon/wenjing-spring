package com.dubbo.framework.proxy;

import com.dubbo.framework.balance.invoker.ClusterInvoker;
import com.dubbo.framework.protocol.invoker.Invocation;
import com.dubbo.framework.protocol.invoker.Invoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 3:33 下午
 */
public class ProxyFactory<T> {

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class<?> interfaceClass) {

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

                try {
                    Invoker invoker = ClusterInvoker.join(interfaceClass);
                    return invoker.invoke(invocation);
                } catch (Exception e) {
                    e.printStackTrace();
                    return doMock(invocation);
                }
            }
        });
    }

    private static Object doMock(Invocation invocation) {
        return "容错逻辑";
    }

}
