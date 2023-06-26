/**
 * This week you learned about trees.
 *
 * One thing that was briefly introduced in lecture but not
 * thoroughly explained is how to traverse a tree. This is
 * a very important topic. There are three main types of
 * tree traversals, explained by your UIs in the mini-lecture.
 *
 * 1. In-order traversal (left, root, right)
 * 2. Pre-order traversal (root, left, right)
 * 3. Post-order traversal (left, right, root)
 *
 * There are each used in different situations, so it is
 * good to know all three.
 *
 * Implement each of them below.
 *
 * Keep in mind that you will be traversing over a BinTree,
 * and returning a List of the elements in the BinTree.
 */



class Traversal<E> {

    /**
     * An in-order traversal visits the nodes (surprise, surprise)
     * in order.
     *
     *                  1
     *                /  \
     *               2    3
     *             /  \  /  \
     *            4   5 6    7
     *
     * In-order should give {4, 2, 5, 1, 6, 3, 7}
     */

    List<E> inOrderList = new Empty<>();

     <E> List<E> inOrder(BinTree<E> tree) {



        try {

            inOrder(tree.getLeft());

            inOrderList.append(new Node(tree.getData(), new Empty()));

            //return new Node(tree.getData(), new Node(inOrder(tree.getLeft().getData()), inOrder(tree.getRight().getData())

            inOrder(tree.getRight());


            return (List<E>)inOrderList;

        }catch (EmptyTreeE e){
            return new Empty();
        }
    }

    /**
     * A pre-order traversal visits the root first.
     *
     *                  1
     *                /  \
     *               2    3
     *             /  \  /  \
     *            4   5 6    7
     *
     * Pre-order should give {1, 2, 4, 5, 3, 6, 7}
     */
    <E> List<E> preOrder(BinTree<E> tree){
        return new Empty<>();
    }

    /**
     * A post-order traversal visits the root last.
     *
     *                  1
     *                /  \
     *               2    3
     *             /  \  /  \
     *            4   5 6    7
     *
     * Post-order should give {4, 2, 5, 1, 6, 3, 7}
     */
    static <E> List<E> postOrder(BinTree<E> tree) {
        return new Empty<>();
    }
}
