import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {

    @Test
    public void length () {
        List xs = new Node(1,new Node(2, new Node (3, new Empty())));
        assertEquals(3, xs.length());
    }

    @Test
    public void append () {
        List xs = new Node(1,new Node(2, new Node(3, new Empty())));
        List ys = new Node(4, new Node (5, new Empty()));
        List zs = new Node(1,new Node(2, new Node(3, new Node(4, new Node(5, new Empty())))));
        assertEquals(zs, xs.append(ys));
    }
    /*
    @Test
    public void triplicate() {
        List xs = new Node(1,new Node(2, new Node(3, new Empty())));
        List ys = new Node(1,new Node(1,new Node(1,new Node(2,new Node(2,new Node(2, new Node(3,  new Node(3, new Node(3, new Empty())))))))));
        assertEquals(ys, xs.triplicate());
    }
    
    @Test
    public void square() {
        List xs = new Node(1,new Node(2, new Node(3, new Empty())));
        List ys = new Node(1,new Node(4, new Node(9, new Empty())));
        assertEquals(ys, xs.triplicate());
    }
    
    @Test
    public void ssquare() {
        List xs = new Node(1,new Node(2, new Node(3, new Empty())));
        List ys = new Node(1,new Node(4, new Node(9, new Empty())));
        assertEquals(ys, xs.triplicate());
    }
    
    @Test
    public void allEven() {
        List xs = new Node(2,new Node(4, new Node(6, new Empty())));
        List ys = new Node(2,new Node(4, new Node(7, new Empty())));
        assertEquals(xs, true);
        assertEquals(ys, false);
    }
    
    @Test
    public void allEven() {
        List xs = new Node(2,new Node(4, new Node(6, new Empty())));
        List ys = new Node(2,new Node(4, new Node(7, new Empty())));
        assertEquals(xs, true);
        assertEquals(ys, false);
    }
    
    @Test
    public void evens() {
        List xs = new Node(2,new Node(4, new Node(6, new Empty())));
        List ys = new Node(2,new Node(4, new Node(7, new Empty())));
        assertEquals(xs, true);
        assertEquals(ys, false);
    }*/
    

}
