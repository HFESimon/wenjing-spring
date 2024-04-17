package com.thread;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/17 11:07 下午
 */
public class ThreadLocalABC {

    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

        threadLocal.set(1);
        new Thread(() -> {
            System.out.println("A");
        }, "thread_a").start();
        threadLocal.set(2);
        new Thread(() -> {
            while (threadLocal.get() != 2) {
            }
            System.out.println("B");
        }, "thread_b").start();

        threadLocal.set(3);
        new Thread(() -> {
            while (threadLocal.get() != 3) {
            }
            System.out.println("C");
        }, "thread_c").start();


    }
}
