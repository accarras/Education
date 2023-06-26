/**
 * PART 1
 * TODO
 * Convert IndList to a generic class.
 * (Currently, IndList is a list of integers. After
 *  conversion, you should able to make a list of anything!)
 */
abstract class IndList {
    abstract int length ();
    abstract boolean isEmpty();
    abstract IndList append (IndList ys);
    abstract IndList reverse ();
}

class Empty extends IndList {
    int length () { return 0; }
    boolean isEmpty () { return true; }
    IndList append (IndList ys) { return ys; }

    public boolean equals(Object other) {
      return other instanceof Empty;
    }

    IndList reverse() {
      return new Empty();
    }
}

class Node extends IndList {
    private int data;
    private IndList rest;

    Node(int data, IndList rest) {
        this.data = data;
        this.rest = rest;
    }

    int length() {
        return 1 + rest.length();
    }

    boolean isEmpty() {
        return false;
    }

    IndList append(IndList ys) {
        return new Node(data, rest.append(ys));
    }

    IndList reverse() {
      return rest.reverse().append(new Node(data, new Empty()));
    }

    public boolean equals(Object other) {
        if (other instanceof Node) {
            Node xs = (Node) other;
            return this.data == xs.data && this.rest.equals(xs.rest);
        }
        else return false;
    }
}


