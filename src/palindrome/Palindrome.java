package palindrome;

/**
 * Algorithm to check if a string is a palindrome.
 * A string is a palindrome if the string matches the reverse of string.
 * Example: 1221 is a palindrome but not 1121.
 * @author Yury Khodanitcky
 * @see PalindromeTest - a JUnit test for this class
 */
public class Palindrome {

    public static void main(String[] args) {

        String s = "ghhghhg";
        Palindrome palindrome = new Palindrome();
        boolean isPalindrome = palindrome.palindromeCheck(s);
        if (isPalindrome) {
            System.out.println("The word " + "\"" + s + "\"" + " is palindrome");
        } else {
            System.out.println("The word " + "\"" + s + "\"" + " is not palindrome");
        }
    }

    /**
     * Returns true if string is palindrome.
     */
    public boolean palindromeCheck(String original) {
        char[] s = original.toCharArray();
        int length = s.length;
        int halfLength = length / 2;
        for (int i = 0; i < halfLength; i++) {
            char temp = s[i];
            s[i] = s[length - 1 - i];
            s[length - 1 - i] = temp;
        }
        String b = new String(s);
        return original.equals(b);
    }
}