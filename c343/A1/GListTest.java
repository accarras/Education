import org.junit.Test;

import static org.junit.Assert.*;

public class GListTest {

    @Test
    public void length () {
        GList<String> ss = new Node<>("abc", new Node<>("defghijkl", new Empty<>()));
        assertEquals(2, ss.length());

        GList<Integer> ns = new Node<>(1, new Node<>(2, new Node<>(3, new Empty<>())));
        assertEquals(3, ns.length());
    }

    @Test
    public void sum () {
        GList<Integer> ns = new Node<>(1, new Node<>(2, new Node<>(40, new Empty<>())));
        int s = ns.sum(0, (a,b) -> a+b);
        assertEquals(43,s);
        int p = ns.sum(1, (a,b) -> a*b);
        assertEquals(80, p);

        GList<String> ss = new Node<>("abc", new Node<>("defghijkl", new Empty<>()));
        String con = ss.sum("", String::concat);
        assertEquals("abcdefghijkl", con);
        

    }

}
