import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.BiFunction;

// -------------------------------------------------------

/**
 *
 * An exception to throw when attempting to insert elements into a
 * full hash table. 
 *
 */
class TableFullE extends Exception {}

// -------------------------------------------------------

/**
 *
 * The abstract class for the four hash tables we will implement. The
 * file HashTableTest has four test cases that should produce the same
 * information as Figures 5.5, 5.11, 5.13, and 5.18 in the book. You
 * should implement many more test cases!!!
 *
 */
abstract class HashTable {
    abstract void insert (int key) throws TableFullE;
    abstract void delete (int key);
    abstract boolean search (int key);
    abstract void hashToString();
}

// -------------------------------------------------------

/**
 *
 * An implementation of a hash table that uses separate chaining. The
 * constructor should take two arguments: an argument 'size' of type
 * int, and an argument 'hf' of type HashFunction. The constructor
 * should create an ArrayList of the given size and initialize each
 * entry in that ArrayList to an empty linked list. The three methods
 * should be implemented as described in the book. You should also
 * implement a toString method that returns a comprehensive string
 * showing the entire contents of the hash table for debugging and
 * testing purposes.
 *
 */
class HashSeparateChaining extends HashTable {

    int size;
    HashFunction hf;
    ArrayList<LinkedList> chains;

    HashSeparateChaining(int size, HashFunction hf){
        this.size = size;
        this.hf = hf;

        chains = new ArrayList<LinkedList>(size);

        for (int i=0; i<size; i++){
            chains.add(new LinkedList());
        }
    }

    void insert(int key){

        chains.get(hf.apply(key)).addLast(key);

    }

    void delete(int key){

        chains.get(hf.apply(key)).removeLastOccurrence(key);

    }

    boolean search(int key){
        if (chains.get(hf.apply(key)).contains(key)){
            return true;
        }

        return false;
    }

    void hashToString() {
        for (int i = 0; i < size; i++) {
            System.out.print(chains.get(i).toString() + "\n");
        }
    }

}

// -------------------------------------------------------

/**
 *
 * Before implementing the next three variants of hash tables, we will
 * implement a small class hierarchy of hash tables entries. There are
 * three kinds of entries: an entry that contains an 'int' value, an
 * entry that is empty and hence available, and an entry that is
 * marked as deleted. A deleted entry is available for insertions but
 * cannot be marked as empty. See the book for details. 
 *
 */
abstract class Entry {

    abstract boolean available ();

}

/**
 *
 * An instance of this class represents an entry that is
 * available. Don't forget to also implement a toString method.
 *
 */
class Empty extends Entry {

    boolean available() {
        return true;
    }
}

/**
 *
 * An instance of this class represents an entry that is
 * available. Don't forget to also implement a toString method.
 *
 */
class Deleted extends Entry {

    boolean available() {
        return true;
    }
}

/**
 *
 * A constructor of this class takes an 'int' representing a value
 * stored in the hash table. That entry is not available. Also don't
 * forget to implement a toString method.
 *
 */
class Value extends Entry {

    int entryValue;

    Value(int entryValue){
        this.entryValue = entryValue;
    }

    boolean available() {
        return false;
    }

    void entryToString(){
        System.out.print(entryValue);
    }
}

// -------------------------------------------------------

/**
 *
 * This class, although abstract, will have a constructor and an
 * implementation of each of the three methods: insert, delete, and
 * search. (Also don't forget a toString method.) The constructor
 * should take three arguments: an argument 'size' of type int, an
 * argument 'hf' of type HashFunction, and an argument 'f' of type
 * BiFunction<Integer,Integer,Integer>. The constructor should create
 * an ArrayList of the given size and initialize each slot in that
 * array with an Empty entry. The construct should also define a
 * BiFunction<Integer,Integer,Integer> called 'dhf' as follows:
 *
 *   this.dhf = (key,index) -> (hf.apply(key) + f.apply(key,index)) % size;
 *
 * This auxiliary hash function applies the regular hash function 'hf'
 * and then modifies the result using the BiFunction 'f' that depends
 * on the current index in the hash table. The subclasses for linear
 * probing, quadratic probing, and double hashing, will each pass an
 * appropriate function 'f' to control the auxiliary function. 
 *
 * Each of the methods insert, delete, and search takes a value 'key'
 * to process. Each of the methods will involve a loop that iterates
 * over all the positions in the ArrayList. At iteration 'i', an
 * integer position is calculated using:
 *
 *   int h = dhf.apply(key,i)
 *
 * For insert: if the position 'h' is available then the value 'key'
 * is stored. 
 *
 * For delete: if position 'h' has an entry of kind 'Value' and if the
 * stored integer is identical to 'key' then the entry is marked as
 * deleted.
 *
 * For search: if position 'h' is Empty then the item is not found. If
 * position 'h' has an entry of kind 'Value' and if the stored value
 * is identical to 'key' then the item is found.
 *
 */
