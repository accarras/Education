import java.util.LinkedList;
import java.util.function.BinaryOperator;

/**
 * Solve every problem below using ONE call to reduce. Do not use
 * explicit loops, explicit recursion, or iterators.
 */

public class TreeFunctions {

    /*
                                            //wtd node                //wtd leaf
    <R, S> BinTree<R, S> map(Function<N, R> fnode, Function<L, S> fleaf) {
        return new Node<>(fnode.apply(data), left.map(fnode,fleaf), right.map(fnode,fleaf));
    }

                              //base case                     //function applied to each stage
    <R> R reduce(Function<L, R> base, TriFunction<N, R, R, R> f) {
        return f.apply(data, left.reduce(base,f), right.reduce(base,f));
    }
    littleTree.reduce(leaf -> leaf, (node, left, right) -> node + left + right);

    */

    /*

    // Simple reduce, sum all the nodes in t7
        int sum7 = t7.reduce(n -> n, (n,r1,r2) -> n+r1+r2);
        System.out.printf("Sum of all numbers in t7 = %d%n", sum7);

        // Add1 one to all leaves in t7 and then sum
        sum7 = t7.map(n->n, l -> l+1).reduce(n->n, (n,r1,r2) -> n+r1+r2);
        System.out.printf("Sum of all numbers in t7 after adding one to all leaves = %d%n", sum7);

     */


    /* Count the total number of nodes */
    static <N,L> int countNodes (BinTree<N,L> t) {

        // base case = 0
        // keep a running counter of nodes, and increment by 1 for every node
        // node, left, right
       return t.reduce(n->1, (n, l, r) ->  l+r+1);

    }

    /* Count the leaves */
    static <N,L> int countLeaves (BinTree<N,L> t) {

        return t.reduce(n->1, (n,r1,r2) -> r1+r2);

    }

    /* Count the number of internal nodes */
    static <N,L> int countInternalNodes (BinTree<N,L> t) {
        return countNodes(t) - countLeaves(t);
    }

    /* Return the maximum height of the tree */
    static <N,L> int height (BinTree<N,L> t) {

        return t.reduce(n->0, (n,l,r) -> 1+Math.max(l, r));
    }

    /* Check if a tree is balanced. A tree is balanced if every
     * subtree is balanced and if for every node, the heights of its
     * children are no more than 1 apart. To use 'reduce' here, the
     * result of every tree traversal will be a pair containing the
     * height of the tree and a boolean that states whether it is
     * balanced or not.
     */
    static <N,L> boolean isBalanced (BinTree<N,L> t) {

        Pair retB = t.reduce(d->new Pair<>(0, true), (n, l, r)->(new Pair(1+Math.max(l.getFirst(), r.getFirst()),(Math.abs(l.getFirst() - r.getFirst())<2 && l.getSecond() && r.getSecond()))));

        return (Boolean)retB.getSecond();
    }

    /* Preorder traversal */
    static List<Integer> preorder (BinTree<Integer,Integer> t) {

        return t.reduce(n->List.singleton(n), (n, l, r)->(List.singleton(n).append(l).append(r)));

    }

    /* Inorder traversal */
    static List<Integer> inorder (BinTree<Integer,Integer> t) {

        return t.reduce(n->List.singleton(n), (n, l, r)->(l.append(List.singleton(n)).append(r)));

    }

    /* Postorder traversal */
    static List<Integer> postorder (BinTree<Integer,Integer> t) {

        return t.reduce(n->List.singleton(n), (n, l, r)->l.append(r).append(List.singleton(n)));

    }

    /* Here the incoming tree is an expression tree (see book for
     * details). The big difference is that the data at each node is a
     * function that is applied to the results of its children.
     */
    static int eval (BinTree<BinaryOperator<Integer>,Integer> exp) {


        return exp.reduce(n->n, (n, l, r)->n.apply(l, r));


    }

    public static void main (String[] args) {
        BinTree<Integer,Integer> t123, t456, t7;

        t123 = new Node<>(1, new Leaf<>(2), new Leaf<>(3));
        t456 = new Node<>(4, new Leaf<>(5), new Leaf<>(6));
        t7 = new Node<>(7, t123, t456);

        System.out.printf("t7 has %d nodes%n", countNodes(t7));
        System.out.printf("t7 has %d leaves%n", countLeaves(t7));
        System.out.printf("t7 has %d internal nodes%n", countInternalNodes(t7));
        System.out.printf("t7 has height %d%n", height(t7));
        System.out.printf("is t7 balanced? %b%n", isBalanced(t7));
        System.out.printf("preorder traversal of t7: %s%n", preorder(t7));
        System.out.printf("inorder traversal of t7:  %s%n", inorder(t7));
        System.out.printf("postorder traversal of t7:  %s%n", postorder(t7));

        BinTree<BinaryOperator<Integer>,Integer> exp1, exp2, exp3;
        exp1 = new Node<>((a,b) -> a+b, new Leaf<>(2), new Leaf<>(3));
        exp2 = new Node<>((a,b) -> a-b, new Leaf<>(5), new Leaf<>(6));
        exp3 = new Node<>(Math::max, exp1, exp2);
        System.out.printf("evaluating exp1 => %d%n", eval(exp1));
        System.out.printf("evaluating exp2 => %d%n", eval(exp2));
        System.out.printf("evaluating exp3 => %d%n", eval(exp3));
    }

    /* Expected output:

t7 has 7 nodes
t7 has 4 leaves
t7 has 3 internal nodes
t7 has height 2
is t7 balanced? true
preorder traversal of t7: 7,1,2,3,4,5,6,#
inorder traversal of t7:  2,1,3,7,5,4,6,#
postorder traversal of t7:  2,3,1,5,6,4,7,#
evaluating exp1 => 5
evaluating exp2 => -1
evaluating exp3 => 5

    */

}

