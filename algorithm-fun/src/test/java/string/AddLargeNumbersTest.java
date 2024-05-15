package string;

import com.algorithm.string.AddLargeNumbers;
import org.junit.Test;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/15 10:09 上午
 */
public class AddLargeNumbersTest {

    @Test
    public void testAddLargeNumbers() {
        AddLargeNumbers add = new AddLargeNumbers();
        String a = "1242143214123412222421432";
        String b = "5342524524354133433323433";
        System.out.println(add.plus(a, b));
    }
}
