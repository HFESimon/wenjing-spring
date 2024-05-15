package design;

import com.algorithm.design.LRUCache;
import org.junit.Test;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/15 10:06 上午
 */
public class LRUCacheTest {

    @Test
    public void testLRUCache() {
        LRUCache<String, String> lruCache = new LRUCache<>(3);
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
