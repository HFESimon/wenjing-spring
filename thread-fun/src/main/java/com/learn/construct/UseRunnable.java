package com.learn.construct;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/12 1:56 上午
 */
public class UseRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Runnable....." + Thread.currentThread().getName());
    }
}
