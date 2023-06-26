import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Function;
import java.lang.Math;

class DP {

    /**
     * Trivial example to show the pattern: fib
     * First write the normal recursive solution
     */
    static long fib (int n) {
        if (n < 2) return n;
        else return fib(n-1) + fib (n- 2);
    }

    /**
     * Create a hash table: please call it fibMemo as we will refer
     * to it from the test suite.
     */
    static Map<Integer,Long> fibMemo = new WeakHashMap<>();

    /**
     * Use the method computeIfAbsent passing the naive recursive
     * computation as an argument. Do not forget to rename the
     * recursive calls to refer to the memoized version.
     *
     * There will typically be small additional tweaks to do. In
     * this case, I had to explicitly cast from int to long
     * in the return clause for the base case.
     */
    static long mfib (int n1) {
        return fibMemo.computeIfAbsent(n1, n -> {
            if (n < 2) return (long)n;
            else return mfib(n - 1) + mfib(n - 2);
        });
    }

    /**
     * For fun... Compute fib using the golden ratio.
     */
    static long ffib (int n) {

        //int x = Math.pow(x, y)
        //This is the fibonacci sequence, as expressed by the golden ratio
        //The golden ration is a perfect ratio found in nature, and is expressed as sqrt(5)/2
        //This should run faster than

        return (long)((1/Math.sqrt(5)) * ((Math.pow(((1+Math.sqrt(5))/2), n)) - (Math.pow(((1-Math.sqrt(5))/2), n))));

    }

    // -----------------------------------------------------------------------------
    // Coin changing...
    // -----------------------------------------------------------------------------

    /**
     * Given a list of possible coins that must include a penny,
     * and a number of pennies 'n', in how many ways can we use the
     * coins to make change for 'n'.
     */
    static int coinChange (List<Coin> coins, int n) {
        try {
            if (n < 0) return 0; // no way to make change
            else if (n == 0) return 1; // previous choices succeeded
            else return coinChange(coins.getRest(), n) +
                        coinChange(coins,n - coins.getFirst().getValue());
        }
        catch (EmptyListE e) {
            return 0; // no way to make change
        }
    }

    /**
     * Again we create a hash table making sure it is called
     * coinChangeMemo. But here we have to think a bit. Each
     * subproblem is determined by the list of coins and by the
     * desired sum.  That combination should be the key used
     * in hashing.
     */

    static Map<Pair<List<Coin>,Integer>,Integer> coinChangeMemo = new WeakHashMap<>();

    /**
     * We again want to use computeIfAbsent. When we communicate with
     * the hash table, we combine all the information into a key and
     * take back apart when we extract from the hash table.
     */
     static int mcoinChange (List<Coin> coins1, int n1) {
        return coinChangeMemo.computeIfAbsent(new Pair<>(coins1,n1), p -> {
            List<Coin> coins = p.getFirst();
            int n = p.getSecond();
            try {
                if (n < 0) return 0; // no way to make change
                else if (n == 0) return 1; // previous choices succeeded
                else return mcoinChange(coins.getRest(), n) +
                            mcoinChange(coins, n - coins.getFirst().getValue());
            }
            catch (EmptyListE e) {
                return 0; // no way to make change
            }
        });
    }

    // -----------------------------------------------------------------------------
    // Partition ...
    // -----------------------------------------------------------------------------

    /**
     * Partition: take a list, a desired sum 's', and return
     * T/F depending on whether it is possible to find a
     * subsequence of the list whose sum is exactly 's'
     */
    static boolean partition (List<Integer> s, int sum) {
        try {

            int first = s.getFirst();

            boolean r1 = partition(s.getRest(), sum);
            boolean r2 = partition(s.getRest(), sum-first);

            return r1 || r2;

        }

        catch(EmptyListE e){
            if (sum == 0) {
                return true;
            } else {
                return false;
            }
        }
    }


    //static Map<Pair<List<Coin>,Integer>,Integer> coinChangeMemo = new WeakHashMap<>();
    static Map<Pair<List<Integer>, Integer>, Boolean> partitionMemo = new WeakHashMap<>();

