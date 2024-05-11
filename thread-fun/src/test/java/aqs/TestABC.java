package aqs;

import com.practice.printABC.aqs.LockConditionABC;
import org.junit.Test;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/17 10:54 下午
 */
public class TestABC {

    @Test
    public void testABC() {
        new Thread(() -> {
            LockConditionABC.printA();
        }, "thread_a").start();

        new Thread(() -> {
            LockConditionABC.printB();
        }, "thread_b").start();

        new Thread(() -> {
            LockConditionABC.printC();
        }, "thread_c").start();
    }

    @Test
    public void testABC10() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LockConditionABC.printA();
            }, "thread_a").start();

            new Thread(() -> {
                LockConditionABC.printB();
            }, "thread_b").start();

            new Thread(() -> {
                LockConditionABC.printC();
            }, "thread_c").start();
        }
    }
}
