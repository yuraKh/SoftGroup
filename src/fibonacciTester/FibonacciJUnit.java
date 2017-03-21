package fibonacciTester;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Yury Khodanitcky
 * @see Fibonacci
 * @see FibonacciTestDynamic
 * @see FibonacciTestOptimized
 * @see FibonacciTestRecursion
 */
public class FibonacciJUnit {

    Fibonacci fibonacci;

    @Before
    public void initialize() {
        fibonacci = new Fibonacci();
    }

    @Test
    public void test() {
        final Long[] l = new Long[2];
        l[0] = new FibonacciTestDynamic().printResult(30, fibonacci);
        l[1] = new FibonacciTestRecursion().printResult(30, fibonacci);
        System.out.println(l[0] + " " + l[1]);
        assertTrue(l[0] < l[1]);
    }

    @Test
    public void test1() {
        final Long[] l = new Long[2];
        l[0] = new FibonacciTestDynamic().printResult(-30, fibonacci);
        l[1] = new FibonacciTestOptimized().printResult(-30, fibonacci);
        System.out.println(l[0] + " " + l[1]);
        assertTrue(l[0] == l[1]);
    }

    @Test
    public void test2() {
        final Long[] i = new Long[3];
        i[0] = fibonacci.getFibonacciDynamic(-30);
        i[1] = fibonacci.getFibonacciOptimized(-30);
        i[2] = fibonacci.getFibonacciRecursion(-30);
        assertEquals(i[0], i[1]);
        assertEquals(i[2], i[1]);
        System.out.println(i[0] + " " + i[1] + " " + i[2]);
    }
}