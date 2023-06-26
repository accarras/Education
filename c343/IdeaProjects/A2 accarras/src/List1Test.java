import org.junit.Test;
import static org.junit.Assert.*;

public class List1Test {

    @Test
    public void length() {
        List1<Integer> l1 = new Node(3, (new Node(3, new Node(3, new Empty()))));
        assertEquals(3, l1.length());
    }

    @Test
    public void triplicate1() {
        List1<Integer> l1 = new Node(3, new Empty());
        assertEquals(new Node(3, (new Node(3, new Node(3, new Empty())))), l1.triplicate());
    }

    @Test
    public void isEmpty(){
        List1<Integer> l1 = new Empty();
        assertEquals(true, l1.isEmpty());
    }

    @Test
    public void makeIntList1(){
        List1<Integer> l1 = List1.makeIntList1(5, 5);
        assertNotEquals(l1, new Empty());

    }

    /*@Test
    public void quicksort(){



        List1<Integer> l1 = new Node(5, (new Node(2, (new Node(1, (new Node(4, new Node(3, new Empty()))))))));

        assertEquals(List1.quickSort(l1,compare), new Node(1, (new Node(2, (new Node(3, (new Node(4, new Node(5, new Empty()))))))));

    }*/

    /*@Test
    public <E> void sort2(){

        e2 = e1;
        assertEquals(List1.sort2(e1, e2));

    }*/

    @Test
    public void append() {
        List1<Integer> l1 = new Node(3, new Empty());
        assertEquals(l1.append(new Node(3, new Empty())), new Node(3, new Node(3, new Empty())));
    }


}

