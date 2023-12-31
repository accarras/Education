import java.util.HashMap;

/**
 * This is your second task.
 *
 * Implement the TopDown approach to determine the
 * number of ways to make change with a given set
 * of coins and an amount to make change for.
 *
 * This approach was covered in lecture - see
 * Amr's notes from lecture yesterday. The basic idea is
 * that you will use a recursive formulation to solve each
 * subproblem, but you store subproblems in a cache and
 * if you see them later, look them up in the cache
 * instead of recomputing
 *
 * What is the time complexity of this TopDown approach
 * versus recursion?
 */

class SubProblem {
    int l; // length of coins list to include
    int n; // current value remaining

    /**
     * Constructs a problem from the given length and n.
     */

    SubProblem(int l, int n) {
        this.l = l;
        this.n = n;
    }

    /**
     * Returns true iff the given object equals this object, field for field.
     * If we don't override this method, then the hash map will not be able to
     * find previously stored problems.
     */

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SubProblem) {
            SubProblem that = (SubProblem) obj;
            return this.l == that.l && this.n == that.n;
        }
        return false;
    }

    /**
     * Returns a nicely packed version of this Subproblem. This promotes good
     * behavior of a hash map that uses Subproblems as keys.
     */
    @Override
    public int hashCode() {
        return l << 16 | n;
    }
}

public class TopDown {

    static HashMap<SubProblem, Integer> cache;

    public static int countWays(int[] coins, int n) {

        if(coins.length == 0)
            return 0;

        // TODO: initialize the cache
        for (int i = 0; i < coins.length; i++){
            cache.put(new SubProblem(0, 0), i);
        }

        // Initially, we consider the whole list of coins and the original amount of n
        return countHelper(coins, coins.length, n);
    }

    public static int countHelper(int coins[], int l, int n) {
        Integer ans;

        SubProblem cacheKey = new SubProblem(l, n);

        if (cache.containsKey(cacheKey)){
            return cache.get(cacheKey);
        }

        if (n == 0){
            return 1;
        }

        if (n <0){
            return 0;
        }

        if ((l <= 0) && (n >= 1)){
            return 0;
        }

        else {

             ans =(countHelper(coins, l - 1, n) + countHelper(coins, l, n - coins[l - 1]));


        }

        cache.put(cacheKey, ans);

        //ans = 0;
        return ans;
    }

    /**
     * Simple main method tests are given, but you should write
     * additional tests.
     */
    public static void main(String[] args)
    {
        int coins[] = {1, 5, 10, 25};
        assert(countWays(coins, 30) == 18);
        assert(countWays(coins, 5000) == 16892551);
    }

}
