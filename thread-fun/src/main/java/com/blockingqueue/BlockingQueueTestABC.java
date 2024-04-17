package com.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/17 11:16 下午
 */
public class BlockingQueueTestABC {

    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void printA() {
        queue.offer("A");
        System.out.println("A");
    }

    public static void printB() {
        while (queue.size() != 1) {
        }
        queue.offer("B");
        System.out.println("B");
    }

    public static void printC() {
        while (queue.size() != 2) {
        }
        queue.offer("C");
        System.out.println("C");
    }
}
