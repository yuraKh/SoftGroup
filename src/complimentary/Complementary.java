package complimentary;

import java.util.HashMap;
import java.util.Map;

/**
 * Algorithm to find K-complementary pairs in a given array of integers.
 * Given Array A, pair (i, j) is K- complementary if K = A[i] + A[j]
 * @author Yury Khodanitcky
 * @see ComplementaryTest - a JUnit test for this class
 */
public class Complementary {


    public static void main(String[] args) {
        Complementary complementary = new Complementary();
        int[] ints = new int[]{5, 61, -51, 6, 19, 3, -11, -1, 4};
        Map<Integer, Integer> complementaryPairs = complementary.getComplementaryPairs(99, ints);
        if (complementaryPairs.size() > 0) {
            for (Map.Entry<Integer, Integer> entry : complementaryPairs.entrySet()) {
                System.out.println("K-Complementary pairs are " + entry.getKey() + " and " + entry.getValue());
            }
        } else {
            System.out.println("In given array no K-complementary pairs was found");
        }
    }

    /**
     * Returns a Map of K-Complementary pairs.
     */
    public Map<Integer, Integer> getComplementaryPairs(int k, int[] ints) {

        Map<Integer, Integer> pairs = new HashMap<>();
        Integer [][] complementaryPairs = new Integer[ints.length][2];

        // Fill up HashMap where key is number from an array and value is a difference between K and current number
        for(Integer i: ints){
            pairs.put(i, k - i);
        }

        //Check if HashMap contains key which is equals difference between k and current number from an array
        //If so put values to two-dimensional array
        for(int i = 0; i<ints.length; i++) {

            if (pairs.containsKey(k - ints[i])) {
                complementaryPairs[i][0] = ints[i];
                complementaryPairs[i][1] = k - ints[i];
            }
        }

        pairs.clear();

        //Fill up HashMap with K-Complementary pairs
        for(int i = 0; i < complementaryPairs.length; i++){
            if(complementaryPairs[i][0] != null){
                pairs.put(complementaryPairs[i][0], complementaryPairs[i][1]);
            }
        }
      return pairs;
    }
}