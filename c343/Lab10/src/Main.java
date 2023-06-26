/**
 * TODO
 * You will be implementing nearestNeighbor using Binary Heaps, described by
 * your UIs in lab. You will submit to autograder for testing, as you may
 * not have implemented your own BinaryHeap yet. 
 */
public class Main {
    static Point NNHeap(KDTree t, Rect region, Point p) throws EmptyTreeE {

        BinaryHeap<KDTree> h = new BinaryHeap<>();
        int minDistance = Integer.MAX_VALUE;
        Point returnPoint = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

        h.insert(new Item<>(t, t.getText(), 0));

        while(!h.isEmpty() && h.findMin().getValue()<minDistance){

            Point p1 = h.findMin().getData().getPoint();
            KDTree tNew = h.extractMin().getData();

            int d1 = p1.distanceSquaredTo(p);
            if (d1 < minDistance){
                minDistance = d1;
                returnPoint = p1;
            }

            if (!tNew.getChild1().isEmpty()){
                h.insert(new Item<>(tNew.getChild1(), tNew.getChild1().getText(), (tNew.getRegion1(region)).distanceSquaredTo(p)));
            }

            if (!tNew.getChild2().isEmpty()){
                h.insert(new Item<>(tNew.getChild2(), tNew.getChild2().getText(), (tNew.getRegion2(region)).distanceSquaredTo(p)));
            }
        }

        return returnPoint;

    }
}
