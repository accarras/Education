import org.junit.Test;
import static org.junit.Assert.*;

public class List2Test {



/*@Test
   public void countOccurences() {
       List<Integer> l2 = new LinkedList<>();
       l2.add(1);l2.add(2);l2.add(2);l2.add(3);
       assertEquals(3, l2.countOccurrences(x -> x > 1).length());
   }*/

/*@Test
public void sum() {
    List2<Integer> xs = new List2<>();
    xs.add(1);xs.add(2);xs.add(2);xs.add(3);
    assertEquals(8, xs.sum());
}*/

    @Test
    public void makeIntList2() {
        assertNotEquals(List2.makeIntList2(5, 5), new Empty());
    }

    @Test
    public void triplicate2() {
        List2<Integer> l2 = new List2<>();
        l2.add(3);
        List2<Integer> l3 = new List2<>();
        l3.add(3);
        l3.add(3);
        l3.add(3);
        l2.triplicate();

        assertEquals(l3, l2);
    }
}
