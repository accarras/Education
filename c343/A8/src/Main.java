
public class Main {

    public static void main (String[] args) throws EmptyTreeE {
        BinTree t = new Node(50, new Node(30, BinTree.makeLeaf(20), BinTree.makeLeaf(40)),
                new Node(70, BinTree.makeLeaf(60), BinTree.makeLeaf(80)));

        System.out.println("Original tree: ");
        TreePrinter.print(t);

        System.out.println("Insert 41: ");
        TreePrinter.print(t.insert(41));
    }
}
