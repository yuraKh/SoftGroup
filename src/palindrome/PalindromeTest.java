package palindrome;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Yury Khodanitcky
 * @see Palindrome
 */
public class PalindromeTest {

    private Palindrome palindrome;

    @Before
    public void initiate() {
        this.palindrome = new Palindrome();
    }

    @Test
    public void test() {
        boolean b = true;
        String s = "gff111ffg";
        assertEquals(palindrome.palindromeCheck(s), b);
    }

    @Test
    public void test1() {
        boolean b = false;
        String s = "ddddfhmkthjpfotkd[oprhkriojphktjhoptmnpo";
        assertEquals(this.palindrome.palindromeCheck(s), b);
    }

    @Test
    public void test2() {
        boolean b = false;
        String s = "58855858585858585855";
        assertEquals(this.palindrome.palindromeCheck(s), b);

    }
}
