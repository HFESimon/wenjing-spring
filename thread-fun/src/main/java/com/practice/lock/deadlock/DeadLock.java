package com.practice.lock.deadlock;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/10 2:39 下午
 */
public class DeadLock {

    public static final Object lockA = new Object();

    public static final Object lockB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("t1 获取到lockA");

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                synchronized (lockB) {
                    System.out.println("t1 获取到lockB");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized(lockB) {
                System.out.println("t2 获取到lockB");

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }

                synchronized (lockA) {
                    System.out.println("t2 获取到lockA");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
