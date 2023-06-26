import java.util.ArrayList;
import java.util.List;

// -----

abstract class XTree implements TreePrinter.PrintableNode {
    abstract Region findRegion(Point p);

    static XTree makeXTree(List<Point> xPoints, List<Point> yPoints, int bound) {
        if (xPoints.size() <= bound){
            return new XEmpty();
        }

        int h = xPoints.size()/2;
        int s = xPoints.size();

        List<Point> yLeft = new ArrayList<>();
        List<Point> yRight = new ArrayList<>();

        for(int y = 0; y < yPoints.size(); y++){
            if (xPoints.subList(0, h).contains(yPoints.get(y))){
                yLeft.add(yPoints.get(y));
            }

            if (xPoints.subList(h+1, s).contains(yPoints.get(y))){
                yRight.add(yPoints.get(y));
            }
        }

        return new XNode(xPoints.get(h),
                YTree.makeYTree(yLeft, xPoints.subList(0, h),  bound),
                YTree.makeYTree(yRight, xPoints.subList(h+1, s),  bound));
    }
}

abstract class YTree implements TreePrinter.PrintableNode {
    abstract Region findRegion(Point p);

    static YTree makeYTree(List<Point> yPoints, List<Point> xPoints, int bound) {
        if (yPoints.size() <= bound){
            return new YEmpty();
        }

        int h = yPoints.size()/2;
        int s = yPoints.size();

        List<Point> xLeft = new ArrayList<>();
        List<Point> xRight = new ArrayList<>();

        for(int x = 0; x < xPoints.size(); x++){
            if (yPoints.subList(0, h).contains(xPoints.get(x))){
                xLeft.add(xPoints.get(x));
            }

            if(yPoints.subList(h+1, s).contains(xPoints.get(x))){
                xRight.add(xPoints.get(x));
            }
        }

        return new YNode(yPoints.get(h),
                XTree.makeXTree(xLeft, yPoints.subList(0, h), bound),
                XTree.makeXTree(xRight, yPoints.subList(h+1, s), bound));
    }
}

// -----

class XEmpty extends XTree {
    public String getText() {
        return "";
    }

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    public Region findRegion(Point p) {
        return new Region();
    }
}

class YEmpty extends YTree {
    public String getText() {
        return "";
    }

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    public Region findRegion(Point p) {
        return new Region();
    }
}

// -----

class XNode extends XTree {
    private Point point;
    private YTree left, right;

    XNode(Point point, YTree left, YTree right) {
        this.point = point;
        this.left = left;
        this.right = right;
    }

    public String getText() {
        return point.toString();
    }

    public TreePrinter.PrintableNode getLeft() {
        return left;
    }

    public TreePrinter.PrintableNode getRight() {
        return right;
    }

    public Region findRegion(Point p) {
        Region R = new Region();

        if (p.equals(point)){
            return R;
        }

        else if (p.getX() < point.getX()) {
            R = left.findRegion(p);
            R.push(DIR.LEFT);
            return R;
        }
        else {
            R = right.findRegion(p);
            R.push(DIR.RIGHT);
            return R;
        }
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

    public String getText() {
        return point.toString();
    }

    public TreePrinter.PrintableNode getLeft() {
        return under;
    }

    public TreePrinter.PrintableNode getRight() {
        return above;
    }

    public Region findRegion(Point p) {
        Region R = new Region();

        if (p.equals(point)){
            return R;
        }
        else if (p.getY() < point.getY()) {
            R = under.findRegion(p);
            R.push(DIR.UNDER);
            return R;
        }
        else {
            R = above.findRegion(p);
            R.push(DIR.ABOVE);
            return R;
        }
    }

}

// -----
