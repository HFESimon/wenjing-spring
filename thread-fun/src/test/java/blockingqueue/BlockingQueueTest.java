package blockingqueue;

import com.blockingqueue.BlockingQueueTestABC;
import org.junit.Test;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/17 11:19 下午
 */
public class BlockingQueueTest {

    @Test
    public void test() {

        new Thread(() -> {
            BlockingQueueTestABC.printA();
        }, "thread_a").start();

        new Thread(() -> {
            BlockingQueueTestABC.printB();
        }, "thread_b").start();

        new Thread(() -> {
            BlockingQueueTestABC.printC();
        }, "thread_c").start();
    }
}
