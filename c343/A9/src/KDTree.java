import java.util.*;

class EmptyTreeE extends Exception {
}

abstract class XTree implements TreePrinter.PrintableNode {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract Point getPoint() throws EmptyTreeE;

    abstract YTree getLeftT() throws EmptyTreeE;

    abstract YTree getRightT() throws EmptyTreeE;

    abstract boolean isEmpty();

    //--------------------------
    // Insertion and queries
    //--------------------------

    abstract XTree insert(Point p);

    abstract boolean find(Point p);

    abstract Set<Point> rangeSearch(Rect range, Rect region);

    abstract Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE;

    abstract Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE;
}

abstract class YTree implements TreePrinter.PrintableNode {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract Point getPoint() throws EmptyTreeE;

    abstract XTree getUnderT() throws EmptyTreeE;

    abstract XTree getAboveT() throws EmptyTreeE;

    abstract boolean isEmpty();

    //--------------------------
    // Insertion and queries
    //--------------------------

    abstract YTree insert(Point p);

    abstract boolean find(Point p);

    abstract Set<Point> rangeSearch(Rect range, Rect region);

    abstract Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE;

    abstract Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE;

}

//-----------------------------------------------------------------------
// Empty trees

class XEmpty extends XTree {

    //--------------------------
    // Printable interface
    //--------------------------

    public String getText() {
        return "";
    }

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    Point getPoint() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    YTree getLeftT() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    YTree getRightT() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    boolean isEmpty() {
        return true;
    }

    //--------------------------
    // Insertion and queries
    //--------------------------

    XTree insert(Point p) {
        return new XNode(p, new YEmpty(), new YEmpty());
    }

    boolean find(Point p) {
        return false;
    }

    Set<Point> rangeSearch(Rect range, Rect region) {
        return new HashSet<>();
    }

    Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE {
        throw new EmptyTreeE();
    }

}

class YEmpty extends YTree {

    //--------------------------
    // Printable interface
    //--------------------------

    public String getText() {
        return "";
    }

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    Point getPoint() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    XTree getUnderT() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    XTree getAboveT() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    boolean isEmpty() {
        return true;
    }

    //--------------------------
    // Insertion and queries
    //--------------------------

    YTree insert(Point p) {
        return new YNode(p, new XEmpty(), new XEmpty());
    }

    boolean find(Point p) {
        return false;
    }

    Set<Point> rangeSearch(Rect range, Rect region) {
        return new HashSet<>();
    }

    Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE {
        throw new EmptyTreeE();
    }
}

//-----------------------------------------------------------------------
// Non-Empty trees

class XNode extends XTree {
    private Point point;
    private YTree left, right;

