/**
 * Implement a bottom-up solution to the Longest Increasing Subsequence
 * problem, following the algorithm described in lab.
 */

/**
 * A subproblem is to compute the length of the longest increasing
 * subsequence that ends with the i-th element in the array. The
 * result of solving such a subproblem is the score (i.e., the length
 * of the sequence) and the parent (i.e., the index of element in the
 * array that precedes the i-th one in the identified sequence).
 */

class Result {
    int score;
    int parent;

    Result(int score, int parent) {
        this.score = score;
        this.parent = parent;
    }

    public String toString() {
        return String.format("(%d,%d)", score, parent);
    }
}

public class BottomUp {

    /**
     * Returns an array consisting of the longest increasing subsequence
     * in a. Your solution must follow the code sketch given in lab.
     */

    public static int[] lis(int[] a) {
        int n = a.length;

        // TODO: What is the base case you should check for?
        if (n == 0){
            int[] res = {};
            return res;
        }


        Result[] cache = new Result[n];
        // TODO: What is the first entry in the cache?
        // Hint: All other entries in the table build off of this
        // Remember, an entry in the cache is a Result object, which has two pieces.
        // first should be 1
        cache[0] = new Result(1, -1);


        // TODO: Now, build the table.
        // Some steps:
        // 1. You know you'll need to loop through every index i in the table, and for each one,
        //    calculate a score (the LIS that ends at that index) and the parent (the previous
        //    element in that LIS)
        // 2. To calculate the score, you need the MAX SCORE over ALL indices (j) leading up to i
        //    -> So, you need to think about how to find the index j with the "best" score
        // 3. Once you find the best score, create a Result containing that score and the corresponding
        //    parent, and set the cache at index i
        for (int i = 1; i < n; i++){
            if (a[i-1] < a[i]){
                cache[i] = new Result(1+cache[i-1].score, i-1);
            }
            else {
                cache[i] = new Result(cache[i-1].score, cache[].parent);
            }
        }


        // TODO: Traceback.
        // Now that you have the cache with all the information, you need to traceback to get the
        // actual sequence.
        // 1. Start by finding the MAX SCORE in the cache. This is where you will start tracing back.
        //    Keep in mind, this could be anywhere in the cache.
        // 2. Start at the "best" score, then trace back, jumping to the index that is the parent of the
        //    previous. Return this sequence.
        
        return null;
    }

    /**
     * Simple testing.
     */

    public static void main(String... args) {
        int[] a, b;
        a = new int[] { 5, 6, 1, 2, 9, 3, 4, 7, 4, 3 };
        b = lis(a);
        assert 5 == b.length;
        assert 1 == b[0];
        assert 2 == b[1];
        assert 3 == b[2];
        assert 4 == b[3];
        assert 7 == b[4];
        a = new int[] { 2, 1, 5, 3, 6, 4, 2, 7, 9, 11, 8, 10 };
        b = lis(a);
        assert 6 == b.length;
        assert 2 == b[0];
        assert 5 == b[1];
        assert 6 == b[2];
        assert 7 == b[3];
        assert 9 == b[4];
        assert 11 == b[5];
        a = new int[] { 1, 2, 3, 4, 5 };
        b = lis(a);
        assert a.length == b.length;
        a = new int[] { 5, 4, 3, 2, 1 };
        b = lis(a);
        assert 1 == b.length;
        System.out.println("All tests passed...");
    }
}
