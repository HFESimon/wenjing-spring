package synchronize;

import com.practice.printABC.synchronize.SynchronizeWaitNotifyABC;
import org.junit.Test;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/17 11:28 下午
 */
public class SynchronizeWaitNotifyTest {

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                SynchronizeWaitNotifyABC.printA();
            }, "thread_a").start();

            new Thread(()-> {
                SynchronizeWaitNotifyABC.printB();
            }, "thread_b").start();

            new Thread(()-> {
                SynchronizeWaitNotifyABC.printC();
            }, "thread_c").start();
        }
    }
}