    XNode(Point point, YTree left, YTree right) {
        this.point = point;
        this.left = left;
        this.right = right;
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public String getText() {
        return point.toString();
    }

    public TreePrinter.PrintableNode getLeft() {
        return left;
    }

    public TreePrinter.PrintableNode getRight() {
        return right;
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    Point getPoint() {
        return point;
    }

    YTree getLeftT() {
        return left;
    }

    YTree getRightT() {
        return right;
    }

    boolean isEmpty() {
        return false;
    }

    //--------------------------
    // Insertion and queries
    //--------------------------

    XTree insert(Point p) {
        if (p.getX() < point.getX())
            return new XNode(point, left.insert(p), right);
        else
            return new XNode(point, left, right.insert(p));
    }

    boolean find(Point p) {
        if (p == point) return true;
        if (p.getX() < point.getX()) return left.find(p);
        else return right.find(p);
    }

    /*
     * We want to find all the points that lie inside the rectangle
     * 'range'. The parameter 'region' is the entire region
     * represented by this tree.
     *
     * The idea is to check if the region represented by this tree
     * intersects the range of interest. If there is no intersection,
     * we return an empty set. Otherwise, we search this tree in
     * detail as follows. We split the region represented by this tree
     * into a left region and a right region around the current point
     * and make recursive calls. The find set is the union of the the
     * two sets produced by the recursive calls additionally including the
     * current point if it is contained in the desired range.
     */


    //Range is a rectangle of interest
    //Region is the entire region represented by the tree
    Set<Point> rangeSearch(Rect range, Rect region) {



        Set<Point> lRegion = left.rangeSearch(range, region.leftOf(point));
        Set<Point> rRegion = right.rangeSearch(range, region.rightOf(point));


        lRegion.addAll(rRegion);

        if (range.contains(point)) {
            lRegion.add(point);
        }

        return lRegion;

    }

    /*
     * This is the most complicated method in this assignment.
     *
     * The three parameters are as follows. We are looking for the
     * nearest neighbor of p in the given region but excluding every
     * point in the set 'excludes'.
     *
     * Here is the strategy I recommend:
     *
     * - start with a safe appoximation for the 'result' and
     *   'distance' by creating a new point at "infinity": use
     *   Integer.MAX_VALUE for the coordinate and use
     *   Integer.MAX_VALUE as the distance. We will now try to refine
     *   this approximation by trying to find a better point as
     *   'result' and update 'distance' accordingly. If however there
     *   are no more points, this bad approximation will be our final
     *   result. In other words, we may get the point at infinity as
     *   the nearest neighbor if no other points remain.
     *
     * - the first refinement we can try to 'result' and 'distance' is
     *   to check if we can use the current point (the root of the
     *   current tree). If that point is not in the 'excludes' set,
     *   then we can use and we can use it to update 'result' and
     *   'distance'.
     *
     * - The next refinement is to try to find a closer point by
     *   recursively visiting the subtrees. But which subtree to visit
     *   first? If the point p happens to be to the left of the
     *   current point, then it is more likely that its closest
     *   neighbor is to the left, so we formulate a plan to visit the
     *   left subtree first and then the right subtree. Otherwise we
     *   try to visit the right subtree first and then the left
     *   subtree.
     *
     * - Now we put our plan into action. We want to make a recursive
     *   call to one of the subtrees hoping to improve our
     *   approximation. However, sometimes we might be able to detect,
     *   before making the recursive call, that the call will be
     *   useless and can be avoided. This happens if the closest
     *   distance from p to the region represented by the subtree is
     *   greater than or equal to the current approximation. In other
     *   words, every point in that region is farther than our current
     *   approximation so there is no reason to visit that subtree at
     *   all.
     *
     * - If we decide that it is worth it to visit the subtree, then
     *    we making the recursive call, and update our approximation
     *    if the recursive call returns a point that is closer to p.
     *
     */


    Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE {
        Point returnPoint = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        int minDistance = Integer.MAX_VALUE;

        if (!excludes.contains(this.point)){

            returnPoint = this.point;
            minDistance = point.distanceSquaredTo(p);
        }

        if (p.getX() < point.getX()){
            Rect lregion = region.leftOf(p);
            if (lregion.distanceSquaredTo(p) < minDistance) {
                minDistance = lregion.distanceSquaredTo(p);
                try {
                    Point tempPoint = left.nearestNeighbor(p, lregion, excludes);
                    if (tempPoint.distanceSquaredTo(p) < minDistance){
                        minDistance = tempPoint.distanceSquaredTo(p);
                        returnPoint = tempPoint;
                    }
                    if (tempPoint.distanceSquaredTo(p) > region.rightOf(point).distanceSquaredTo(p)){
                        Point tempPoint2 = right.nearestNeighbor(p, region.rightOf(point), excludes);
                        if (tempPoint2.distanceSquaredTo(p) < minDistance){
                            minDistance = tempPoint2.distanceSquaredTo(p);
                            returnPoint = tempPoint2;
                        }
                    }
                } catch(EmptyTreeE e){
                    System.out.print("EmptyTree");
                }
            }
        }

        else{
            Rect rRegion = region.rightOf(p);
            if (rRegion.distanceSquaredTo(p) < minDistance) {
                minDistance = rRegion.distanceSquaredTo(p);
                try {
                    Point tempPoint = right.nearestNeighbor(p, rRegion, excludes);
                    if (tempPoint.distanceSquaredTo(p) < minDistance){
                        minDistance = tempPoint.distanceSquaredTo(p);
                        returnPoint = tempPoint;
                    }
                    else if (tempPoint.distanceSquaredTo(p) > region.leftOf(p).distanceSquaredTo(p)){
                        Point tempPoint2 = left.nearestNeighbor(p, region.leftOf(p), excludes);
                        if (tempPoint2.distanceSquaredTo(p) < minDistance){
                            minDistance = tempPoint2.distanceSquaredTo(p);
                            returnPoint = tempPoint2;
                        }
                    }

                } catch(EmptyTreeE e){
                    System.out.print("EmptyTree");
                }
            }
        }

        return returnPoint;

    }

    /*
     * If your nearestNeighbor works correctly, this method is
     * relatively easy. You repeatedly compute the nearest neighbor
     * making sure each successive call excludes the previous
     * neighbors from the search.
     */
    Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE {

        Set<Point> returnNeighbors = new HashSet<>();

        for(int i = 0; i < k;){
            Point tempNeighbor = nearestNeighbor(p, region, returnNeighbors);
            if (!returnNeighbors.contains(tempNeighbor)){
                returnNeighbors.add(tempNeighbor);
                i++;
            }
        }

        return returnNeighbors;
    }
}

class YNode extends YTree {
    private Point point;
    private XTree under, above;

    YNode(Point point, XTree under, XTree above) {
        this.point = point;
        this.under = under;
        this.above = above;
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public String getText() {
        return point.toString();
    }

    public TreePrinter.PrintableNode getLeft() {
        return under;
    }

    public TreePrinter.PrintableNode getRight() {
        return above;
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    Point getPoint() {
        return point;
    }

    XTree getUnderT() {
        return under;
    }

    XTree getAboveT() {
        return above;
    }

    boolean isEmpty() {
        return false;
    }

    //--------------------------
    // Insertion and queries
    //--------------------------

    YTree insert(Point p) {
        if (p.getY() < point.getY())
            return new YNode(point, under.insert(p), above);
        else
            return new YNode(point, under, above.insert(p));
    }

    boolean find(Point p) {
        if (p == point) return true;
        if (p.getY() < point.getY()) return under.find(p);
        else return above.find(p);
    }

    Set<Point> rangeSearch(Rect range, Rect region) {
        Set<Point> upRegion = under.rangeSearch(range, region.leftOf(point));
        Set<Point> downRegion = above.rangeSearch(range, region.rightOf(point));


        upRegion.addAll(downRegion);

        if (range.contains(point)) {
            upRegion.add(point);
        }

        return upRegion;


    }

    Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE {
        Point returnPoint = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        int minDistance = Integer.MAX_VALUE;

        if (!excludes.contains(this.point)){

            returnPoint = this.point;
            minDistance = point.distanceSquaredTo(p);

        }

        if (p.getY() < point.getY()){
            Rect downRegion = region.underOf(p);
            if (downRegion.distanceSquaredTo(p) < minDistance) {
                minDistance = downRegion.distanceSquaredTo(p);

                try {

                    Point tempPoint = under.nearestNeighbor(p, downRegion, excludes);
                    if (tempPoint.distanceSquaredTo(p) < minDistance){
                        minDistance = tempPoint.distanceSquaredTo(p);
                        returnPoint = tempPoint;
                    }

                    else if (tempPoint.distanceSquaredTo(p) > region.rightOf(p).distanceSquaredTo(p)){
                        Point tempPoint2 = above.nearestNeighbor(p, region.rightOf(p), excludes);
                        if (tempPoint2.distanceSquaredTo(p) < minDistance){
                            minDistance = tempPoint2.distanceSquaredTo(p);
                            returnPoint = tempPoint2;
                        }
                    }
                } catch(EmptyTreeE e){
                    System.out.print("EmptyTree");
                }
            }
        }

        else{
            Rect upRegion = region.aboveOf(p);
            if (upRegion.distanceSquaredTo(p) < minDistance) {
                minDistance = upRegion.distanceSquaredTo(p);
                try {
                    Point tempPoint = above.nearestNeighbor(p, upRegion, excludes);
                    if (tempPoint.distanceSquaredTo(p) < minDistance){
                        minDistance = tempPoint.distanceSquaredTo(p);
                        returnPoint = tempPoint;
                    }
                    else if (tempPoint.distanceSquaredTo(p) > region.leftOf(p).distanceSquaredTo(p)){
                        Point tempPoint2 = under.nearestNeighbor(p, region.leftOf(p), excludes);
                        if (tempPoint2.distanceSquaredTo(p) < minDistance){
                            minDistance = tempPoint2.distanceSquaredTo(p);
                            returnPoint = tempPoint2;
                        }
                    }

                } catch(EmptyTreeE e){
                    System.out.print("EmptyTree");
                }
            }
        }

        return returnPoint;
    }

    Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE {
        Set<Point> returnNeighbors = new HashSet<>();

        for(int i = 0; i < k;){
            Point tempNeighbor = nearestNeighbor(p, region, returnNeighbors);
            returnNeighbors.add(tempNeighbor);
            i++;
        }

        return returnNeighbors;
    }
}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------


