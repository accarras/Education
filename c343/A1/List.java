abstract class List {
    abstract int length ();
    abstract boolean isEmpty();
    abstract int total ();
    abstract List append (List ys);
    abstract List triplicate();
    abstract List square();
    abstract boolean allEven();
    abstract List evens();
    abstract int product();
    
   /* public static void main(String[] args) {
        List xs = new Node(1, new Node(2, new Node (3, new Empty())));
        List ys = new Node(4, new Node (5, new Empty()));
        System.out.println(xs.length());
        System.out.println(xs.total());
        System.out.println(xs.append(ys).length());
    }*/
}

class Empty extends List {
    int length () { return 0; }
    boolean isEmpty () { return true; }
    int total () { return 0; }
    List append (List ys) { return ys; }

    public boolean equals(Object other) {
        return other instanceof Empty;
    }
    
    List triplicate() {return new Empty();}
    List square(){return new Empty();}
    boolean allEven(){return true;}
    List evens(){return new Empty();}
    int product(){return 0;}
    
    
}

class Node extends List {
    private int data;
    private List rest;

    Node(int data, List rest) {
        this.data = data;
        this.rest = rest;
    }

    int length() {
        return 1 + rest.length();
    }

    boolean isEmpty() {
        return false;
    }

    int total() {
        return data + rest.total();
    }

    List append(List ys) {
        return new Node(data, rest.append(ys));
    }

    public boolean equals(Object other) {
        if (other instanceof Node) {
            Node xs = (Node) other;
            return this.data == xs.data && this.rest.equals(xs.rest);
        }
        else return false;
    }
    
    List triplicate() {
      return new Node(data, new Node(data, new Node(data, rest.triplicate())));
    }
    
    List square(){
      return new Node(data*data, rest.square());
    }
    
    boolean allEven(){
      return (data%2!=0) && rest.allEven();
    }
    
    List evens(){
      if (data%2!=0){
        return new Node(data, rest.evens());
      }
      else
       return rest.evens();
    }
    
    int product(){
      return data*rest.product();
    }
}