    static boolean mpartition (List<Integer> s1, int sum1) {
        // return coinChangeMemo.computeIfAbsent(new Pair<>(coins1,n1), p -> {
        return partitionMemo.computeIfAbsent(new Pair<>(s1, sum1), p-> {

            List<Integer> s11 = p.getFirst();
            int sum11 = p.getSecond();

            try {

                int first2 = s11.getFirst();

                //return mpartition(s11.getRest(), sum11) || mpartition(s11.getRest(), sum11 - first2);

                if (mpartition(s11.getRest(), sum11 - first2)){
                    return true;
                }
                else if(mpartition(s11.getRest(), sum11)){
                    return true;
                }
                return false;

            } catch (EmptyListE e) {
                if (sum11 == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
    // -----------------------------------------------------------------------------
    // Min distance ...
    // -----------------------------------------------------------------------------

    final static int GAP = 2;
    final static int MATCH = 0;
    final static int MISMATCH = 1;

    enum BASE { A, T, G, C }

    static int noShift = 0;

    static int minDistance (List<BASE> dna1, List<BASE> dna2)
    {
        try{

            //match
            //fill gap in first
            //fill gap in second

            noShift=0;

            if (dna1.getFirst() != dna2.getFirst()){
                noShift=1;
            }

            int r1 = noShift + minDistance(dna1.getRest(), dna2.getRest());
            int r2 = GAP + minDistance(dna1.getRest(), dna2);
            int r3 = GAP + minDistance(dna1, dna2.getRest());


            // add gap cost and mismatch cost
            return Math.min(Math.min(r1, r2), r3);

        } catch (EmptyListE e) {
            return Math.max(GAP*dna1.length(), GAP*dna2.length());
        }
    }

    //static Map<Pair<List<Coin>,Integer>,Integer> coinChangeMemo = new WeakHashMap<>();
    static Map<Pair<List<BASE>,List<BASE>>,Integer> minDistanceMemo = new WeakHashMap<>();
    static int noShift2 = 0;

    static int mminDistance (List<BASE> dna11, List<BASE> dna21) {
        return minDistanceMemo.computeIfAbsent(new Pair<>(dna11, dna21), p-> {
            List<BASE> dna101 = p.getFirst();
            List<BASE> dna202 = p.getSecond();

            try {

                noShift2 = 0;
                //match
                //fill gap in first
                //fill gap in second

                if (dna101.getFirst() != dna202.getFirst()){
                    noShift2 = 1;
                }

                int r1 = noShift2 + mminDistance(dna101.getRest(), dna202.getRest());
                int r2 = GAP + mminDistance(dna101.getRest(), dna202);
                int r3 = GAP + mminDistance(dna101, dna202.getRest());


                // add gap cost and mismatch cost
                return Math.min(Math.min(r1, r2), r3);

            } catch (EmptyListE e) {
                return Math.max(GAP*dna101.length(), GAP*dna202.length());
            }
        });
    }

    // -----------------------------------------------------------------------------
    // Longest common subsequence ...
    // -----------------------------------------------------------------------------

    static List<Character> lcs (List<Character> cs1, List<Character> cs2) {

        try {

            if (cs1.getFirst() == cs2.getFirst()){
                // add first to return list
                return new Node(cs1.getFirst(), lcs(cs1.getRest(), cs2.getRest()));
            }

            else{

                List<Character> r1 = lcs(cs1, cs2.getRest());
                List<Character> r2 = lcs(cs1.getRest(), cs2);

                if (r1.length() > r2.length()){
                    //add r1 to result
                    return r1;
                }
                else {
                    //add r2 to result
                    return r2;
                }
            }

        } catch (EmptyListE e) {
            return new Empty();
        }
    }

    //static Map<Pair<List<Coin>,Integer>,Integer> coinChangeMemo = new WeakHashMap<>();
    static Map<Pair<List<Character>,List<Character>>,List<Character>> lcsMemo = new WeakHashMap<>();

    static List<Character> mlcs (List<Character> cs11, List<Character> cs21) {
        return lcsMemo.computeIfAbsent(new Pair<>(cs11, cs21), p-> {
            List<Character> cs111 = p.getFirst();
            List<Character> cs222 = p.getSecond();

            try {
                if (cs111.getFirst() == cs222.getFirst()) {
                    // add first to return list
                    return new Node(cs111.getFirst(), mlcs(cs111.getRest(), cs222.getRest()));
                } else {

                    List<Character> r1 = mlcs(cs111, cs222.getRest());
                    List<Character> r2 = mlcs(cs111.getRest(), cs222);

                    if (r1.length() > r2.length()) {
                        //add r1 to result
                        return r1;
                    } else {
                        //add r2 to result
                        return r2;
                    }
                }

            } catch (EmptyListE e) {
                return new Empty();
            }
        });
    }
}
