package com.practice.printABC.join;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/12 7:00 下午
 */
public class ThreadJoinABC {

    public static void printA() {
        System.out.println("A");
    }

    public static void printB() {
        System.out.println("B");
    }

    public static void printC() {
        System.out.println("C");
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            ThreadJoinABC.printA();
        });
        Thread threadB = new Thread(() -> {
            try {
                threadA.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ThreadJoinABC.printB();
        });

        Thread threadC = new Thread(() -> {
            try {
                threadB.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ThreadJoinABC.printC();
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
