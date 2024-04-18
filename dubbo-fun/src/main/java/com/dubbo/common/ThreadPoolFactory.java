package com.dubbo.common;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 11:25 上午
 */
public class ThreadPoolFactory {
    // 线程池保持的最小线程数
    private static final Integer CORE_POOL_SIZE = 10;
    // 线程池最大线程数
    private static final Integer MAX_POOL_SIZE = 20;
    // 大于core的线程空闲回收时间
    private static final Long KEEP_ALIVE_TIME = 0L;
    // 阻塞队列长度
    public static final int CAPACITY = 2000;

    public static ExecutorService createThreadPool(String name) {
        return doCreateThreadPool(name, CORE_POOL_SIZE, MAX_POOL_SIZE);
    }

    public static ExecutorService doCreateThreadPool(String name, Integer corePoolSize, Integer maximumPoolSize) {
        return new ThreadPoolExecutor(corePoolSize,
                                      maximumPoolSize,
                                      KEEP_ALIVE_TIME,
                                      TimeUnit.MILLISECONDS,
                                      // 阻塞队列
                                      new ArrayBlockingQueue<>(CAPACITY),
                                      // 线程工厂
                                      new MyThreadFactory(name),
                                      // 拒绝策略
                                      new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    static class MyThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public MyThreadFactory(String poolName) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = poolName + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            return thread;
        }
    }
}
