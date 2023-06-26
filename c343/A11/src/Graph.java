import java.util.*;

// ---------------------------------------------------------------------------

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

    void traverse() {
        while (!ncoll.isEmpty()) visitPrint(ncoll.extract());
    }

    void visitPrint(Item<E> u) {
        if (!u.isVisited()) {
            u.setVisited(true);
            System.out.print(u);
            neighbors.get(u).forEach(v -> relax(u, v));
        }
    }

    abstract void relax(Item<E> u, Item<E> v);
}

// ---------------------------------------------------------------------------

class BFS<E> extends Graph<E> {
    BFS(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors) {
        super(neighbors, null, NodeCollection.make(KIND.QUEUE));
    }

    void relax(Item<E> u, Item<E> v) {

        if (!v.isVisited()) {
            ncoll.insert(v);
        }

    }
}

// ---------------------------------------------------------------------------

class DFS<E> extends Graph<E> {
    DFS(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors) {
        super(neighbors, null, NodeCollection.make(KIND.STACK));
    }

    void relax(Item<E> u, Item<E> v) {

        if (!v.isVisited()){
            ncoll.insert(v);
        }
    }
}

// ---------------------------------------------------------------------------

class SP<E> extends Graph<E> {
    //shortest path
    SP(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors,
       Hashtable<Pair<Item<E>, Item<E>>, Integer> weights) {
        super(neighbors, weights, NodeCollection.make(KIND.BINARY_HEAP));
    }

    void relax(Item<E> u, Item<E> v) {

        if (u.getValue()+weights.get(new Pair(u, v)) < v.getValue()){
            ncoll.updateKey(v.getPosition(), weights.get(new Pair<>(u, v))+u.getValue());
        }
    }
}

// ---------------------------------------------------------------------------

class SPD<E> extends Graph<E> {
    //shortest path DHeap
    SPD(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors,
        Hashtable<Pair<Item<E>, Item<E>>, Integer> weights) {
        super(neighbors, weights, NodeCollection.make(KIND.DHEAP_3));
    }

    void relax(Item<E> u, Item<E> v) {

        if (u.getValue()+weights.get(new Pair(u, v)) < v.getValue()){
            ncoll.updateKey(v.getPosition(), weights.get(new Pair<>(u, v))+u.getValue());
        }
    }
}

// ---------------------------------------------------------------------------

class MSP<E> extends Graph<E> {
    //min shortest path
    MSP(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors,
        Hashtable<Pair<Item<E>, Item<E>>, Integer> weights) {
        super(neighbors, weights, NodeCollection.make(KIND.BINARY_HEAP));
    }

    void relax(Item<E> u, Item<E> v) {
        if (u.getValue()+weights.get(new Pair(u, v)) < v.getValue() && !v.isVisited()){
            ncoll.updateKey(v.getPosition(), weights.get(new Pair<>(u, v)));
        }
    }
}

// ---------------------------------------------------------------------------
// ---------------------------------------------------------------------------

