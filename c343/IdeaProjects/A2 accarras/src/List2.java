import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class List2<E> {
    private LinkedList<E> deleg;

    List2() {
        this.deleg = new LinkedList<>();
    }

    E get (int index) {
        return deleg.get(index);
    }

    int length () {
        return deleg.size();
    }

    void add (E elem) {
        deleg.add(elem);
    }

    /**
     This is the same method from the class List1. Your solution must use
     the method listIterator to get an iterator for the list.
     */
    void triplicate () {
        LinkedList<E> xs = new LinkedList<>();
        Iterator<E> litr = deleg.listIterator();
        while(litr.hasNext()){
            xs.add(litr.next());
            xs.add(litr.next());
            xs.add(litr.next());
        }

        deleg = xs;
        return;
    }

    /**
     This is the method sum we wrote in the Jan. 15 lecture for the
     List1 class.  Your solution for this class must use the method
     listIterator to get an iterator for the list.
     */
    E sum (E base, BiFunction<E,E,E> acc) {

        Iterator<E> litr = deleg.listIterator();

        return acc.apply(litr.next(), sum(litr.next(), acc));

        //acc.apply(this.data,rest.sum(base,acc));
    }

    /**
     The following method reverses the list using iterators. A
     simple idea is to the 'descendingIterator' method to traverse
     the list backwards and build the reversed list.
     */
    void reverse () {

        LinkedList<E> xs = new LinkedList<>();

        Iterator<E> litr = deleg.descendingIterator();
        while(litr.hasNext()){
           xs.add(litr.next());
        }

        deleg = xs;
    }

    /**
     The following method takes a predicate on values type E and
     returns the number of list elements that satisfy the
     predicate. Again use an iterator to traverse the list.
     */
    int countOccurrences(Predicate<E> pred) {

        int ocs = 0;
        Iterator<E> litr = deleg.descendingIterator();
        while(litr.hasNext()) {
            if (pred.test(litr.next())) {
                ocs++;
            }
        }

        return ocs;
    }

    void sort (Comparator<E> c) {
        deleg.sort(c);
    }

    /**
     This is similar to the method in List1.
     */
    static List2<Integer> makeIntList2 (int size, int bound) {

        List2<Integer> xs = new List2<>();
        for (int i = 0; i < size; i++){
            Random random = new Random();
            xs.add(random.nextInt(bound-1));
        }

        return xs;
    }
}
