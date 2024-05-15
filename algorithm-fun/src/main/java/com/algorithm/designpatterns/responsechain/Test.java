package com.algorithm.designpatterns.responsechain;

import com.algorithm.designpatterns.responsechain.Handler;
import com.algorithm.designpatterns.responsechain.ResponseChain1;
import com.algorithm.designpatterns.responsechain.ResponseChain2;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/15 7:09 下午
 */
public class Test {

    public static void main(String[] args) {
        Handler handler1 = new ResponseChain1();
        Handler handler2 = new ResponseChain2();

        handler1.setSuccessHandler(handler2);

        for(int i = 0; i < 15; i++) {
            handler1.handle(i);
        }
    }
}
