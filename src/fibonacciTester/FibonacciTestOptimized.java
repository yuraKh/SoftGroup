package fibonacciTester;

/**
 * Class to test {@link Fibonacci#getFibonacciOptimized(int)} function to find N'th Fibonacci number
 * @author Yury Khodanitcky
 * @see FibonacciJUnit - a JUnit test for this class
 * @see Fibonacci
 * @see FibonacciTestBase
 */
public class FibonacciTestOptimized extends FibonacciTestBase {

    /**
     * Return time in milliseconds to find N'th Fibonacci number
     * @param n N'th Fibonacci number
     * @param fibonacci Fibonacci class
     * @return time in milliseconds to find N'th Fibonacci number
     */
    @Override
    public Long getTime(int n, Fibonacci fibonacci) {
        long start = System.currentTimeMillis();
        fibonacci.getFibonacciOptimized(n);
        return System.currentTimeMillis() - start;
    }
}
