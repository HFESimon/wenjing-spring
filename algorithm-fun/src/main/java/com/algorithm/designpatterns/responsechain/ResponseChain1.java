package com.algorithm.designpatterns.responsechain;

import com.algorithm.designpatterns.responsechain.Handler;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/15 7:06 下午
 */
public class ResponseChain1 extends Handler {

    @Override
    public void handle(int req) {
        if (req >= 1 && req < 5) {
            System.out.println("ConcreteHandler1 handled request: " + req);
        } else if (successHandler != null) {
            successHandler.handle(req);
        }
    }
}
