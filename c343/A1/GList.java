import java.util.function.BiFunction;

abstract public class GList<E> {
    abstract int length();
    abstract boolean isEmpty();
    abstract GList<E> append (GList<E> xs);
    abstract E sum (E base, BiFunction<E,E,E> acc);
}

class Empty<E> extends GList<E> {
    int length () { return 0; }
    boolean isEmpty() { return true; }
    GList<E> append (GList<E> xs) { return xs; }
    E sum (E base, BiFunction<E,E,E> acc) { return base; }
}

class Node<E> extends GList<E> {
    private E data;
    private GList<E> rest;

    Node (E data, GList<E> rest) {
        this.data = data;
        this.rest = rest;
    }

    int length() { return 1 + rest.length(); }
    boolean isEmpty() { return false; }
    GList<E> append (GList<E> xs) {
        return new Node<>(this.data, rest.append(xs));
    }
    E sum (E base, BiFunction<E,E,E> acc) {
        return acc.apply(this.data,rest.sum(base,acc));
    }





}






