import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Today's lab is meant to be another application of trees that
 * could potentially be a technical interview question. (I have
 * seen a similar problem to this in a technical interview
 * before)
 *
 * Your UIs will explain the problem to you and they will give
 * you some examples. You should try to think about various
 * examples that would be helpful for you to draw out.
 */

class Edges {
    /**
     *
     * Find the rightmost edge of a binary tree,
     * which is defined as the rightmost node on each level of the
     * tree.
     *
     * For example, in this tree:
     *             1
     *            / \
     *           2   3
     *          / \ / \
     *         4  5 6  7
     *  We return {1, 3, 7}. Easy peezy.
     *
     *  Not quite.
     *
     *  This is a situation where it is helpful to draw out a more
     *  complicated example and ask your interviewer lots of
     *  questions. We never said it was a FULL binary tree.
     *
     *  In the following tree:
     *             1
     *            / \
     *           2   3
     *          / \ /
     *         4  5 6
     *        /
     *       7
     *  It should return {1, 3, 6, 7}, which are the rightmost
     *  nodes in each level. If we only looked at the rightmost edge
     *  of the tree, we would get {1, 3}, which is wrong.
     *
     */

    static <E> List<E> findRight(BinTree<E> tree) {
        try {
            ArrayList<BinTree<E>> old = new ArrayList<>();
            ArrayList<BinTree<E>> next = new ArrayList<>();
            List<E> toreturn = new Empty<>();
            BinTree<E> hold = null;
            old.add(tree);
            while (!old.isEmpty()) {
                toreturn = toreturn.append(new Node(old.get(0).getData(), new Empty()));
                System.out.println("edge: " + old.get(0).getData());
                System.out.println("old: " + old.toString());
                while (!old.isEmpty()) {
                    hold = old.get(0);
                    old.remove(0);
                    if (!hold.getRight().isEmpty()){
                        next.add(hold.getRight());
                        System.out.println("I got right");
                    }
                    if (!hold.getLeft().isEmpty()){
                        next.add(hold.getLeft());
                        System.out.println("I got left");
                    }
                }
                old = next;
                next = new ArrayList<>();
            }

            return toreturn;
        } catch (EmptyTreeE e){
            System.out.println("error");
            return null;
        }
    }

    /**
     *
     * Find the leftmost edge of a binary tree,
     * which is defined as the leftmost node on each level of the
     * tree.
     *
     * For example, in this tree:
     *             1
     *            / \
     *           2   3
     *          / \ / \
     *         4  5 6  7
     *  We return {1, 2, 4}.
     *
     *  In the following tree:
     *             1
     *            / \
     *           2   3
     *            \ /
     *            5 6
     *               \
     *                7
     *
     *  It should return {1, 2, 5, 7}.
     *
     */

    static <E> List<E> findLeft(BinTree<E> tree) {
        try {
            ArrayList<BinTree<E>> old = new ArrayList<>();
            ArrayList<BinTree<E>> next = new ArrayList<>();
            List<E> toreturn = new Empty<>();
            BinTree<E> hold = null;
            old.add(tree);
            while (!old.isEmpty()) {
                toreturn = toreturn.append(new Node(old.get(old.size() - 1).getData(), new Empty()));
                System.out.println("edge: " + old.get(old.size() - 1).getData());
                System.out.println("old: " + old.toString());
                while (!old.isEmpty()) {
                    hold = old.get(0);
                    old.remove(0);
                    if (!hold.getRight().isEmpty()){
                        next.add(hold.getRight());
                        System.out.println("I got right");
                    }
                    if (!hold.getLeft().isEmpty()){
                        next.add(hold.getLeft());
                        System.out.println("I got left");
                    }
                }
                old = next;
                next = new ArrayList<>();
            }

            return toreturn;
        } catch (EmptyTreeE e){
            System.out.println("error");
            return null;
        }
    }
}