abstract class HashTableAux extends HashTable {

    int size;
    ArrayList<Entry> chain;
    BiFunction<Integer,Integer,Integer> dhf;

    HashTableAux(int size, HashFunction hf, BiFunction<Integer,Integer,Integer> f){

        this.size = size;
        chain = new ArrayList<Entry>(size);

        for (int i=0; i<size; i++){
            chain.add(i, new Empty());
        }

        this.dhf = (key,index) -> (hf.apply(key) + f.apply(key,index)) % size;

    }

    void insert(int key) throws TableFullE {

        try {
            for (int i = 0; i < size; i++) {
                int h = dhf.apply(key, i);
                if (chain.get(h).available()) {
                    chain.add(h, new Value(key));
                    System.out.print("Aux insertion has happened\n");
                    break;
                }
                else if (i == size-1){
                    throw new TableFullE();
                }
            }
        }
        catch (TableFullE e){
            System.out.print("Table Full or Spot Unavailable!!\n");
        }
    }


    void delete(int key) {

        for (int i=0; i<size; i++){
            int h = dhf.apply(key,i);
            if (chain.get(h) instanceof Value && (((Value) chain.get(h)).entryValue == key)){
                chain.add(h, new Deleted());
            }
        }
    }

    boolean search(int key) {
        /*if position 'h' is Empty then the item is not found. If
         * position 'h' has an entry of kind 'Value' and if the stored value
         * is identical to 'key' then the item is found.*/

        for (int i=0; i<size; i++){
            int h = dhf.apply(key, i);
            if (chain.get(h) instanceof Value && (((Value) chain.get(h)).entryValue == key)){
                return true;
            }

            /*else if (chain.get(h) instanceof Empty){
                return false;
            }*/
        }

        return false;
    }

    void hashToString() {

        for (int i = 0; i < size; i++) {
            System.out.print(chain.get(i).toString() + "\n");
        }
    }
}

// -------------------------------------------------------


/**
 *
 * The only code in this class should be a constructor that takes two
 * arguments: an argument 'size' of type int and an argument 'hf' of
 * type HashFunction.
 *
 */
class HashLinearProbing extends HashTableAux {

    //int size;
    //HashFunction hf;
    //BiFunction Bif;

    HashLinearProbing(int sizeLP, HashFunction hf) {

        //this.dhf = (key,index) -> (hf.apply(key) + f.apply(key,index)) % size;
        super(sizeLP, hf, ((x,y) -> y%sizeLP));
        //this.size = sizeLP;
        //this.hf = hf;

    }
}


// -------------------------------------------------------

/**
 *
 * The only code in this class should be a constructor that takes two
 * arguments: an argument 'size' of type int and an argument 'hf' of
 * type HashFunction.
 *
 */
class HashQuadProbing extends HashTableAux {

    HashQuadProbing(int size, HashFunction hf) {

        //this.dhf = (key,index) -> (hf.apply(key) + f.apply(key,index)) % size;
        super(size, hf, (x, y)->(y*y)%size);
    }
}

// -------------------------------------------------------

/**
 *
 * The only code in this class should be a constructor that takes
 * three arguments: an argument 'size' of type int, an argument 'hf'
 * of type HashFunction, and a third argument 'hf2' represents the
 * secondary hash function.
 *
 */
class HashDouble extends HashTableAux {


    HashDouble(int size, HashFunction hf, HashFunction hf2) {

        super(size, hf, (x, y) -> hf2.apply(x));

    }

}


// -------------------------------------------------------
