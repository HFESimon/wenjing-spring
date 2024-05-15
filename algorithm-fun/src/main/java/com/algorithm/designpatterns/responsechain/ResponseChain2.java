package com.algorithm.designpatterns.responsechain;

import com.algorithm.designpatterns.responsechain.Handler;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/15 7:09 下午
 */
public class ResponseChain2 extends Handler {
    @Override
    public void handle(int req) {
        if (req >= 5 && req <= 10) {
            System.out.println("ConcreteHandler2 handled request: " + req);
        } else if (successHandler != null) {
            successHandler.handle(req);
        }
    }
}
