package com.dubbo.framework.protocol.factory;

import com.dubbo.framework.protocol.Protocol;
import com.dubbo.framework.protocol.dubbo.DubboProtocol;
import com.dubbo.framework.protocol.http.HttpProtocol;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 6:22 下午
 */
public class ProtocolFactory {

    public static Protocol getProtocol(String name) {

        if (name == null || name.length() == 0) {
            name = "dubbo";
        }

        switch (name) {
            case "dubbo":
                return new DubboProtocol();
            case "http":
                // 没实现也用这个吧
                return new HttpProtocol();
            default:
                break;
        }
        return new DubboProtocol();
    }
}
