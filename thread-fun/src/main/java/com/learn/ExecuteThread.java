package com.learn;

import com.learn.construct.UseCallable;
import com.learn.construct.UseRunnable;
import com.learn.construct.UseThread;

import java.util.concurrent.FutureTask;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/12 1:46 上午
 */
public class ExecuteThread {

    public static void main(String[] args) {
        System.out.println("main thread start..." + Thread.currentThread().getName());

        UseThread useThread = new UseThread();
        useThread.start();

        UseRunnable useRunnable = new UseRunnable();
        new Thread(useRunnable).start();


        UseCallable useCallable = new UseCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(useCallable);
        new Thread(futureTask).start();
    }
}
