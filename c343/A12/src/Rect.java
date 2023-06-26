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

    //Rect(int xmin, int ymin, int xmax, int ymax)
    Rect leftOf(Point p) {
        return new Rect(getXmin(), getYmin(), p.getX(), getYmax());
    }

    /*
     * Split the current rectangle at the x-coordinate of the given
     * point; return a new rectangle for the right portion.
     */
    Rect rightOf(Point p){
        return new Rect(p.getX(), getYmin(), getXmax() , getYmax());
    }

    /*
     * Split the current rectangle at the y-coordinate of the given
     * point; return a new rectangle for the below portion.
     */
    Rect underOf(Point p) {

        //return new Rect(getXmin(), getYmin(), getXmax(), getYmax());
        return new Rect(getXmin() ,getYmin(), getXmax(), p.getY());
    }

    /*
     * Split the current rectangle at the y-coordinate of the given
     * point; return a new rectangle for the above portion.
     */
    Rect aboveOf(Point p) {
        return new Rect(getXmin(), p.getY(), getXmax(), getYmax());
    }

    /*
     * Checks if the given point is contained in the rectangle. A
     * point lying on the edge counts as being contained in the
     * rectangle.
     */
    boolean contains(Point p) {
        return ((getXmin() <= p.getX() && p.getX() <= getXmax())
                &&
                (getYmin() <= p.getY() && p.getY() <= getYmax()));
    }

    /*
     * Checks if this rectangle intersects the given rectangle.
     */
    boolean intersect(Rect r) {

        return  r.contains(new Point(getXmin(), getYmin())) ||
                r.contains(new Point(getXmin(), getYmax())) ||
                r.contains(new Point(getXmax(), getYmin())) ||
                r.contains(new Point(getXmax(), getYmax())) ||

                contains(new Point(r.getXmin(), r.getYmin())) ||
                contains(new Point(r.getXmin(), r.getYmax())) ||
                contains(new Point(r.getXmax(), r.getYmin())) ||
                contains(new Point(r.getXmax(), r.getYmax())) ||

                (r.getXmin() < getXmin() && getXmax() < r.getXmax() && getYmin() < r.getYmin() && r.getYmax() < getYmax()) ||

                (getXmin() < r.getXmin() && r.getXmax() < getXmax() && r.getYmin() < getYmin() && getYmax() < r.getYmax());

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


        // left side
        if (p.getX() < getXmin()){
            // Top left
            if (p.getY() > getYmax()){
                return p.distanceSquaredTo(new Point(getXmin(), getYmax()));
            }

            // bottom left
			else if (p.getY() < getYmin()){
                return p.distanceSquaredTo(new Point(getXmin(), getYmin()));
            }

            // Left of Rect
            else {
                return p.distanceSquaredTo(new Point(getXmin(), p.getY()));
            }

        }

        //right side
        else if (p.getX() > getXmax()){
            // Top right
            if (p.getY() > getYmax()){
                return p.distanceSquaredTo(new Point(getXmax(), getYmax()));
            }

            // bottom Right
            else if (p.getY() < getYmin()){
                return p.distanceSquaredTo(new Point(getXmax(), getYmin()));
            }

            // Right of Rect
            else {
                return p.distanceSquaredTo(new Point(getXmax(), p.getY()));

            }
        }

        //top and bottom
        else {
            // Above Rect
            if (p.getY() > getYmax ()){
                return p.distanceSquaredTo(new Point(p.getX(), getYmax()));
            }

            // Below Rect
            else if (p.getY() < getYmin()){
                return p.distanceSquaredTo(new Point(p.getX(), getYmin()));
            }

            else return 0;
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

    public int hashCode() {
        return Integer.hashCode(xmin) + Integer.hashCode(xmax) +
                Integer.hashCode(ymin) + Integer.hashCode(ymax);
    }
}
