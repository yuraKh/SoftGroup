package fibonacciTester;

/**
 * Algorithms to find the N'th Fibonacci number.
 * @author Yury Khodanitcky
 * @see FibonacciJUnit - a JUnit test for this class
 */
public class Fibonacci {

    public Long getFibonacciRecursion(int n) {

        if (n == 0) {
            return 0L;
        } else if (n == 1 || n == -1) {
            return 1L;
        } else {
            if (n > 0) {
                return getFibonacciRecursion(n - 1) + getFibonacciRecursion(n - 2);
            } else {
                return getFibonacciRecursion(n + 2) - getFibonacciRecursion(n + 1);
            }
        }
    }

    public Long getFibonacciDynamic (int n){

        Long f[] = new Long[ Math.abs(n)+1];

        f[0] = 0L;
        f[1] = 1L;


        if(n> 0) {
            for (int i = 2; i <= n; i++) {
                f[i] = f[i-1] + f[i-2];
            }
        }else {
            for (int i = 2; i <= Math.abs(n); i++) {
                f[i] = f[i - 2] - f[i - 1];
            }
        }
        return f[Math.abs(n)];
    }

    public Long getFibonacciOptimized(int n)
    {
        Long x = 1L;
        Long y = 0L;
        Long ans = 0L;
        if(n>0) {
            for (int i = 1; i <= n; i++) {
                ans = x + y;
                x = y;
                y = ans;
            }
            return ans;
        }else {
            for (int i = 1; i <= Math.abs(n); i++) {
                ans = x - y;
                x = y;
                y = ans;
            }
            return ans;
        }
    }

}
