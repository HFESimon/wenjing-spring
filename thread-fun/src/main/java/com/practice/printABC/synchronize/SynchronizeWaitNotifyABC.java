package com.practice.printABC.synchronize;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/17 11:25 下午
 */
public class SynchronizeWaitNotifyABC {

    private static final Object lock = new Object();

    private static boolean flag_a = true;

    private static boolean flag_b = false;

    private static boolean flag_c = false;

    public static void printA() {

        synchronized (lock) {
            while (!flag_a) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("A");
            flag_a = false;
            flag_b = true;
            flag_c = false;
            lock.notifyAll();
        }
    }

    public static void printB() {

        synchronized (lock) {
            while (!flag_b) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("B");
            flag_a = false;
            flag_b = false;
            flag_c = true;
            lock.notifyAll();
        }
    }

    public static void printC() {

        synchronized (lock) {
            while (!flag_c) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C");
            flag_a = true;
            flag_b = false;
            flag_c = false;
            lock.notifyAll();
        }
    }
}
