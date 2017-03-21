package fibonacciTester;

/**
 * Abstract class to test different function to find N'th {@link Fibonacci} number
 * @author Yury Khodanitcky
 * @see FibonacciJUnit - a JUnit test for this class
 * @see Fibonacci
 * @see FibonacciTestDynamic
 * @see FibonacciTestOptimized
 * @see FibonacciTestRecursion
 */
public abstract class FibonacciTestBase {


    String declaredClassname = this.getClass().getSimpleName();

    /**
     * Return time in milliseconds to find N'th Fibonacci number
     * @param n N'th Fibonacci number
     * @param fibonacci Fibonacci class
     * @return time in milliseconds to find N'th Fibonacci number
     */
    Long getTime(int n, Fibonacci fibonacci) {

        return null;
    }

    /**
     * Return time in milliseconds to find N'th Fibonacci number
     * @param n N'th Fibonacci number
     * @param fibonacci Fibonacci class
     * @return time in milliseconds to find N'th Fibonacci number
     */
    Long printResult(int n, Fibonacci fibonacci) {
        Long l = getTime(n, fibonacci);
        System.out.println("Time to find the " + n + " Fibonacci number using " + declaredClassname + " is " + l + " milliseconds");
        return l;
    }
}
