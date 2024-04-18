package com.dubbo.framework.protocol.common;

import com.dubbo.framework.protocol.dubbo.DubboProtocol;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 6:22 下午
 */
public class ProtocolFactory {

    public static Protocol getProtocol() {
        String name = System.getProperty("protocol");
        if (name == null || name.length() == 0) {
            name = "dubbo";
        }

        switch (name) {
            case "dubbo":
                return new DubboProtocol();
            case "http":
                // 没实现也用这个吧
                return new DubboProtocol();
            default:
                break;
        }
        return new DubboProtocol();
    }
}
