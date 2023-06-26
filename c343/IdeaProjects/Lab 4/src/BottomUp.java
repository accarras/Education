/**
 * This is your third task.
 *
 * Implement the BottomUp approach to determine the
 * number of ways to make change with a given set
 * of coins and an amount to make change for.
 *
 * This approach was NOT covered in lecture, but your UIs
 * covered it in lab today. The basic approach is that
 * instead of using a recursive formulation and storing
 * subproblems as they arrive, you build a table where
 * each entry builds on the one before it.
 *
 * What is the time complexity of this BottomUP approach
 * versus recursion and TopDown?
 */

public class BottomUp {
    static int countWays(int[] coins, int l, int n) {

        // TODO: Initialize the table, then fill it using the bottom up approach

        return 0;
    }

    /**
     * Simple main method tests are given, but you should write
     * additional tests.
     */
    public static void main(String args[]) {
        int coins[] = {1, 5, 10, 25};
        int l = coins.length;
        assert(countWays(coins, l, 30) == 18);
        assert(countWays(coins, l, 5000) == 16892551);
    }
}
