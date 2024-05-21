package design;

import com.algorithm.design.LRUCache;
import com.algorithm.design.LRUCache2;
import org.junit.Test;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/21 11:50 下午
 */
public class LRUCache2Test {

    @Test
    public void testLRUCache() {
        LRUCache2<String, String> lruCache = new LRUCache2<>(3);
        lruCache.put("1", "1");
        lruCache.put("2", "2");
        lruCache.put("3", "3");
        System.out.println(lruCache.get("1"));
        System.out.println(lruCache.get("2"));
        System.out.println(lruCache.get("3"));
        lruCache.put("4", "4");
        System.out.println(lruCache.get("1"));
        System.out.println(lruCache.get("4"));
    }
}
