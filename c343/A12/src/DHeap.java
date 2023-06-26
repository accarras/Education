import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

class NoChildE extends Exception {
}

class DHeap<E> {
    private int d;
    private int size;
    private ArrayList<Item<E>> elems;

    DHeap(int d) {
        elems = new ArrayList<>();
        elems.add(0, null);
        size = 0;
        this.d = d;
    }

    DHeap(int d, ArrayList<Item<E>> es) {

        elems = new ArrayList<>();
        elems.add(0, null);
        size = es.size();
        this.d = d;

        for (int i = 0; i<size;i++) {
            Item<E> tempy = es.get(i);
            tempy.setPosition(i+1);
            elems.add(tempy);
        }

        for (int i = size/d; i > 0;i--) {
            moveDown(i);
        }
    }

    boolean isEmpty() {
        return size == 0;
    }

    int getSize() {
	return size;
    }

    Item<E> findMin() {
	    return elems.get(1);
    }

    List<Item<E>> getElems() {
        return elems.subList(1, size+1);
    }

    int getParentIndex(int i) throws NoParentE {

            if (i <= 1){
                throw new NoParentE();
            }
            return (i-2) / d+1;

    }

    int getChildIndex(int i, int j) throws NoChildE {


        if (d * i - d + j + 1 > size) {
            throw new NoChildE();
        }

        return (d * i - d + j + 1 );

    }

    void swap(int i, int j) {
        Item temp1 = elems.get(i);
        Item temp2 = elems.get(j);

        temp1.setPosition(j);
        temp2.setPosition(i);

        elems.set(i, temp2);
        elems.set(j, temp1);
    }

    int getKey(int i) {
        return elems.get(i).getValue();
    }

    void updateKey(int i, int value) {
        elems.get(i).setValue(value);
        moveUp(i);
    }

    void moveUp(int i) {

        try {

            int parent = getParentIndex(i);

            if (getKey(parent) > getKey(i)){
                swap(i, parent);
                moveUp(parent);
            }

        }catch(NoParentE NPE){
        }
    }

    void insert(Item<E> ek) {
        size++;

        ek.setPosition(size);
        elems.add(ek);

        moveUp(size);
    }

    /*
     * Returns the index of the smallest child.
     */
    int minChildIndex(int i) throws NoChildE {

        int bestChild = getChildIndex(i, 1);


        try {


        for (int j = 2; j <= d; j++){
            if (getKey(getChildIndex(i, j))< getKey(bestChild)){
                bestChild = getChildIndex(i, j);
            }
        }

        return bestChild;
        } catch (NoChildE NRCE){
            return bestChild;
        }
    }

    void moveDown(int i) {
        try {

            int child = this.minChildIndex(i);
            if (getKey(child) < getKey(i)) {
                swap(i, child);
                moveDown(child);
            }


        } catch (NoChildE NLCE) {
        }
    }

    Item<E> extractMin() {

        // Store the minimum value, and remove it from heap
        Item<E> root = findMin();
        swap(1, size);
        elems.remove(size);
        //elems.set(1, elems.remove(size));
        //elems.get(1).setPosition(1);
        size--;
        moveDown(1);

        return root;
    }

    public String toString() {
        return getElems().toString();
    }

}

