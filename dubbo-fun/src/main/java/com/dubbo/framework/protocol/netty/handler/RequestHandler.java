package com.dubbo.framework.protocol.netty.handler;

import com.dubbo.framework.protocol.dto.Invocation;
import com.dubbo.framework.register.LocalRegister;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.Method;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 10:52 上午
 */
public class RequestHandler implements ChannelHandler{

    @Override
    public void handler(ChannelHandlerContext ctx, Invocation invocation) throws Exception {

        Class<?> clazz = LocalRegister.get(invocation.getInterfaceName());

        Method method = clazz.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
        Object result = method.invoke(clazz.newInstance(), invocation.getParameters());

        ctx.writeAndFlush("Netty: " + result);
    }
}
