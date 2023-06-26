import org.junit.Test;
import static org.junit.Assert.*;
import java.util.function.BinaryOperator;

public class TreeFunctionsTest {

    @Test
    public void countNodeTest () {
        BinTree<Integer,Integer> t123, t456, t7;

        t123 = new Node<>(1, new Leaf<>(2), new Leaf<>(3));
        t456 = new Node<>(4, new Leaf<>(5), new Leaf<>(6));
        t7 = new Node<>(7, t123, t456);

        BinTree<Integer, Integer> t2 = new Node<>(5, new Leaf<>(5), new Leaf<>(5));

        BinTree<Integer, Integer> t3 = new Leaf(5);

        assertEquals(TreeFunctions.countNodes(t7), 7);
        assertEquals(TreeFunctions.countNodes(t2), 3);
        assertEquals(TreeFunctions.countNodes(t3), 1);
    }

    @Test
    public void countLeavesTest () {
        BinTree<Integer,Integer> t123, t456, t7;

        t123 = new Node<>(1, new Leaf<>(2), new Leaf<>(3));
        t456 = new Node<>(4, new Leaf<>(5), new Leaf<>(6));
        t7 = new Node<>(7, t123, t456);

        BinTree<Integer, Integer> t2 = new Node<>(5, new Leaf<>(5), new Leaf<>(5));

        BinTree<Integer, Integer> t3 = new Leaf(5);

        assertEquals(TreeFunctions.countLeaves(t7), 4);
        assertEquals(TreeFunctions.countLeaves(t2), 2);
        assertEquals(TreeFunctions.countLeaves(t3), 1);
    }

    @Test
    public void countInternalNodesTest () {
        BinTree<Integer,Integer> t123, t456, t7;

        t123 = new Node<>(1, new Leaf<>(2), new Leaf<>(3));
        t456 = new Node<>(4, new Leaf<>(5), new Leaf<>(6));
        t7 = new Node<>(7, t123, t456);

        BinTree<Integer, Integer> t2 = new Node<>(5, new Leaf<>(5), new Leaf<>(5));

        BinTree<Integer, Integer> t3 = new Leaf(5);

        assertEquals(TreeFunctions.countInternalNodes(t7), 3);
        assertEquals(TreeFunctions.countInternalNodes(t2), 1);
        assertEquals(TreeFunctions.countInternalNodes(t3), 0);
    }

    @Test
    public void heightTest () {
        BinTree<Integer,Integer> t123, t456, t7;

        t123 = new Node<>(1, new Leaf<>(2), new Leaf<>(3));
        t456 = new Node<>(4, new Leaf<>(5), new Leaf<>(6));
        t7 = new Node<>(7, t123, t456);

        BinTree<Integer, Integer> t2 = new Node<>(5, new Leaf<>(5), new Leaf<>(5));

        BinTree<Integer, Integer> t3 = new Leaf(5);

        assertEquals(TreeFunctions.height(t7), 2);
        assertEquals(TreeFunctions.height(t2), 1);
        assertEquals(TreeFunctions.height(t3), 0);
    }

    @Test
    public void isBalancedTest () {
        BinTree<Integer,Integer> t123, t456, t7;

        t123 = new Node<>(1, new Leaf<>(2), new Leaf<>(3));
        t456 = new Node<>(4, new Leaf<>(5), new Leaf<>(6));
        t7 = new Node<>(7, t123, t456);

        BinTree<Integer, Integer> t2 = new Node<>(5, new Leaf<>(5), new Leaf<>(5));

        BinTree<Integer, Integer> t3 = new Leaf(5);

        BinTree<Integer, Integer> tc = new Node<>(5, t7, t3);

        assertTrue(TreeFunctions.isBalanced(t7));
        assertTrue(TreeFunctions.isBalanced(t2));
        assertTrue(TreeFunctions.isBalanced(t3));
        assertFalse(TreeFunctions.isBalanced(tc));
    }

