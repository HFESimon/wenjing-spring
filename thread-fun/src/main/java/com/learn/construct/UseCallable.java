package com.learn.construct;

import java.util.concurrent.Callable;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/12 1:51 上午
 */
public class UseCallable implements Callable<Integer> {

    private int num;

    @Override
    public Integer call() throws Exception {
        System.out.println("callable start......" + Thread.currentThread().getName());

        for(int i = 0; i < 5000; i++) {
            if(Thread.currentThread().isInterrupted()) {
                System.out.println("callable interrupted......");
                return 0;
            }
            num += i;
        }
        System.out.println("callable end......result is: " + num);
        return num;
    }
}
