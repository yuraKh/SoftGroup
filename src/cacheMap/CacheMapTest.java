package cacheMap;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author Yury Khodanitcky
 */
public class CacheMapTest {

    private CacheMap<Integer, String> cache;
    private final static long TIME_TO_LIVE = 1000;

    @Before
    public void initiate() {
        cache = new CacheMapImpl<>();
        cache.setLifeTime(TIME_TO_LIVE);
    }

    @Test
    public void test1() throws Exception {
        cache.put(1, "Coca");
        assertEquals("Coca", cache.get(1));
        assertFalse(cache.isEmpty());

        TimeUnit.MILLISECONDS.sleep(2000);
        assertNull(cache.get(1));
        assertTrue(cache.isEmpty());

    }

    @Test
    public void test2() throws Exception {
        Map<Integer, String> test = new HashMap<Integer, String>(){{
            put(4,"Fanta");
            put(2, "Sprite");
            put(3, "Water");
        }};

        cache.put(1, "Coca");
        assertEquals("Coca", cache.get(1));
        assertFalse(cache.isEmpty());
        cache.putAll(test);
        assertEquals("Fanta", cache.get(4));
        assertEquals(4, cache.size());
        TimeUnit.MILLISECONDS.sleep(2000);
        assertNull(cache.get(1));
        assertTrue(cache.isEmpty());
        assertEquals(0, cache.size());
    }

    @Test
    public void test3() throws Exception {
        assertNull(cache.remove(1));

        cache.put(1, "Coca");
        assertEquals("Coca", cache.remove(1));
        assertNull(cache.get(1));
        assertEquals(0, cache.size());

    }
}