    @Test
    public void preOrderTest () {
        BinTree<Integer,Integer> t123, t456, t7;

        t123 = new Node<>(1, new Leaf<>(2), new Leaf<>(3));
        t456 = new Node<>(4, new Leaf<>(5), new Leaf<>(6));
        t7 = new Node<>(7, t123, t456);

        BinTree<Integer, Integer> t2 = new Node<>(5, new Leaf<>(5), new Leaf<>(5));

        BinTree<Integer, Integer> t3 = new Leaf(5);


        List<Integer> l1 = new NodeL<>(7,new NodeL<>(1,new NodeL<>(2,new NodeL<>(3,new NodeL<>(4,new NodeL<> (5,new NodeL<>(6,new EmptyL<>())))))));
        List<Integer> l2 = new NodeL<>(5, new NodeL<>(5, new NodeL<>(5, new EmptyL<>())));
        List<Integer> l3 = new NodeL<>(5, new EmptyL<>());

        assertEquals(TreeFunctions.preorder(t7).toString(),l1.toString());
        assertEquals(TreeFunctions.preorder(t2).toString(),l2.toString());
        assertEquals(TreeFunctions.preorder(t3).toString(),l3.toString());

    }

    @Test
    public void inOrderTest () {
        //2,1,3,7,5,4,6
        BinTree<Integer,Integer> t123, t456, t7;

        t123 = new Node<>(1, new Leaf<>(2), new Leaf<>(3));
        t456 = new Node<>(4, new Leaf<>(5), new Leaf<>(6));
        t7 = new Node<>(7, t123, t456);

        BinTree<Integer, Integer> t2 = new Node<>(5, new Leaf<>(5), new Leaf<>(5));

        BinTree<Integer, Integer> t3 = new Leaf(5);


        List<Integer> l1 = new NodeL<>(2,new NodeL<>(1,new NodeL<>(3,new NodeL<>(7,new NodeL<>(5,new NodeL<> (4,new NodeL<>(6,new EmptyL<>())))))));
        List<Integer> l2 = new NodeL<>(5, new NodeL<>(5, new NodeL<>(5, new EmptyL<>())));
        List<Integer> l3 = new NodeL<>(5, new EmptyL<>());

        assertEquals(TreeFunctions.inorder(t7).toString(),l1.toString());
        assertEquals(TreeFunctions.inorder(t2).toString(),l2.toString());
        assertEquals(TreeFunctions.inorder(t3).toString(),l3.toString());

    }

    @Test
    public void postOrderTest () {
        //2,3,1,5,6,4,7
        BinTree<Integer,Integer> t123, t456, t7;

        t123 = new Node<>(1, new Leaf<>(2), new Leaf<>(3));
        t456 = new Node<>(4, new Leaf<>(5), new Leaf<>(6));
        t7 = new Node<>(7, t123, t456);

        BinTree<Integer, Integer> t2 = new Node<>(5, new Leaf<>(5), new Leaf<>(5));

        BinTree<Integer, Integer> t3 = new Leaf(5);


        List<Integer> l1 = new NodeL<>(2,new NodeL<>(3,new NodeL<>(1, new NodeL<>(5,new NodeL<>(6, new NodeL<> (4,new NodeL<>(7,new EmptyL<>())))))));
        List<Integer> l2 = new NodeL<>(5, new NodeL<>(5, new NodeL<>(5, new EmptyL<>())));
        List<Integer> l3 = new NodeL<>(5, new EmptyL<>());

        assertEquals(TreeFunctions.postorder(t7).toString(),l1.toString());
        assertEquals(TreeFunctions.postorder(t2).toString(),l2.toString());
        assertEquals(TreeFunctions.postorder(t3).toString(),l3.toString());

    }

    @Test
    public void evalTest(){
        BinTree<BinaryOperator<Integer>,Integer> exp1, exp2, exp3;
        exp1 = new Node<>((a,b) -> a+b, new Leaf<>(2), new Leaf<>(3));
        exp2 = new Node<>((a,b) -> a-b, new Leaf<>(5), new Leaf<>(6));
        exp3 = new Node<>(Math::max, exp1, exp2);

        /*
        evaluating exp1 => 5
        evaluating exp2 => -1
        evaluating exp3 => 5

         */
        assertEquals(TreeFunctions.eval(exp1), 5);
        assertEquals(TreeFunctions.eval(exp2), -1);
        assertEquals(TreeFunctions.eval(exp3), 5);
    }

}
