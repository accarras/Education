import java.util.*;

/**
 * In last week's assignment, you were asked to implement
 * relax() for both SP and SPD
 *
 * SP uses a Binary Heap, while SPD uses a D Heap. This
 * lab is more conceptual than practical. You will be
 * asked to time your implementations of SP and SPD and
 * compare the results. In order to get credit, you need
 * to:
 *
 * (1) complete large tests (the one listed is very
 * small and will not be sufficient for comparisons)
 *
 * (2) come up with the theoretical time complexities (in
 * terms of Big O) for SP and SPD.
 *
 * (3) decide which heap is best in practice
 *
 * (4) decide whether or not you can construct a graph for
 * which BinaryHeap is superior - if so, construct it,
 * otherwise, explain why.
 *
 * (5) decide whether or not you can construct a graph for
 * which DHeaps are better for various choices of d
 */

public class Main {
    public static void main(String[] args) {
        Item<String> a, b, c, d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(b, c)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(c, d, e)));
        neighbors.put(c, new ArrayList<>(Arrays.asList()));
        neighbors.put(d, new ArrayList<>(Arrays.asList(e)));
        neighbors.put(e, new ArrayList<>(Arrays.asList()));

        Hashtable<Pair<Item<String>,Item<String>>,Integer> weights = new Hashtable<>();
        weights.put(new Pair<>(a,b),2);
        weights.put(new Pair<>(a,c),2);
        weights.put(new Pair<>(b,c),1);
        weights.put(new Pair<>(b,d),2);
        weights.put(new Pair<>(b,e),2);
        weights.put(new Pair<>(d,e),1);

        Graph<String> g;
        System.out.println("SP");
        g = new SP<>(neighbors,weights);
        g.setSource(nodes,a);

        long startTime = System.nanoTime();
        g.traverse();
        long endTime = System.nanoTime();
        long duration1 = (endTime - startTime) / 1000000;
        System.out.println("Time for SP: " + duration1);

        nodes.forEach(Item::reset);
        System.out.printf("%n%n---------%n");

        System.out.println("SPD");
        g = new SPD<>(neighbors,weights);
        g.setSource(nodes,a);

        startTime = System.nanoTime();
        g.traverse();
        endTime = System.nanoTime();
        long duration2 = (endTime - startTime) / 1000000;
        System.out.println("Time for SPD: " + duration2);

        nodes.forEach(Item::reset);
        System.out.printf("%n%n---------%n");

        /**
         * TODO 1: Make a big graph (~100-1000 nodes depending on your computer) to test these timings
         *
         * Hint: remember you can use Random to make the values of the weights. When you do this, make
         * sure the random numbers don't repeat- otherwise you might have repeated nodes and edges.
         * The hard part will be determining how to randomly assign neighbors to each node.
         *
         * Make sure to explain your strategy to your UIs.
         */

        int num = 100;
        ArrayList<Item<String>> items2 = new ArrayList<>();
        Random r = new Random(1);
        for (int i = 0; i < num; i++) {
            int k = r.nextInt(100);
            items2.add(new Item<>("a" + k, "a" + k, k));
        }

        Hashtable<Pair<Item<String>, Item<String>>, Integer> weights2 = new Hashtable<>();
        for (int i = 0; i < num; i++) {
            int k = r.nextInt(100);
            int j = r.nextInt(100);
            weights2.put(new Pair(new Item<>("a"+k, "a"+k, 0), new Item<>("a"+j, "a"+j, 0)), k*j);
        }

        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors2 = new Hashtable<>();
        for (int i = 0; i < num; i++) {
            int k = r.nextInt(100);
            int j = r.nextInt(10);
            ArrayList<Item<String>> miniNeighbors = new ArrayList();
            for(int kk =0; kk < j; kk++) {
                miniNeighbors.add(new Item<>("a"+j, "a"+j, 0));
            }
            neighbors2.put(new Item<>("a" + k, "a" + k, 0), (miniNeighbors));

        }


        /**
         * TODO 2: What are the theoretical complexities of SP and SPD? Demonstrate this empirically.
         *
         * You are free to test the timing of any of the heap methods in order to show that the
         * complexities you come up with are correct. This is an open-ended   question but in order to
         * earn full points you'll need to have a well-thought out answer with tests that support it.
         */




        /**
         * TODO 3: Which heap is better in practice?
         */


        /**
         * TODO 4: Decide whether or not you can construct a graph for which BinaryHeap is superior
         * If so, construct it, otherwise, explain why.
         */


        /**
         * TODO 5: Decide whether or not you can construct a graph for which DHeaps are better for various choices of d
         */

    }
}
