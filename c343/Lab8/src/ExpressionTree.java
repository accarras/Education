import java.util.Stack;
import java.util.function.BinaryOperator;

/**
 * Lab 8
 * Today's lab covers expression trees, which were introduced
 * in lecture yesterday. Expression trees are really useful for
 * (surprise, surprise) evaluating expressions.
 *
 * You have three tasks in lab today:
 * 1. Construct an ExpressionTree given a List of Data, where
 *    Data can be either a Num (value, not necessarily an int) or
 *    an Op (which has a name and a function). This will be in
 *    the constructor
 * 2. Traverse the resulting tree to return a string representing
 *    the expression in infix notation. Which traversal (inorder,
 *    preorder, postorder) should you use for this?
 * 3. Lastly, write a method to evaluate the expression, regardless
 *    of data type. This is where the true power of expression trees
 *    lies.
 */

abstract class Data<E>{
}

class Base<E> extends Data<E>{
    E value;

    // A Base has a value
    // For example, 'a' or 3 (could be any type)
    Base(E value){
        this.value = value;
    }
    E getData(){return this.value;}
    public String toString(){return String.valueOf(value);}
}

class Op<E> extends Data<E>{
    String name;
    BinaryOperator<E> f;

    // An Op has a function (for example, String::concat), and a name (for example, '+')
    // The name is used in the string representation of the tree
    Op(String name, BinaryOperator<E> f){
        this.name = name;
        this.f = f;
    }

    String getName(){return this.name;}
    BinaryOperator<E> getFunction(){return this.f;}
    public String toString(){return this.name;}
}

class ExpressionTree<E> {
    BinTree<Op<E>, E> root; // this will be the final tree


    /**
     * TODO 1
     *
     * Creates an expression tree from the given List
     * of Data using the algorithm described by your UIs
     * (and in the book, pages 109-111).
     */
    ExpressionTree(List<Data<E>> expression){
        Stack<BinTree<Op<E>,E>> s = new Stack<>();
        try {
            while (!expression.isEmpty()) {
                Data d = expression.getFirst();
                if (d instanceof Base){
                    Base<E> current = (Base)d;
                    s.push(new Leaf<>(current.getData()));
                }
                else{
                    Op<E> current = (Op<E>) d;
                    BinTree<Op<E>, E> pop1 = s.pop();
                    BinTree<Op<E>, E> pop2 = s.pop();

                    s.push(new Node<>(current, pop2, pop1));
                }
                expression = expression.getRest();
            }
            root = s.pop();


        }catch(EmptyListE e){

        }

    }

    public String toString(){
        return toStringHelper(root);
    }

    String toStringHelper(BinTree<Op<E>, E> t){
        /**
         * TODO 2
         *
         * Traverses the BinTree t and return a string in infix notation
         *
         * What is infix notation?
         * Given postfix expression "ab+cde+**", infix notation returns:
         * ((a + b) * (c * (d + e)))
         *
         * What would prefix notation be?
         * TODO: THIS ANSWER IS PART OF YOUR DEMO
         *
         */
        try {

            if (t.isLeaf()){
                return "" + t.getLeafData();
            }
            return ("(" + toStringHelper(t.getLeft()) + " " + t.getNodeData() + " " + toStringHelper(t.getRight()) + ")");
        }catch(WrongTreeE e){
            return "";
        }
    }

    static int evaluate(BinTree<Op<Integer>, Integer> t){
        /**
         * TODO 3
         *
         * This method should return the result of evaluating the BinTree t
         * of integers
         *
         * Hint - what order should you evaluate the BinTree in?
         *
         * How would you evaluate t if you didn't know the data type of the tree was int?
         * TODO: THIS ANSWER IS PART OF YOUR DEMO
         *
         * This will be really important for A7.
         */



        try {
            if (t.isLeaf()){
                return t.getLeafData();
            }
            else return (t.getNodeData().getFunction().apply(evaluate(t.getLeft()), evaluate(t.getRight())));
        }catch(WrongTreeE e){
            return 0;
        }
    }
}
