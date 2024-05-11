package com.practice.printABC.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/17 10:59 下午
 */

public class LockConditionABC {

    private static final Lock lock = new ReentrantLock();

    private static int state = 1;

    private static final Condition conditionA = lock.newCondition();

    private static final Condition conditionB = lock.newCondition();

    private static final Condition conditionC = lock.newCondition();

    public static void printA() {
        lock.lock();
        try {
            while (state != 1) {
                conditionA.await();
            }
            System.out.println("A");
            state = 2;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void printB() {
        lock.lock();
        try {
            while (state != 2) {
                conditionB.await();
            }
            System.out.println("B");
            state = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void printC() {
        lock.lock();
        try {
            while (state != 3) {
                conditionC.await();
            }
            System.out.println("C");
            state = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
