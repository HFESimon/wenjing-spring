package com.learn.construct;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/12 1:49 上午
 */

public class UseThread extends Thread{

    @Override
    public void run() {
        System.out.println("new thread..." + Thread.currentThread().getName());
    }
}
