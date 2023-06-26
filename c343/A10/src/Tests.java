import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void isEmptyTest(){

        BinaryHeap h = new BinaryHeap();
        DHeap Dh = new DHeap(3);
        assertTrue(h.isEmpty());
        assertTrue(Dh.isEmpty());

        h.insert(new Item(5, "obj1", 5));
        Dh.insert(new Item(5, "obj1", 5));

        assertTrue(!h.isEmpty());
        assertTrue(!Dh.isEmpty());


    }

    @Test
    public void getSizeTest(){
        BinaryHeap h = new BinaryHeap();
        DHeap Dh = new DHeap(3);

        assertEquals(h.getSize(), 0);
        h.insert(new Item<>(5, "new Obj", 1));
        assertEquals(h.getSize(), 1);

    }

    @Test
    public void findMinTest(){

        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        DHeap Dh = new DHeap(2);
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));

        BinaryHeap h2 = new BinaryHeap();
        h2.insert(new Item<>(5, "new Obj", 4));
        h2.insert(new Item<>(6, "new Obj2", 5));
        h2.insert(new Item<>(7, "new Obj3", 6));
        DHeap Dh2 = new DHeap(2);
        Dh2.insert(new Item<>(5, "new Obj", 1));
        Dh2.insert(new Item<>(6, "new Obj2", 2));
        Dh2.insert(new Item<>(7, "new Obj3", 3));

        BinaryHeap h3 = new BinaryHeap();
        h3.insert(new Item<>(5, "new Obj", 7));
        h3.insert(new Item<>(6, "new Obj2", 8));
        h3.insert(new Item<>(7, "new Obj3", 9));
        DHeap Dh3 = new DHeap(2);
        Dh3.insert(new Item<>(5, "new Obj", 1));
        Dh3.insert(new Item<>(6, "new Obj2", 2));
        Dh3.insert(new Item<>(7, "new Obj3", 3));

        assertEquals(h.findMin().getValue(), 1);
        assertEquals(h2.findMin().getValue(), 4);
        assertEquals(h3.findMin().getValue(), 7);
        assertEquals(Dh.findMin().getValue(), 1);
        assertEquals(Dh2.findMin().getValue(), 1);
        assertEquals(Dh3.findMin().getValue(), 1);

    }

    @Test
    public void getElemsTest(){

        int num = 4;
        ArrayList<Item<String>> items = new ArrayList<>();
        Random r = new Random(1);
        for (int i = 0; i < num; i++) {
            int k = r.nextInt(100);
            items.add(new Item<>("a" + k, "a" + k, k));
        }

        BinaryHeap h1 = new BinaryHeap(items);
        DHeap dh1 = new DHeap(3,items);
        h1.getElems();
        dh1.getElems();

    }

    @Test
    public void getParentIndexTest() throws NoParentE{
        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        h.insert(new Item<>(8, "new Obj3", 4));
        h.insert(new Item<>(9, "new Obj3", 5));
        DHeap Dh = new DHeap(2);
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));
        Dh.insert(new Item<>(8, "new Obj3", 4));
        Dh.insert(new Item<>(9, "new Obj3", 5));

        assertEquals(1, h.getParentIndex(2));
        assertEquals(h.getParentIndex(4), 2);
        assertEquals(h.getParentIndex(5), 2);
        assertEquals(Dh.getParentIndex(2), 2);
        assertEquals(Dh.getParentIndex(3), 3);

    }

    @Test
    public void getLeftChildIndexTest() throws NoLeftChildE {
        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        h.insert(new Item<>(8, "new Obj3", 4));
        h.insert(new Item<>(9, "new Obj3", 5));
        h.insert(new Item<>(10, "new Obj3", 6));
        h.insert(new Item<>(11, "new Obj3", 7));

        assertEquals(h.getLeftChildIndex(2), 4);
        assertEquals(h.getLeftChildIndex(1), 2);
        assertEquals(h.getLeftChildIndex(3), 6);
    }


        @Test
        public void getChildIndexTest() throws NoChildE{
            DHeap Dh = new DHeap(2);
            Dh.insert(new Item<>(5, "new Obj", 1));
            Dh.insert(new Item<>(6, "new Obj2", 2));
            Dh.insert(new Item<>(7, "new Obj3", 3));
            Dh.insert(new Item<>(8, "new Obj3", 4));
            Dh.insert(new Item<>(9, "new Obj3", 5));
            Dh.insert(new Item<>(10, "new Obj3", 6));
            Dh.insert(new Item<>(11, "new Obj3", 7));

            assertEquals(Dh.getChildIndex(2, 1), 4);
            assertEquals(Dh.getChildIndex(1, 1), 2);
            assertEquals(Dh.getChildIndex(3, 1), 6);
        }

    @Test
    public void getRightChildIndexTest() throws NoRightChildE{

        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        h.insert(new Item<>(8, "new Obj3", 4));
        h.insert(new Item<>(9, "new Obj3", 5));
        h.insert(new Item<>(10, "new Obj3", 6));
        h.insert(new Item<>(11, "new Obj3", 7));

        assertEquals(h.getRightChildIndex(2), 5);
        assertEquals(h.getRightChildIndex(1), 3);
        assertEquals(h.getRightChildIndex(3), 7);

    }

    @Test
    public void swapTest(){

        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        h.insert(new Item<>(8, "new Obj3", 4));
        h.insert(new Item<>(9, "new Obj3", 5));
        h.insert(new Item<>(10, "new Obj3", 6));
        h.insert(new Item<>(11, "new Obj3", 7));

        BinaryHeap Dh = new BinaryHeap();
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));
        Dh.insert(new Item<>(8, "new Obj3", 4));
        Dh.insert(new Item<>(9, "new Obj3", 5));
        Dh.insert(new Item<>(10, "new Obj3", 6));
        Dh.insert(new Item<>(11, "new Obj3", 7));

        System.out.print(h.toString());
        h.swap(2, 3);
        System.out.print(h.toString());
        h.swap(4, 5);
        System.out.print(h.toString());
        h.swap(6, 7);
        System.out.print(h.toString());

        System.out.print(h.toString());
        h.swap(2, 3);
        System.out.print(h.toString());
        h.swap(4, 5);
        System.out.print(h.toString());
        h.swap(6, 7);
        System.out.print(h.toString());

    }

    @Test
    public void getKeyTest(){

        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        h.insert(new Item<>(8, "new Obj3", 4));
        h.insert(new Item<>(9, "new Obj3", 5));
        h.insert(new Item<>(10, "new Obj3", 6));
        h.insert(new Item<>(11, "new Obj3", 7));

        BinaryHeap Dh = new BinaryHeap();
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));
        Dh.insert(new Item<>(8, "new Obj3", 4));
        Dh.insert(new Item<>(9, "new Obj3", 5));
        Dh.insert(new Item<>(10, "new Obj3", 6));
        Dh.insert(new Item<>(11, "new Obj3", 7));

        assertEquals(h.getKey(1), 1);
        assertEquals(h.getKey(3), 3);
        assertEquals(h.getKey(5), 5);

        assertEquals(Dh.getKey(2), 2);
        assertEquals(Dh.getKey(4), 4);
        assertEquals(Dh.getKey(6), 6);

    }

    @Test
    public void insertTest(){

        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(1, "new Obj", 1));
        h.insert(new Item<>(2, "new Obj2", 2));
        h.insert(new Item<>(3, "new Obj3", 3));
        h.insert(new Item<>(4, "new Obj4", 4));
        h.insert(new Item<>(5, "new Obj5", 5));
        h.insert(new Item<>(6, "new Obj6", 6));
        h.insert(new Item<>(7, "new Obj7", 7));

        BinaryHeap Dh = new BinaryHeap();
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));
        Dh.insert(new Item<>(8, "new Obj4", 4));
        Dh.insert(new Item<>(9, "new Obj5", 5));
        Dh.insert(new Item<>(10, "new Obj6", 6));
        Dh.insert(new Item<>(11, "new Obj7", 7));

        //assertTrue(h.getElems().contains(1));
        //assertTrue(h.getElems().contains(3));
        //assertTrue(h.getElems().contains(5));
        System.out.print(Dh.getElems());

        //assertTrue(Dh.getElems().contains(1));
        //assertTrue(Dh.getElems().contains(3));
        //assertTrue(Dh.getElems().contains(5));

    }

    @Test
    public void extractMinTest(){

        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        h.insert(new Item<>(8, "new Obj3", 4));
        h.insert(new Item<>(9, "new Obj3", 5));
        h.insert(new Item<>(10, "new Obj3", 6));
        h.insert(new Item<>(11, "new Obj3", 7));

        BinaryHeap Dh = new BinaryHeap();
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));
        Dh.insert(new Item<>(8, "new Obj3", 4));
        Dh.insert(new Item<>(9, "new Obj3", 5));
        Dh.insert(new Item<>(10, "new Obj3", 6));
        Dh.insert(new Item<>(11, "new Obj3", 7));

        assertEquals(h.extractMin().getValue(), 1);
        assertEquals(h.extractMin().getValue(), 2);
        assertEquals(h.extractMin().getValue(), 3);

        assertEquals(Dh.extractMin().getValue(), 1);
        assertEquals(Dh.extractMin().getValue(), 2);
        assertEquals(Dh.extractMin().getValue(), 3);


    }

    @Test
    public void minChildIndexTest() throws NoLeftChildE{

        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        h.insert(new Item<>(8, "new Obj3", 4));
        h.insert(new Item<>(9, "new Obj3", 5));
        h.insert(new Item<>(10, "new Obj3", 6));
        h.insert(new Item<>(11, "new Obj3", 7));

        BinaryHeap Dh = new BinaryHeap();
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));
        Dh.insert(new Item<>(8, "new Obj3", 4));
        Dh.insert(new Item<>(9, "new Obj3", 5));
        Dh.insert(new Item<>(10, "new Obj3", 6));
        Dh.insert(new Item<>(11, "new Obj3", 7));

        assertEquals(h.minChildIndex(1), 2);
        assertEquals(h.minChildIndex(3), 6);
        assertEquals(h.minChildIndex(2), 4);

        assertEquals(Dh.minChildIndex(2), 4);
        assertEquals(Dh.minChildIndex(1), 2);
        assertEquals(Dh.minChildIndex(3), 6);

    }



}
