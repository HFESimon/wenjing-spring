package com.practice.printABC.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/13 1:32 上午
 */
public class LockCondition {

    ReentrantLock lock;

    Condition condition;

    volatile int state = 1;

    public LockCondition() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public void printA() {
        lock.lock();
        try {
            while (state != 1) {
                condition.await();
            }
            System.out.println("A");
            state = 2;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (state != 2) {
                condition.await();
            }
            System.out.println("B");
            state = 3;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (state != 3) {
                condition.await();
            }
            System.out.println("C");
            state = 1;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
