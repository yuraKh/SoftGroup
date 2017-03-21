package fibonacciTester;

/**
 * Class to test {@link Fibonacci} class functions to find N'th Fibonacci number in different thread
 * @author Yury Khodanitcky
 * @see Fibonacci
 * @see FibonacciTestDynamic
 * @see FibonacciTestOptimized
 * @see FibonacciTestRecursion
 */
public class MultiThreadTester {

    private int n;

    public static void main(String[] args) {
        MultiThreadTester multiThreadTester = new MultiThreadTester(30);
        MultiThreadTester multiThreadTester1 = new MultiThreadTester(-30);
        multiThreadTester.test(multiThreadTester.getN(), new Fibonacci());
        multiThreadTester1.test(multiThreadTester1.getN(), new Fibonacci());
    }

    public MultiThreadTester(int n){
        this.n = n;
    }

    public int getN (){
        return n;
    }

    public void test(int n, Fibonacci fibonacci) {

        Thread thread = new Thread(() -> {
            FibonacciTestBase fibonacciTestDynamic = new FibonacciTestDynamic();
            fibonacciTestDynamic.printResult(n, fibonacci);

        });

        Thread thread1 = new Thread(() -> {
            FibonacciTestBase fibonacciTestRecursion = new FibonacciTestRecursion();
            fibonacciTestRecursion.printResult(n, fibonacci);
        });

        Thread thread2 = new Thread(() -> {
            FibonacciTestBase fibonacciTestOptimized = new FibonacciTestOptimized();
            fibonacciTestOptimized.printResult(n, fibonacci);
        });

        thread.start();
        thread1.start();
        thread2.start();
    }
}
