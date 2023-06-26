import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {

    @Test
    public void insertBTest(){
        BinTree t = new Node(50, new Node(30, BinTree.makeLeaf(20), BinTree.makeLeaf(40)),
                new Node(70, BinTree.makeLeaf(60), BinTree.makeLeaf(80)));

        t = t.insertB(90);
        TreePrinter.print(t);

        t = t.insertB(61);
        TreePrinter.print(t);

        t = t.insertB(50);
        TreePrinter.print(t);

    }


    @Test
    public void rotateRightTest(){
        BinTree t = new Node(50, new Node(30, BinTree.makeLeaf(20), BinTree.makeLeaf(40)),
                new Node(70, BinTree.makeLeaf(60), BinTree.makeLeaf(80)));

        t = t.insertB(41);
        TreePrinter.print(t);
        t = t.insertB(42);
        TreePrinter.print(t);
        t = t.insertB(43);
        TreePrinter.print(t);
        t = t.insertB(44);
        TreePrinter.print(t);
        t = t.insertB(45);
        TreePrinter.print(t);



    }


    @Test
    public void rotateLeftTest(){
        BinTree t = new Node(50, new Node(30, BinTree.makeLeaf(20), BinTree.makeLeaf(40)),
                new Node(70, BinTree.makeLeaf(60), BinTree.makeLeaf(80)));

        t = t.insertB(61);
        TreePrinter.print(t);
        t = t.insertB(62);
        TreePrinter.print(t);
        t = t.insertB(63);
        TreePrinter.print(t);
        t = t.insertB(64);
        TreePrinter.print(t);
        t = t.insertB(65);
        TreePrinter.print(t);

    }

    @Test
    public void deleteBTest() throws EmptyTreeE{

        BinTree t = new Node(50, new Node(30, BinTree.makeLeaf(20), BinTree.makeLeaf(40)),
                new Node(70, BinTree.makeLeaf(60), BinTree.makeLeaf(80)));

        TreePrinter.print(t.deleteB(50));
        TreePrinter.print(t.deleteB(30));
        TreePrinter.print(t.deleteB(80));

    }

    @Test
    public void shrinkTest() throws EmptyTreeE{

        BinTree t = new Node(50, new Node(30, BinTree.makeLeaf(20), BinTree.makeLeaf(40)),
                new Node(70, BinTree.makeLeaf(60), BinTree.makeLeaf(80)));

        System.out.print(t.shrink().getFirst() + "\n");
        TreePrinter.print(t.shrink().getSecond());

        t = t.insertB(90);

        System.out.print(t.shrink().getFirst() + "\n");
        TreePrinter.print(t.shrink().getSecond());

        t = t.insertB(100);

        System.out.print(t.shrink().getFirst() + "\n");
        TreePrinter.print(t.shrink().getSecond());
    }

}
