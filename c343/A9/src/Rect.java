public class Rect {
    private int xmin, ymin, xmax, ymax;

    Rect(int xmin, int ymin, int xmax, int ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    int getXmin() {
        return xmin;
    }

    int getYmin() {
        return ymin;
    }

    int getXmax() {
        return xmax;
    }

    int getYmax() {
        return ymax;
    }

    /*
     * Split the current rectangle at the x-coordinate of the given
     * point; return a new rectangle for the left portion.
     */
    Rect leftOf(Point p) {

        return new Rect(p.getX(), getYmin(), getXmax(), getYmax());
    }

    /*
     * Split the current rectangle at the x-coordinate of the given
     * point; return a new rectangle for the right portion.
     */
    Rect rightOf(Point p){
        return new Rect(getXmin(), getYmin(), p.getX(), getYmax());
    }

    /*
     * Split the current rectangle at the y-coordinate of the given
     * point; return a new rectangle for the below portion.
     */
    Rect underOf(Point p) {

        //return new Rect(getXmin(), getYmin(), getXmax(), getYmax());
        return new Rect(getXmin(), p.getY(), getXmax(), getYmax());
    }

    /*
     * Split the current rectangle at the y-coordinate of the given
     * point; return a new rectangle for the above portion.
     */
    Rect aboveOf(Point p) {

        return new Rect(getXmin(), getYmin(), getXmax(), p.getY());
    }

    /*
     * Checks if the given point is contained in the rectangle. A
     * point lying on the edge counts as being contained in the
     * rectangle.
     */
    boolean contains(Point p) {
        return ((getXmin() <= p.getX() && p.getX() <= getXmax()) && (getYmin() <= p.getY() && p.getY() <= getYmax()));

    }

    /*
     * Checks if this rectangle intersects the given rectangle.
     */
    boolean intersect(Rect r) {

        return (getXmin() <= r.getYmax() && getXmax() >= r.getXmin() && getYmax()>= r.getYmin() && getYmin() <= r.getYmax());

    }

    int square(int x){
        return x*x;
    }

    /*
     * Calculates the shortest distance from the given point to the
     * current rectangle. If the point is contained in the rectangle,
     * the distance is 0.
     */
    int distanceSquaredTo(Point p) {

        if (contains(p)){
            return 0;
        }

        // Above Rect
        else if (getXmin() < p.getX() && p.getX() < getXmax() && p.getY() > getYmax()){
            return square(p.getY() - getYmax());
        }

        // Below Rect
        else if (getXmin() < p.getX() && p.getX() < getXmax() && p.getY() < getYmin()){
            return square(getYmin() - p.getY());
        }

        // Left of Rect
        else if (getYmin() < p.getY() && p.getY() < getYmax() && p.getX() < getXmin()){
            return square(getXmin() - p.getX());
        }

        // Right of Rect
        else if (getYmin() < p.getY() && p.getY() < getYmax() && p.getX() > getXmax()){
            return square(p.getX() - getXmax());
        }

        // Top left
        else if (p.getY() > getYmax() && p.getX() < getXmin()){
            return p.distanceSquaredTo(new Point(getXmin(), getYmax()));
        }

        // Top right
        else if (p.getY() > getYmax() && p.getX() > getXmax()){
            return p.distanceSquaredTo(new Point(getXmax(), getYmax()));
        }

        // bottom left
        else if (p.getY() < getYmin() && p.getX() < getXmin()){
            return p.distanceSquaredTo(new Point(getXmin(), getYmin()));
        }

        // bottom Right
        else {
            return p.distanceSquaredTo(new Point(getXmax(), getYmin()));
        }

    }

    public boolean equals(Object o) {
        if (o instanceof Rect) {
            Rect other = (Rect) o;
            return xmin == other.xmin && xmax == other.xmax &&
                    ymin == other.ymin && ymax == other.ymax;
        } else return false;
    }

    public String toString() {
        return String.format("R[(%d,%d)--(%d,%d)]", xmin, ymin, xmax, ymax);
    }
}
