import java.util.*;

abstract class Graph<E> {
    protected Hashtable<Item<E>, ArrayList<Item<E>>> neighbors;
    protected Hashtable<Pair<Item<E>, Item<E>>, Integer> weights;
    protected NodeCollection<E> ncoll;

    Graph(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors,
          Hashtable<Pair<Item<E>, Item<E>>, Integer> weights,
          NodeCollection<E> ncoll) {
        this.neighbors = neighbors;
        this.weights = weights;
        this.ncoll = ncoll;
    }

    void setSource(ArrayList<Item<E>> nodes, Item<E> s) {
        ncoll.setSource(nodes,s);
    }

    abstract void relax(Item<E> u, Item<E> v);

    void visitPrint(Item<E> u) {
        if (!u.isVisited()) {
            u.setVisited(true);
            System.out.print(u);
            neighbors.get(u).forEach(v -> relax(u, v));
        }
    }

    void traverse() {
        while (!ncoll.isEmpty()) visitPrint(ncoll.extract());
    }

    abstract void findCycles();

    abstract boolean isEmpty();
}

class DFS<E> extends Graph<E> {
    DFS(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors) {
        super(neighbors, null, NodeCollection.make(KIND.STACK));
    }

    boolean isEmpty() {
        return ncoll.isEmpty();
    }

    void relax(Item<E> u, Item<E> v) {
        ncoll.insert(v);
    }

    HashSet visited = new HashSet<Pair>();

    void findCycles(){


        while (!ncoll.isEmpty()){

            Item nc = ncoll.extract();

            if (neighbors.get(nc).isEmpty()){
                nc.setVisited(false);
            }

            else if (nc.isVisited()){
                System.out.print("Cycle found\n");
                return;
            }

            else{
                nc.setVisited(true);

                for(Item i: neighbors.get(nc)) {
                    ncoll.insert(i);
                }
            }
        }


    }
}
