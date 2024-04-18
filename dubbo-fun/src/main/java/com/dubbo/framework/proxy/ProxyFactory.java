package com.dubbo.framework.proxy;

import com.dubbo.framework.LocalBalance;
import com.dubbo.framework.URL;
import com.dubbo.framework.protocol.factory.ProtocolFactory;
import com.dubbo.framework.protocol.dto.Invocation;
import com.dubbo.framework.protocol.factory.Protocol;
import com.dubbo.framework.register.ZookeeperRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

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
                    List<URL> urls = ZookeeperRegister.get(interfaceClass.getName());
                    URL url = LocalBalance.random(urls);

                    System.out.println("消费者选择的服务提供者地址是："+ url.toString());

                    Protocol protocol = ProtocolFactory.getProtocol();
                    return protocol.send(url, invocation);
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
