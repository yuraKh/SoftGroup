package complimentary;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * @author Yury Khodanitcky
 * @see Complementary
 */
public class ComplementaryTest {

    private Complementary complementary;

    @Before
    public void initiate() {
        this.complementary = new Complementary();
    }

    @Test
    public void test1() {
        Map<Integer, Integer> expected = new HashMap<Integer, Integer>() {
            {
                put(11, 5);
                put(7, 9);
                put(9, 7);
                put(5, 11);
            }
        };
        int[] test = new int[]{11, 5, 7, 9};
        assertTrue(expected.equals(this.complementary.getComplementaryPairs(16, test)));
    }

    @Test
    public void test2() {
        Map<Integer, Integer> expected = new HashMap<Integer, Integer>() {
            {
                put(0, -6);
                put(-6, 0);
            }
        };
        int[] test = new int[]{3, 5, 7, 4 , 8, 0, -6};

        assertTrue(expected.equals(this.complementary.getComplementaryPairs(-6, test)));
    }

    @Test
    public void test3() {
        Map<Integer, Integer> expected = new HashMap<>();
        int[] test = new int[]{3, -18, 2, 7, 3, 1};

        assertTrue(expected.equals(this.complementary.getComplementaryPairs(7, test)));
    }
}