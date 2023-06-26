import java.util.*;

/**
 * Lab 11
 *
 * Today's lab will be focused on finding cycles in a
 * graph.
 *
 * You will implement findCycles() in the Graph class and use
 * the results from that to find the first 10 cycles
 * from the graph and print them.
 *
 */

public class FindCycles{
    public static void main(String[] args) {
        smallgraphnocycle();
        smallgraphcycle();
    }

    static void smallgraphnocycle () {
        Item<String> a = new Item<>("a", "a", 0);
        Item<String> b = new Item<>("b", "b", 0);
        Item<String> c = new Item<>("c", "c", 0);
        ArrayList<Item<String>> nodes = new ArrayList<>(Arrays.asList(a, b, c));
        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();

        neighbors.put(a,new ArrayList<>(Arrays.asList(b,c)));
        neighbors.put(b,new ArrayList<>(Arrays.asList(c)));
        neighbors.put(c,new ArrayList<>(Arrays.asList()));

        DFS<String> g = new DFS<>(neighbors);
        g.setSource(nodes, a);
        System.out.printf("%n%nTraversing small graph no cycle...%n");
        g.findCycles();

        nodes.forEach(Item::reset);
    }

    static void smallgraphcycle () {
        Item<String> a = new Item<>("a", "a", 0);
        Item<String> b = new Item<>("b", "b", 0);
        Item<String> c = new Item<>("c", "c", 0);
        Item<String> d = new Item<>("d", "d", 0);
        Item<String> e = new Item<>("e", "e", 0);
        ArrayList<Item<String>> nodes = new ArrayList<>(Arrays.asList(a, b, c, d, e));
        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();

        neighbors.put(a, new ArrayList<>(Arrays.asList(a, b)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(c, b)));


        DFS DF = new DFS(neighbors);
        DF.setSource(nodes, a);

        DF.findCycles();

        nodes.forEach(Item::reset);
    }

    static void largegraph() {
        ArrayList<Item<Integer>> nodes = new ArrayList<>(Arrays.asList());
        Random nodeGen = new Random(133);

        Item<String> a = new Item<>("a", "a", 0);
        Item<String> b = new Item<>("b", "b", 0);
        Item<String> c = new Item<>("c", "c", 0);
        Item<String> d = new Item<>("d", "d", 0);
        Item<String> e = new Item<>("e", "e", 0);
        Item<String> f = new Item<>("f", "f", 0);
        Item<String> g = new Item<>("g", "g", 0);
        Item<String> h = new Item<>("h", "h", 0);
        Item<String> i = new Item<>("i", "i", 0);
        Item<String> k = new Item<>("k", "k", 0);

        //DFS DF = new DFS();
        //DF.setSource(nodes, a);
    }
}
