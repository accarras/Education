import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void testPoint() {
        Point p1, p2, p3, p4;
        p1 = new Point(0, 0);
        p2 = new Point(3, 4);
        p3 = new Point(1, 2);
        p4 = new Point(1, 2);
        assertEquals(3, p2.getX());
        assertEquals(4, p2.getY());
        assertEquals(25, p1.distanceSquaredTo(p2));
        assertEquals(25, p2.distanceSquaredTo(p1));
        assertEquals(0, p1.distanceSquaredTo(p1));
        assertEquals(0, p2.distanceSquaredTo(p2));
        assertEquals(p3, p4);
        assertEquals(p3.hashCode(), p4.hashCode());
    }

    @Test
    public void testEdge() {
        Point p1, p2, p3, p4;
        p1 = new Point(0, 0);
        p2 = new Point(3, 4);
        p3 = new Point(1, 2);
        p4 = new Point(1, 2);

        Edge e1, e2, e3, e4;
        e1 = new Edge(p1, p2);
        e2 = new Edge(p3, p3);
        e3 = new Edge(p2, p4);
        e4 = new Edge(p2, p3);

        assertEquals(e1.getFrom(), p1);
        assertEquals(e1.getTo(), p2);
        assertEquals(e2.getFrom(), p3);
        assertEquals(e2.getFrom(), p4);
        assertEquals(e2.getFrom(), e2.getTo());
        assertEquals(e3, e4);
        assertEquals(e3.hashCode(), e4.hashCode());

        assertEquals(new Edge(p4, p3), new Edge(p3, p4).flip());
    }

    @Test
    public void testRect() {
        Rect r1, r2, r3, r4, r5, r6, r7, r8;
        r1 = new Rect(0, 0, 4, 5);
        r2 = new Rect(1, 2, 3, 3);
        r3 = new Rect(2, 1, 5, 4);
        r4 = new Rect(6, 2, 7, 3);

        r5 = new Rect(0, 0, 2, 5);
        r6 = new Rect(2, 0, 4, 5);
        r7 = new Rect(0, 0, 4, 2);
        r8 = new Rect(0, 2, 4, 5);

        assertEquals(r3.getXmin(), 2);
        assertEquals(r3.getYmin(), 1);
        assertEquals(r3.getXmax(), 5);
        assertEquals(r3.getYmax(), 4);

        Point p = new Point(2, 2);
        assertEquals(r5, r1.leftOf(p));
        assertEquals(r6, r1.rightOf(p));
        assertEquals(r7, r1.underOf(p));
        assertEquals(r8, r1.aboveOf(p));

        assertTrue(r1.contains(p));
        assertTrue(r2.contains(p));
        assertTrue(r3.contains(p));
        assertFalse(r4.contains(p));

        assertTrue(r1.intersect(r1));
        assertTrue(r1.intersect(r2));
        assertTrue(r1.intersect(r3));
        assertFalse(r1.intersect(r4));

        assertTrue(r2.intersect(r1));
        assertTrue(r2.intersect(r2));
        assertTrue(r2.intersect(r3));
        assertFalse(r2.intersect(r4));

        assertTrue(r3.intersect(r1));
        assertTrue(r3.intersect(r2));
        assertTrue(r3.intersect(r3));
        assertFalse(r3.intersect(r4));

        assertFalse(r4.intersect(r1));
        assertFalse(r4.intersect(r2));
        assertFalse(r4.intersect(r3));
        assertTrue(r4.intersect(r4));

        assertEquals(0, r3.distanceSquaredTo(new Point(3, 1)));
        assertEquals(4, r3.distanceSquaredTo(new Point(0, 2)));
        assertEquals(1, r3.distanceSquaredTo(new Point(3, 0)));
        //r3 = new Rect(xmin 2, y min 1, xmax 5, ymax 4);
        assertEquals(4, r3.distanceSquaredTo(new Point(7, 3)));
        assertEquals(36, r3.distanceSquaredTo(new Point(2, 10)));
        assertEquals(5, r3.distanceSquaredTo(new Point(0, 0)));
        assertEquals(5, r3.distanceSquaredTo(new Point(0, 5)));
        assertEquals(5, r3.distanceSquaredTo(new Point(7, 0)));
        assertEquals(2, r3.distanceSquaredTo(new Point(6, 5)));
    }

    @Test
    public void testRect2(){

        Rect r1 = new Rect(1,1  ,4,4);

        //Contains
        assert(r1.contains(new Point(2,3)));
        assert(r1.contains(new Point(1,1)));
        assertFalse(r1.contains(new Point(0,0)));
        assertFalse(r1.contains(new Point(4,5)));


        // ...Of methods
        Point x1 = new Point(2, 3);

        assertEquals(new Rect(1,1,2,4), r1.leftOf(x1));
        assertEquals(new Rect(2,1,4,4), r1.rightOf(x1));
        assertEquals(new Rect(1,3,4,4), r1.aboveOf(x1));
        assertEquals(new Rect(1,1,4,3),r1.underOf(x1));

        assertEquals(r1, r1.leftOf(new Point (4,1)));
        assertEquals(r1, r1.rightOf(new Point (1,3)));
        assertEquals(r1, r1.underOf(new Point (2,4)));
        assertEquals(r1, r1.aboveOf(new Point (0,1)));

        //Intersect
        Rect r2 = new Rect(2,0,3,5); //y
        Rect r3 = new Rect(2,2,3,3); //y
        Rect r4 = new Rect(3,3,6,6); //y
        Rect r5 = new Rect(-3,1,2,3); //y
        Rect r6 = new Rect(-2,0,0,6); //n
        Rect r7 = new Rect(3,5,6,6); //n

        assert(r1.intersect(r2));
        assert(r1.intersect(r3));
        assert(r1.intersect(r4));
        assert(r1.intersect(r5));
        assert(!r1.intersect(r6));
        assert(!r1.intersect(r7));

        //DistanceSquaredTo
        assertEquals(2, r1.distanceSquaredTo(new Point(0,0)));
        assertEquals(1, r1.distanceSquaredTo(new Point(0,3)));
        assertEquals(10, r1.distanceSquaredTo(new Point(0,7)));
        assertEquals(4, r1.distanceSquaredTo(new Point(1,6)));
        assertEquals(20, r1.distanceSquaredTo(new Point(6,8)));
        assertEquals(1, r1.distanceSquaredTo(new Point(5,3)));
        assertEquals(2, r1.distanceSquaredTo(new Point(5,0)));
        assertEquals(4, r1.distanceSquaredTo(new Point(3,-1)));
        assertEquals(0, r1.distanceSquaredTo(new Point(3,3)));
    }

    @Test
    public void testRegion() {
        Region r1, r2;
        r1 = new Region();
        r2 = new Region();

        assertTrue(r1.isEmpty());
        assertTrue(r2.getDirs().isEmpty());

        r1.push(DIR.LEFT);
        assertFalse(r1.isEmpty());
        assertTrue(r1.getDirs().contains(DIR.LEFT));
        assertTrue(r2.getDirs().isEmpty());

        r1.push(DIR.UNDER);
        assertEquals(r1.getDirs().get(0), DIR.UNDER);
        assertEquals(r1.getDirs().get(1), DIR.LEFT);

        r1.push(DIR.RIGHT);
        r2.push(DIR.LEFT);
        r2.push(DIR.UNDER);
        r2.push(DIR.RIGHT);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    public void testRregion2() {

        Region reg = new Region();

        assert(reg.isEmpty());

        reg.push(DIR.LEFT);
        reg.push(DIR.ABOVE);
        reg.push(DIR.RIGHT);

        ArrayList<DIR> regions = reg.getDirs();

        assertEquals(regions.get(0),DIR.RIGHT);
        assertEquals(regions.get(1),DIR.ABOVE);
        assertEquals(regions.get(2),DIR.LEFT);

        assert(!reg.isEmpty());

    }

    @Test
    public void testItem() {
        Point p1, p2, p3, p4, p5;
        Item<Point> pp1, pp2, pp3, pp4, pp5;

        p1 = new Point(0, 0);
        p2 = new Point(1, 1);
        p3 = new Point(2, 2);
        p4 = new Point(3, 3);
        p5 = new Point(4, 4);

        pp1 = new Item<>(p1, p1.toString(), 0);
        pp2 = new Item<>(p2, p2.toString(), 0);
        pp3 = new Item<>(p3, p3.toString(), 0);
        pp4 = new Item<>(p4, p4.toString(), 0);
        pp5 = new Item<>(p5, p5.toString(), 0);

        assertEquals(pp1, new Item<>(new Point(0, 0), "(0,0)", 0));
        assertEquals(pp1.hashCode(),
                new Item<>(new Point(0, 0), "(0,0)", 0).hashCode());

        pp1.setPrevious(pp2);
        pp2.setPrevious(pp3);
        pp3.setPrevious(pp4);
        pp4.setPrevious(pp5);

        ArrayList<Edge> path = Item.pathToSource(pp1);
        assertEquals(4, path.size());

        Edge e1, e2, e3, e4;
        e1 = path.get(0);
        e2 = path.get(1);
        e3 = path.get(2);
        e4 = path.get(3);

        assertEquals(e1.getFrom(), p1);
        assertEquals(e1.getTo(), p2);

        assertEquals(e2.getFrom(), p2);
        assertEquals(e2.getTo(), p3);

        assertEquals(e3.getFrom(), p3);
        assertEquals(e3.getTo(), p4);

        assertEquals(e4.getFrom(), p4);
        assertEquals(e4.getTo(), p5);
    }

    @Test
    public void TestItem2() {

        Item<Point> a = new Item<>(new Point(0,1), "a", 0);
        Item<Point> b = new Item<>(new Point(1,1), "b", 0);
        Item<Point> c = new Item<>(new Point(2,1), "c", 0);
        Item<Point> d = new Item<>(new Point(3,1), "d", 0);

        a.setPrevious(null);
        b.setPrevious(a);
        c.setPrevious(b);
        d.setPrevious(c);

        // the path is like this a -> b -> c -> d : where a is the root node


        ArrayList<Edge> res1 = new ArrayList<>();

        assertEquals(res1, Item.pathToSource(a)); //Because a is the root node, its path to source is the empty list

        res1.add(new Edge(b.getData(),a.getData()));  //Adding the b -> a edge to the expected path
        assertEquals(res1,Item.pathToSource(b));

        res1.add(0,new Edge(c.getData(),b.getData()));  //Adding the c -> b edge to the expected path
        assertEquals(res1,Item.pathToSource(c));

        res1.add(0,new Edge(d.getData(),c.getData()));  //Adding the d -> c edge to the expected path
        assertEquals(res1,Item.pathToSource(d));

        Item<Point> e = new Item<>(new Point(3,1), "d", 0);

        //Now b also extends to node e, so e's path should be e -> b -> a , which is identical to c's path, because they are on the same level with the same parent
        e.setPrevious(b);

        res1 = new ArrayList<>();
        res1.add(new Edge(b.getData(),a.getData()));
        res1.add(0, new Edge(e.getData(),b.getData()));

        assertEquals(res1,Item.pathToSource(e));

    }

    @Test
    public void testKDTree() {
        ArrayList<Point> xPoints, yPoints;
        Point p1, p2, p3, p4, p5, p6, p7;
        p1 = new Point(1, 6);
        p2 = new Point(2, 2);
        p3 = new Point(3, 3);
        p4 = new Point(4, 1);
        p5 = new Point(5, 7);
        p6 = new Point(6, 4);
        p7 = new Point(7, 5);
        xPoints = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
        yPoints = new ArrayList<>(Arrays.asList(p4, p2, p3, p6, p7, p1, p5));
        XTree xtree = XTree.makeXTree(xPoints, yPoints, 0);
        /*

        TreePrinter.print(xtree);


                                              (4,1)
                            ┌───────────────────┴───────────────────┐
                          (3,3)                                   (7,5)
                  ┌─────────┴─────────┐                   ┌─────────┴─────────┐
                (2,2)               (1,6)               (6,4)               (5,7)
             ┌────┴────┐         ┌────┴────┐         ┌────┴────┐         ┌────┴────┐



        */

        Region r;

        r = new Region();
        r.push(DIR.UNDER);
        r.push(DIR.LEFT);
        assertEquals(r, xtree.findRegion(p2));

        r = new Region();
        r.push(DIR.ABOVE);
        r.push(DIR.LEFT);
        assertEquals(r, xtree.findRegion(p1));

        r = new Region();
        r.push(DIR.LEFT);
        assertEquals(r, xtree.findRegion(p3));

        r = new Region();
        assertEquals(r, xtree.findRegion(p4));

        r = new Region();
        r.push(DIR.RIGHT);
        assertEquals(r, xtree.findRegion(p7));

        r = new Region();
        r.push(DIR.UNDER);
        r.push(DIR.RIGHT);
        assertEquals(r, xtree.findRegion(p6));

        r = new Region();
        r.push(DIR.ABOVE);
        r.push(DIR.RIGHT);
        assertEquals(r, xtree.findRegion(p5));
    }

    @Test
    public void TestKDTree2() {
        ArrayList<Point> xPoints, yPoints;
        Point p1, p2, p3, p4, p5, p6, p7;
        p1 = new Point(1, 0);
        p2 = new Point(2, 2);
        p3 = new Point(3, 4);
        p4 = new Point(4, 1);
        p5 = new Point(6, 5);
        p6 = new Point(8, 3);
        p7 = new Point(5, 6);
        xPoints = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p7, p5, p6));
        yPoints = new ArrayList<>(Arrays.asList(p1, p4, p2, p6, p3, p5, p7));


        //Test on an unbounded tree

        XTree xtree = XTree.makeXTree(xPoints, yPoints, 0);

        /*

        TreePrinter.print(xtree);


                                              (4,1)
                            ┌───────────────────┴───────────────────┐
                          (2,2)                                   (6,5)
                  ┌─────────┴─────────┐                   ┌─────────┴─────────┐
                (1,0)               (3,4)               (8,3)               (5,6)
             ┌────┴────┐         ┌────┴────┐         ┌────┴────┐         ┌────┴────┐



        */

        Region r;

        r = new Region();
        r.push(DIR.LEFT);
        assertEquals(r, xtree.findRegion(p2));

        r = new Region();
        r.push(DIR.UNDER);
        r.push(DIR.LEFT);
        assertEquals(r, xtree.findRegion(p1));

        r = new Region();
        r.push(DIR.ABOVE);
        r.push(DIR.LEFT);
        assertEquals(r, xtree.findRegion(p3));

        r = new Region();
        assertEquals(r, xtree.findRegion(p4));

        r = new Region();
        r.push(DIR.ABOVE);
        r.push(DIR.RIGHT);
        assertEquals(r, xtree.findRegion(p7));

        r = new Region();
        r.push(DIR.UNDER);
        r.push(DIR.RIGHT);
        assertEquals(r, xtree.findRegion(p6));

        r = new Region();
        r.push(DIR.RIGHT);
        assertEquals(r, xtree.findRegion(p5));


        //Bound is set so that the tree is only of size 3
        XTree test2 = XTree.makeXTree(xPoints, yPoints, 2);

        assertEquals("(4,1)",test2.getText());
        assertEquals("(2,2)", test2.getLeft().getText());
        assertEquals("(6,5)", test2.getRight().getText());

        r = new Region();
        assertEquals(r, test2.findRegion(p4));

        r = new Region();
        r.push(DIR.LEFT );
        assertEquals(r, test2.findRegion(p2));

        r = new Region();
        r.push(DIR.RIGHT);
        assertEquals(r, test2.findRegion(p5));

        //Bound is set so that the tree is only of size 3 -- YTree
        YTree test3 = YTree.makeYTree(yPoints, xPoints, 2);

        assertEquals("(8,3)",test3.getText());
        assertEquals("(2,2)", test3.getLeft().getText());
        assertEquals("(5,6)", test3.getRight().getText());

        r = new Region();
        assertEquals(r, test3.findRegion(p6));

        r = new Region();
        r.push(DIR.UNDER);
        assertEquals(r, test3.findRegion(p2));

        r = new Region();
        r.push(DIR.ABOVE);
        assertEquals(r, test3.findRegion(p7));
    }

    @Test
    public void testSpatialGraph() {
        Rect grid = new Rect(0, 0, 10, 8);

        Point p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12;
        p1 = new Point(2, 1);
        p2 = new Point(2, 2);
        p3 = new Point(2, 3);
        p4 = new Point(2, 5);
        p5 = new Point(3, 4);
        p6 = new Point(4, 3);
        p7 = new Point(5, 2);
        p8 = new Point(5, 1);
        p9 = new Point(7, 2);
        p10 = new Point(7, 1);
        p11 = new Point(8, 2);
        p12 = new Point(8, 1);

        ArrayList<Point> points =
                new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));

        Item<Point> pp1, pp2, pp3, pp4, pp5, pp6, pp7, pp8, pp9, pp10, pp11, pp12;
        pp1 = new Item<>(p1, p1.toString(), 0);
        pp2 = new Item<>(p2, p2.toString(), 0);
        pp3 = new Item<>(p3, p3.toString(), 0);
        pp4 = new Item<>(p4, p4.toString(), 0);
        pp5 = new Item<>(p5, p5.toString(), 0);
        pp6 = new Item<>(p6, p6.toString(), 0);
        pp7 = new Item<>(p7, p7.toString(), 0);
        pp8 = new Item<>(p8, p8.toString(), 0);
        pp9 = new Item<>(p9, p9.toString(), 0);
        pp10 = new Item<>(p10, p10.toString(), 0);
        pp11 = new Item<>(p11, p11.toString(), 0);
        pp12 = new Item<>(p12, p12.toString(), 0);

        ArrayList<Item<Point>> nodes =
                new ArrayList<>(Arrays.asList(pp1, pp2, pp3, pp4, pp5, pp6, pp7, pp8, pp9, pp10, pp11, pp12));

        Hashtable<Item<Point>, ArrayList<Item<Point>>> neighbors = new Hashtable<>();
        neighbors.put(pp1, new ArrayList<>());
        neighbors.put(pp2, new ArrayList<>(Arrays.asList(pp1)));
        neighbors.put(pp3, new ArrayList<>(Arrays.asList(pp2)));
        neighbors.put(pp4, new ArrayList<>());
        neighbors.put(pp5, new ArrayList<>(Arrays.asList(pp4)));
        neighbors.put(pp6, new ArrayList<>(Arrays.asList(pp3, pp5)));
        neighbors.put(pp7, new ArrayList<>(Arrays.asList(pp6, pp8, pp9, pp10)));
        neighbors.put(pp8, new ArrayList<>(Arrays.asList(pp12)));
        neighbors.put(pp9, new ArrayList<>(Arrays.asList(pp11)));
        neighbors.put(pp10, new ArrayList<>(Arrays.asList(pp12)));
        neighbors.put(pp11, new ArrayList<>(Arrays.asList(pp12)));
        neighbors.put(pp12, new ArrayList<>());

        Edge e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13;
        e1 = new Edge(p2, p1);
        e2 = new Edge(p3, p2);
        e3 = new Edge(p5, p4);
        e4 = new Edge(p6, p3);
        e5 = new Edge(p6, p5);
        e6 = new Edge(p7, p6);
        e7 = new Edge(p7, p8);
        e8 = new Edge(p7, p9);
        e9 = new Edge(p7, p10);
        e10 = new Edge(p8, p12);
        e11 = new Edge(p9, p11);
        e12 = new Edge(p10, p12);
        e13 = new Edge(p11, p12);

        Hashtable<Edge, Integer> weights = new Hashtable<>();
        weights.put(e1, 10);
        weights.put(e2, 10);
        weights.put(e3, 10);
        weights.put(e4, 10);
        weights.put(e5, 10);
        weights.put(e6, 10);
        weights.put(e7, 10);
        weights.put(e8, 10);
        weights.put(e9, 10);
        weights.put(e10, 2);
        weights.put(e11, 1);
        weights.put(e12, 1);
        weights.put(e13, 1);

        SpatialGraph g = new SpatialGraph(grid, points, nodes, neighbors, weights);

        // all shortest paths from p7
        g.allShortestPaths(pp7);

        assertTrue(pp1.isVisited());
        assertTrue(pp2.isVisited());
        assertTrue(pp3.isVisited());
        assertTrue(pp4.isVisited());
        assertTrue(pp5.isVisited());
        assertTrue(pp6.isVisited());
        assertTrue(pp7.isVisited());
        assertTrue(pp8.isVisited());
        assertTrue(pp9.isVisited());
        assertTrue(pp10.isVisited());
        assertTrue(pp11.isVisited());
        assertTrue(pp12.isVisited());

        assertEquals(pp2, pp1.getPrevious());
        assertEquals(pp3, pp2.getPrevious());
        assertEquals(pp6, pp3.getPrevious());
        assertEquals(pp5, pp4.getPrevious());
        assertEquals(pp6, pp5.getPrevious());
        assertEquals(pp7, pp6.getPrevious());
        assertEquals(pp7, pp8.getPrevious());
        assertEquals(pp7, pp9.getPrevious());
        assertEquals(pp7, pp10.getPrevious());
        assertEquals(pp9, pp11.getPrevious());
        assertEquals(pp10, pp12.getPrevious());

        // shortest path from p7 to p4
        g.shortestPath(pp7, pp4);

        assertFalse(pp1.isVisited());
        assertTrue(pp2.isVisited());
        assertTrue(pp3.isVisited());
        assertTrue(pp5.isVisited());
        assertTrue(pp6.isVisited());
        assertTrue(pp7.isVisited());
        assertTrue(pp8.isVisited());
        assertTrue(pp9.isVisited());
        assertTrue(pp10.isVisited());
        assertTrue(pp11.isVisited());
        assertTrue(pp12.isVisited());

        assertEquals(pp2, pp1.getPrevious());
        assertEquals(pp3, pp2.getPrevious());
        assertEquals(pp6, pp3.getPrevious());
        assertEquals(pp5, pp4.getPrevious());
        assertEquals(pp6, pp5.getPrevious());
        assertEquals(pp7, pp6.getPrevious());
        assertEquals(pp7, pp8.getPrevious());
        assertEquals(pp7, pp9.getPrevious());
        assertEquals(pp7, pp10.getPrevious());
        assertEquals(pp9, pp11.getPrevious());
        assertEquals(pp10, pp12.getPrevious());

        g.buildKDTree(1);
        System.out.printf("p1 Region %s%n", g.getKdtree().findRegion(p1));
        System.out.printf("p2 Region %s%n", g.getKdtree().findRegion(p2));
        System.out.printf("p3 Region %s%n", g.getKdtree().findRegion(p3));
        System.out.printf("p4 Region %s%n", g.getKdtree().findRegion(p4));
        System.out.printf("p5 Region %s%n", g.getKdtree().findRegion(p5));
        System.out.printf("p6 Region %s%n", g.getKdtree().findRegion(p6));
        System.out.printf("p7 Region %s%n", g.getKdtree().findRegion(p7));
        System.out.printf("p8 Region %s%n", g.getKdtree().findRegion(p8));
        System.out.printf("p9 Region %s%n", g.getKdtree().findRegion(p9));
        System.out.printf("p10 Region %s%n", g.getKdtree().findRegion(p10));
        System.out.printf("p11 Region %s%n", g.getKdtree().findRegion(p11));
        System.out.printf("p12 Region %s%n", g.getKdtree().findRegion(p12));

        TreePrinter.print(g.getKdtree());
        /*

                                      (5,2)
                    ┌───────────────────┴───────────────────┐
                  (4,3)                                   (8,1)
          ┌─────────┴─────────┐                   ┌─────────┴─────────┐
        (2,2)               (3,4)               (7,1)               (8,2)
     ┌────┴────┐         ┌────┴────┐         ┌────┴────┐         ┌────┴────┐




        */

        g.preprocess(0);

        System.out.printf("Regional edges:%n%s%n:", g.getRegionalEdges());

        Region r = g.getKdtree().findRegion(p4);
        Hashtable<Edge, HashSet<Region>> regionalEdges = g.getRegionalEdges();

        assertTrue(regionalEdges.get(e6).contains(r));
        assertFalse(regionalEdges.get(e7).contains(r));
        assertFalse(regionalEdges.get(e8).contains(r));
        assertFalse(regionalEdges.get(e9).contains(r));

        g.regionalShortestPath(pp7, pp4);
        assertFalse(pp1.isVisited());
        assertFalse(pp2.isVisited());
        assertFalse(pp3.isVisited());
        assertTrue(pp5.isVisited());
        assertTrue(pp6.isVisited());
        assertTrue(pp7.isVisited());
        assertFalse(pp8.isVisited());
        assertFalse(pp9.isVisited());
        assertFalse(pp10.isVisited());
        assertFalse(pp11.isVisited());
        assertFalse(pp12.isVisited());
    }

    @Test
    public  void testSpacialGraph2() {

        Rect grid = new Rect(0, 0, 10, 10);

        Point p1, p2, p3, p4, p5, p6;
        p1 = new Point(2, 1);
        p2 = new Point(2, 2);
        p3 = new Point(2, 3);
        p4 = new Point(2, 5);
        p5 = new Point(3, 4);
        p6 = new Point(4, 3);


        ArrayList<Point> points =
                new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6));

        Item<Point> pp1, pp2, pp3, pp4, pp5, pp6;
        pp1 = new Item<>(p1, "a", 0);
        pp2 = new Item<>(p2, "b", 0);
        pp3 = new Item<>(p3, "c", 0);
        pp4 = new Item<>(p4, "d", 0);
        pp5 = new Item<>(p5, "e", 0);
        pp6 = new Item<>(p6, "f", 0);

        ArrayList<Item<Point>> nodes =
                new ArrayList<>(Arrays.asList(pp1, pp2, pp3, pp4, pp5, pp6));

        Hashtable<Item<Point>, ArrayList<Item<Point>>> neighbors = new Hashtable<>();
        neighbors.put(pp1, new ArrayList<>(Arrays.asList(pp2, pp6)));
        neighbors.put(pp2, new ArrayList<>(Arrays.asList(pp3)));
        neighbors.put(pp3, new ArrayList<>(Arrays.asList(pp2, pp4, pp5)));
        neighbors.put(pp4, new ArrayList<>(Arrays.asList(pp5, pp6)));
        neighbors.put(pp5, new ArrayList<>());
        neighbors.put(pp6, new ArrayList<>(Arrays.asList(pp3)));


        Edge e1, e2, e3, e4, e5, e6, e7, e8, e9;
        e1 = new Edge(p1, p2);
        e2 = new Edge(p1, p6);
        e3 = new Edge(p2, p3);
        e4 = new Edge(p6, p3);
        e5 = new Edge(p3, p2);
        e6 = new Edge(p3, p4);
        e7 = new Edge(p3, p5);
        e8 = new Edge(p4, p5);
        e9 = new Edge(p4, p6);

        Hashtable<Edge, Integer> weights = new Hashtable<>();
        weights.put(e1, 4);
        weights.put(e2, 2);
        weights.put(e3, 1);
        weights.put(e4, 1);
        weights.put(e5, 2);
        weights.put(e6, 4);
        weights.put(e7, 3);
        weights.put(e8, 6);
        weights.put(e9, 2);

        SpatialGraph g = new SpatialGraph(grid, points, nodes, neighbors, weights);

        //Testing allShortestPaths():

        // all shortest paths from p6
        g.allShortestPaths(pp6);

        //assertTrue(pp1.isVisited());
        assertTrue(pp2.isVisited());
        assertTrue(pp3.isVisited());
        assertTrue(pp4.isVisited());
        assertTrue(pp5.isVisited());

        //Checking all distances from p6
        assertEquals(1,pp3.getValue());
        assertEquals(3,pp2.getValue());
        assertEquals(5,pp4.getValue());
        assertEquals(4,pp5.getValue());
        assertEquals(Integer.MAX_VALUE,pp1.getValue());


        // all shortest paths from p1
        g.allShortestPaths(pp1);

        //Checking all distances from p1
        assertEquals(3,pp3.getValue());
        assertEquals(4,pp2.getValue());
        assertEquals(7,pp4.getValue());
        assertEquals(6,pp5.getValue());
        assertEquals(2,pp6.getValue());


        //Testing shortestPath():

        g.shortestPath(pp2,pp6);

        assertFalse(pp1.isVisited());
        assertFalse(pp6.isVisited());
        assertTrue(pp3.isVisited());
        assertTrue(pp4.isVisited());
    }

    @Test
    public void testSpacialGraph3(){
        // Another Graph
        Point ppp1, ppp2, ppp3, ppp4, ppp5, ppp6, ppp7, ppp8, ppp9, ppp10, ppp11;
        ppp1 = new Point(6, 3);
        ppp2 = new Point(4, 1);
        ppp3 = new Point(2, 4);
        ppp4 = new Point(3, 7);
        ppp5 = new Point(4, 6);
        ppp6 = new Point(3, 5);
        ppp7 = new Point(7, 5);
        ppp8 = new Point(9, 4);
        ppp9 = new Point(8, 2);
        ppp10 = new Point(10, 3);
        ppp11 = new Point(10, 1);

        ArrayList<Point> myPoints =
                new ArrayList<>(Arrays.asList(ppp1, ppp2, ppp3, ppp4, ppp5, ppp6, ppp7, ppp8, ppp9, ppp10, ppp11));

        Item<Point> ppi1, ppi2, ppi3, ppi4, ppi5, ppi6, ppi7, ppi8, ppi9, ppi10, ppi11;
        ppi1 = new Item<>(ppp1, ppp1.toString(), 0);
        ppi2 = new Item<>(ppp2, ppp2.toString(), 0);
        ppi3 = new Item<>(ppp3, ppp3.toString(), 0);
        ppi4 = new Item<>(ppp4, ppp4.toString(), 0);
        ppi5 = new Item<>(ppp5, ppp5.toString(), 0);
        ppi6 = new Item<>(ppp6, ppp6.toString(), 0);
        ppi7 = new Item<>(ppp7, ppp7.toString(), 0);
        ppi8 = new Item<>(ppp8, ppp8.toString(), 0);
        ppi9 = new Item<>(ppp9, ppp9.toString(), 0);
        ppi10 = new Item<>(ppp10, ppp10.toString(), 0);
        ppi11 = new Item<>(ppp11, ppp11.toString(), 0);

        ArrayList<Item<Point>> myNodes =
                new ArrayList<>(Arrays.asList(ppi1, ppi2, ppi3, ppi4, ppi5, ppi6, ppi7, ppi8, ppi9, ppi10, ppi11));

        Hashtable<Item<Point>, ArrayList<Item<Point>>> myNeighbors = new Hashtable<>();
        myNeighbors.put(ppi1, new ArrayList<>(Arrays.asList(ppi5, ppi6, ppi2, ppi7, ppi8)));
        myNeighbors.put(ppi2, new ArrayList<>(Arrays.asList(ppi3)));
        myNeighbors.put(ppi3, new ArrayList<>(Arrays.asList(ppi4)));
        myNeighbors.put(ppi4, new ArrayList<>());
        myNeighbors.put(ppi5, new ArrayList<>(Arrays.asList(ppi4)));
        myNeighbors.put(ppi6, new ArrayList<>(Arrays.asList(ppi5)));
        myNeighbors.put(ppi7, new ArrayList<>());
        myNeighbors.put(ppi8, new ArrayList<>(Arrays.asList(ppi7, ppi9, ppi10)));
        myNeighbors.put(ppi9, new ArrayList<>(Arrays.asList(ppi10, ppi11)));
        myNeighbors.put(ppi10, new ArrayList<>(Arrays.asList(ppi11)));
        myNeighbors.put(ppi11, new ArrayList<>());


        Edge ee1, ee2, ee3, ee4, ee5, ee6, ee7, ee8, ee9, ee10, ee11, ee12, ee13, ee14, ee15;
        ee1 = new Edge(ppp1, ppp2);
        ee2 = new Edge(ppp1, ppp5);
        ee3 = new Edge(ppp1, ppp6);
        ee4 = new Edge(ppp1, ppp7);
        ee5 = new Edge(ppp1, ppp8);
        ee6 = new Edge(ppp2, ppp3);
        ee7 = new Edge(ppp3, ppp4);
        ee8 = new Edge(ppp5, ppp4);
        ee9 = new Edge(ppp6, ppp5);
        ee10 = new Edge(ppp8, ppp7);
        ee11 = new Edge(ppp8, ppp9);
        ee12 = new Edge(ppp8, ppp10);
        ee13 = new Edge(ppp9, ppp10);
        ee14 = new Edge(ppp9, ppp11);
        ee15 = new Edge(ppp10, ppp11);

        Hashtable<Edge, Integer> myWeights = new Hashtable<>();
        myWeights.put(ee1, 2);
        myWeights.put(ee2, 10);
        myWeights.put(ee3, 5);
        myWeights.put(ee4, 6);
        myWeights.put(ee5, 7);
        myWeights.put(ee6, 4);
        myWeights.put(ee7, 3);
        myWeights.put(ee8, 1);
        myWeights.put(ee9, 4);
        myWeights.put(ee10, 1);
        myWeights.put(ee11, 2);
        myWeights.put(ee12, 2);
        myWeights.put(ee13, 1);
        myWeights.put(ee14, 1);
        myWeights.put(ee15, 1);


        Rect myGrid = new Rect(0, 0, 12, 8);
        SpatialGraph myG = new SpatialGraph(myGrid, myPoints, myNodes, myNeighbors, myWeights);

        myG.preprocess(0);
        XTree myKDTree = myG.getKdtree();
        Hashtable<Edge, HashSet<Region>> myRegionalEdges = myG.getRegionalEdges();

        TreePrinter.print(myKDTree);
        System.out.println(myRegionalEdges);

        Region myR = myKDTree.findRegion(ppp11);

        assertTrue(myRegionalEdges.get(ee14).contains(myR));
        assertTrue(myRegionalEdges.get(ee11).contains(myR));
        assertTrue(myRegionalEdges.get(ee2).isEmpty());
        assertTrue(myRegionalEdges.get(ee5).contains(myR));
        assertFalse(myRegionalEdges.get(ee12).contains(myR));

        myR = myKDTree.findRegion(ppp4);

        assertTrue(myRegionalEdges.get(ee9).contains(myR));
        assertTrue(myRegionalEdges.get(ee8).contains(myR));
        assertTrue(myRegionalEdges.get(ee7).contains(myR));
        assertTrue(myRegionalEdges.get(ee6).contains(myR));
        assertTrue(myRegionalEdges.get(ee1).contains(myR));
        assertFalse(myRegionalEdges.get(ee2).contains(myR));
        assertFalse(myRegionalEdges.get(ee3).contains(myR));

        myG.regionalShortestPath(ppi1, ppi4);

        assertTrue(ppi1.isVisited());
        assertTrue(ppi2.isVisited());
        assertTrue(ppi3.isVisited());
        assertFalse(ppi4.isVisited());
        assertFalse(ppi6.isVisited());
        assertFalse(ppi5.isVisited());
        assertFalse(ppi7.isVisited());
        assertFalse(ppi8.isVisited());

        myG.regionalShortestPath(ppi1, ppi11);

        assertTrue(ppi1.isVisited());
        assertTrue(ppi8.isVisited());
        assertTrue(ppi9.isVisited());
        assertFalse(ppi11.isVisited());
        assertFalse(ppi2.isVisited());
        assertFalse(ppi3.isVisited());
        assertFalse(ppi10.isVisited());
        assertFalse(ppi7.isVisited());
        assertFalse(ppi6.isVisited());

        myG.regionalShortestPath(ppi6, ppi4);
        assertTrue(ppi6.isVisited());
        assertTrue(ppi5.isVisited());
        assertFalse(ppi4.isVisited());
        assertFalse(ppi1.isVisited());
        assertFalse(ppi3.isVisited());
        assertFalse(ppi8.isVisited());
        assertFalse(ppi2.isVisited());
    }

    @Test
    public void testSpacialGraph4(){

        // Another Graph
        Point ppp1, ppp2, ppp3, ppp4, ppp5, ppp6, ppp7, ppp8, ppp9, ppp10, ppp11;
        ppp1 = new Point(6, 3);
        ppp2 = new Point(4, 1);
        ppp3 = new Point(2, 4);
        ppp4 = new Point(3, 7);
        ppp5 = new Point(4, 6);
        ppp6 = new Point(3, 5);
        ppp7 = new Point(7, 5);
        ppp8 = new Point(9, 4);
        ppp9 = new Point(8, 2);
        ppp10 = new Point(10, 3);
        ppp11 = new Point(10, 1);

        ArrayList<Point> myPoints =
                new ArrayList<>(Arrays.asList(ppp1, ppp2, ppp3, ppp4, ppp5, ppp6, ppp7, ppp8, ppp9, ppp10, ppp11));

        Item<Point> ppi1, ppi2, ppi3, ppi4, ppi5, ppi6, ppi7, ppi8, ppi9, ppi10, ppi11;
        ppi1 = new Item<>(ppp1, ppp1.toString(), 0);
        ppi2 = new Item<>(ppp2, ppp2.toString(), 0);
        ppi3 = new Item<>(ppp3, ppp3.toString(), 0);
        ppi4 = new Item<>(ppp4, ppp4.toString(), 0);
        ppi5 = new Item<>(ppp5, ppp5.toString(), 0);
        ppi6 = new Item<>(ppp6, ppp6.toString(), 0);
        ppi7 = new Item<>(ppp7, ppp7.toString(), 0);
        ppi8 = new Item<>(ppp8, ppp8.toString(), 0);
        ppi9 = new Item<>(ppp9, ppp9.toString(), 0);
        ppi10 = new Item<>(ppp10, ppp10.toString(), 0);
        ppi11 = new Item<>(ppp11, ppp11.toString(), 0);

        ArrayList<Item<Point>> myNodes =
                new ArrayList<>(Arrays.asList(ppi1, ppi2, ppi3, ppi4, ppi5, ppi6, ppi7, ppi8, ppi9, ppi10, ppi11));

        Hashtable<Item<Point>, ArrayList<Item<Point>>> myNeighbors = new Hashtable<>();
        myNeighbors.put(ppi1, new ArrayList<>(Arrays.asList(ppi5, ppi6, ppi2, ppi7, ppi8)));
        myNeighbors.put(ppi2, new ArrayList<>(Arrays.asList(ppi3)));
        myNeighbors.put(ppi3, new ArrayList<>(Arrays.asList(ppi4)));
        myNeighbors.put(ppi4, new ArrayList<>());
        myNeighbors.put(ppi5, new ArrayList<>(Arrays.asList(ppi4)));
        myNeighbors.put(ppi6, new ArrayList<>(Arrays.asList(ppi5)));
        myNeighbors.put(ppi7, new ArrayList<>());
        myNeighbors.put(ppi8, new ArrayList<>(Arrays.asList(ppi7, ppi9, ppi10)));
        myNeighbors.put(ppi9, new ArrayList<>(Arrays.asList(ppi10, ppi11)));
        myNeighbors.put(ppi10, new ArrayList<>(Arrays.asList(ppi11)));
        myNeighbors.put(ppi11, new ArrayList<>());


        Edge ee1, ee2, ee3, ee4, ee5, ee6, ee7, ee8, ee9, ee10, ee11, ee12, ee13, ee14, ee15;
        ee1 = new Edge(ppp1, ppp2);
        ee2 = new Edge(ppp1, ppp5);
        ee3 = new Edge(ppp1, ppp6);
        ee4 = new Edge(ppp1, ppp7);
        ee5 = new Edge(ppp1, ppp8);
        ee6 = new Edge(ppp2, ppp3);
        ee7 = new Edge(ppp3, ppp4);
        ee8 = new Edge(ppp5, ppp4);
        ee9 = new Edge(ppp6, ppp5);
        ee10 = new Edge(ppp8, ppp7);
        ee11 = new Edge(ppp8, ppp9);
        ee12 = new Edge(ppp8, ppp10);
        ee13 = new Edge(ppp9, ppp10);
        ee14 = new Edge(ppp9, ppp11);
        ee15 = new Edge(ppp10, ppp11);

        Hashtable<Edge, Integer> myWeights = new Hashtable<>();
        myWeights.put(ee1, 2);
        myWeights.put(ee2, 10);
        myWeights.put(ee3, 5);
        myWeights.put(ee4, 6);
        myWeights.put(ee5, 7);
        myWeights.put(ee6, 4);
        myWeights.put(ee7, 3);
        myWeights.put(ee8, 1);
        myWeights.put(ee9, 4);
        myWeights.put(ee10, 1);
        myWeights.put(ee11, 2);
        myWeights.put(ee12, 2);
        myWeights.put(ee13, 1);
        myWeights.put(ee14, 1);
        myWeights.put(ee15, 1);


        Rect myGrid = new Rect(0, 0, 12, 8);
        SpatialGraph myG = new SpatialGraph(myGrid, myPoints, myNodes, myNeighbors, myWeights);

        myG.preprocess(0);
        XTree myKDTree = myG.getKdtree();
        Hashtable<Edge, HashSet<Region>> myRegionalEdges = myG.getRegionalEdges();

        TreePrinter.print(myKDTree);
        System.out.println(myRegionalEdges);

        Region myR = myKDTree.findRegion(ppp11);

        assertTrue(myRegionalEdges.get(ee14).contains(myR));
        assertTrue(myRegionalEdges.get(ee11).contains(myR));
        assertTrue(myRegionalEdges.get(ee2).isEmpty());
        assertTrue(myRegionalEdges.get(ee5).contains(myR));
        assertFalse(myRegionalEdges.get(ee12).contains(myR));

        myR = myKDTree.findRegion(ppp4);

        assertTrue(myRegionalEdges.get(ee9).contains(myR));
        assertTrue(myRegionalEdges.get(ee8).contains(myR));
        assertTrue(myRegionalEdges.get(ee7).contains(myR));
        assertTrue(myRegionalEdges.get(ee6).contains(myR));
        assertTrue(myRegionalEdges.get(ee1).contains(myR));
        assertFalse(myRegionalEdges.get(ee2).contains(myR));
        assertFalse(myRegionalEdges.get(ee3).contains(myR));

        myG.regionalShortestPath(ppi1, ppi4);

        assertTrue(ppi1.isVisited());
        assertTrue(ppi2.isVisited());
        assertTrue(ppi3.isVisited());
        assertFalse(ppi4.isVisited());
        assertFalse(ppi6.isVisited());
        assertFalse(ppi5.isVisited());
        assertFalse(ppi7.isVisited());
        assertFalse(ppi8.isVisited());

        myG.regionalShortestPath(ppi1, ppi11);

        assertTrue(ppi1.isVisited());
        assertTrue(ppi8.isVisited());
        assertTrue(ppi9.isVisited());
        assertFalse(ppi11.isVisited());
        assertFalse(ppi2.isVisited());
        assertFalse(ppi3.isVisited());
        assertFalse(ppi10.isVisited());
        assertFalse(ppi7.isVisited());
        assertFalse(ppi6.isVisited());

        myG.regionalShortestPath(ppi6, ppi4);
        assertTrue(ppi6.isVisited());
        assertTrue(ppi5.isVisited());
        assertFalse(ppi4.isVisited());
        assertFalse(ppi1.isVisited());
        assertFalse(ppi3.isVisited());
        assertFalse(ppi8.isVisited());
        assertFalse(ppi2.isVisited());

    }

    static long testBigSpatialGraphSlow(Random r, SpatialGraph g) {
        ArrayList<Item<Point>> nodes = g.getNodes();
        long start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            g.shortestPath(nodes.get(r.nextInt(1000)), nodes.get(r.nextInt(1000)));
        }
        long end = System.nanoTime();
        return (end - start) / 1000000;
    }

    static long testBigSpatialGraphFast(Random r, SpatialGraph g) {
        ArrayList<Item<Point>> nodes = g.getNodes();
        long start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            g.regionalShortestPath(nodes.get(r.nextInt(1000)), nodes.get(r.nextInt(1000)));
        }
        long end = System.nanoTime();
        return (end - start) / 1000000;
    }

    @Test
    public void testWeirdGraph(){
        // Weird graph
        Point wp1, wp2, wp3, wp4, wp5;

        wp1 = new Point(4, 3);
        wp2 = new Point(3, 1);
        wp3 = new Point(5, 1);
        wp4 = new Point(6, 2);
        wp5 = new Point(8, 2);


        ArrayList<Point> wPoints = new ArrayList<>(Arrays.asList(wp1, wp2, wp3, wp4, wp5));

        Item<Point> wpi1, wpi2, wpi3, wpi4, wpi5;

        wpi1 = new Item<>(wp1, wp1.toString(), 0);
        wpi2 = new Item<>(wp2, wp2.toString(), 0);
        wpi3 = new Item<>(wp3, wp3.toString(), 0);
        wpi4 = new Item<>(wp4, wp4.toString(), 0);
        wpi5 = new Item<>(wp5, wp5.toString(), 0);

        ArrayList<Item<Point>> wNodes = new ArrayList<>(Arrays.asList(wpi1, wpi2, wpi3, wpi4, wpi5));

        Hashtable<Item<Point>, ArrayList<Item<Point>>> wNeighbors = new Hashtable<>();

        wNeighbors.put(wpi1, new ArrayList<>(Arrays.asList(wpi2, wpi3)));
        wNeighbors.put(wpi2, new ArrayList<>(Arrays.asList(wpi1, wpi3)));
        wNeighbors.put(wpi3, new ArrayList<>(Arrays.asList(wpi1, wpi2)));
        wNeighbors.put(wpi4, new ArrayList<>(Arrays.asList(wpi5)));
        wNeighbors.put(wpi5, new ArrayList<>());

        Edge we1, we2, we3, we4, we5, we6, we7;
        we1 = new Edge(wp1, wp2);
        we2 = new Edge(wp1, wp3);
        we3 = new Edge(wp2, wp1);
        we4 = new Edge(wp2, wp3);
        we5 = new Edge(wp3, wp1);
        we6 = new Edge(wp3, wp2);
        we7 = new Edge(wp4, wp5);

        Hashtable<Edge, Integer> wWeights = new Hashtable<>();

        wWeights.put(we1, 1);
        wWeights.put(we2, 1);
        wWeights.put(we3, 1);
        wWeights.put(we4, 2);
        wWeights.put(we5, 1);
        wWeights.put(we6, 2);
        wWeights.put(we7, 3);

        Rect wGrid = new Rect(0, 0, 10, 5);
        SpatialGraph wG = new SpatialGraph(wGrid, wPoints, wNodes, wNeighbors, wWeights);

        wG.preprocess(0);
        TreePrinter.print(wG.getKdtree());

        ArrayList<Edge> path = wG.regionalShortestPath(wpi1, wpi5);

        // Test to make sure that when we try to visit wpi5 from wpi1, we are unable to get there. So the path returned should be empty.
        assertTrue(path.isEmpty());
        assertTrue(wpi1.isVisited());
        assertFalse(wpi4.isVisited());
        assertFalse(wpi5.isVisited());

    }

    @Test
    public void testTime() {
        Random r = new Random(1);
        SpatialGraph g = new SpatialGraph(r, 1000, 10, 20, 100);
        long start = System.nanoTime();
        g.preprocess(10);
        long end = System.nanoTime();
        System.out.printf("Preprocessing took %d%n", (end - start) / 1000000);

        long fast = testBigSpatialGraphFast(r, g);
        System.out.printf("Regional search took %d%n", fast);

        long slow = testBigSpatialGraphSlow(r, g);
        System.out.printf("Regular search took %d%n", slow);
    }

    @Test
    public void testBinaryHeap() {
        Point p1, p2, p3, p4, p5;
        Item<Point> pp1, pp2, pp3, pp4, pp5;

        p1 = new Point(0, 0);
        p2 = new Point(1, 1);
        p3 = new Point(2, 2);
        p4 = new Point(3, 3);
        p5 = new Point(4, 4);

        pp1 = new Item<>(p1, p1.toString(), 5);
        pp2 = new Item<>(p2, p2.toString(), 2);
        pp3 = new Item<>(p3, p3.toString(), 3);
        pp4 = new Item<>(p4, p4.toString(), 1);
        pp5 = new Item<>(p5, p5.toString(), 4);

        BinaryHeap<Point> h = new BinaryHeap<>();
        h.insert(pp1);
        h.insert(pp2);
        h.insert(pp3);
        h.insert(pp4);
        h.insert(pp5);

        ArrayList<Item<Point>> sorted;
        sorted = new ArrayList<>(Arrays.asList(pp4, pp2, pp3, pp1, pp5));
        assertEquals(sorted, h.getElems());
        assertEquals(pp4, h.extractMin());
        sorted = new ArrayList<>(Arrays.asList(pp2, pp5, pp3, pp1));
        assertEquals(sorted, h.getElems());
        h.updateKey(4, 1);
        sorted = new ArrayList<>(Arrays.asList(pp1, pp2, pp3, pp5));
        assertEquals(sorted, h.getElems());
    }

    @Test
    public void isEmptyTest(){

        BinaryHeap h = new BinaryHeap();
        DHeap Dh = new DHeap(3);
        assertTrue(h.isEmpty());
        assertTrue(Dh.isEmpty());

        h.insert(new Item(5, "obj1", 5));
        Dh.insert(new Item(5, "obj1", 5));

        assertTrue(!h.isEmpty());
        assertTrue(!Dh.isEmpty());


    }

    @Test
    public void getSizeTest(){
        BinaryHeap h = new BinaryHeap();
        DHeap Dh = new DHeap(3);

        assertEquals(h.getSize(), 0);
        h.insert(new Item<>(5, "new Obj", 1));
        assertEquals(h.getSize(), 1);

    }

    @Test
    public void findMinTest(){

        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        DHeap Dh = new DHeap(2);
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));

        BinaryHeap h2 = new BinaryHeap();
        h2.insert(new Item<>(5, "new Obj", 4));
        h2.insert(new Item<>(6, "new Obj2", 5));
        h2.insert(new Item<>(7, "new Obj3", 6));
        DHeap Dh2 = new DHeap(2);
        Dh2.insert(new Item<>(5, "new Obj", 1));
        Dh2.insert(new Item<>(6, "new Obj2", 2));
        Dh2.insert(new Item<>(7, "new Obj3", 3));

        BinaryHeap h3 = new BinaryHeap();
        h3.insert(new Item<>(5, "new Obj", 7));
        h3.insert(new Item<>(6, "new Obj2", 8));
        h3.insert(new Item<>(7, "new Obj3", 9));
        DHeap Dh3 = new DHeap(2);
        Dh3.insert(new Item<>(5, "new Obj", 1));
        Dh3.insert(new Item<>(6, "new Obj2", 2));
        Dh3.insert(new Item<>(7, "new Obj3", 3));

        assertEquals(h.findMin().getValue(), 1);
        assertEquals(h2.findMin().getValue(), 4);
        assertEquals(h3.findMin().getValue(), 7);
        assertEquals(Dh.findMin().getValue(), 1);
        assertEquals(Dh2.findMin().getValue(), 1);
        assertEquals(Dh3.findMin().getValue(), 1);

    }

    @Test
    public void getElemsTest(){

        int num = 4;
        ArrayList<Item<String>> items = new ArrayList<>();
        Random r = new Random(1);
        for (int i = 0; i < num; i++) {
            int k = r.nextInt(100);
            items.add(new Item<>("a" + k, "a" + k, k));
        }

        BinaryHeap h1 = new BinaryHeap(items);
        DHeap dh1 = new DHeap(3, items);
        h1.getElems();
        dh1.getElems();

    }

    @Test
    public void getParentIndexTest() throws NoParentE{
        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        h.insert(new Item<>(8, "new Obj3", 4));
        h.insert(new Item<>(9, "new Obj3", 5));
        DHeap Dh = new DHeap(2);
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));
        Dh.insert(new Item<>(8, "new Obj3", 4));
        Dh.insert(new Item<>(9, "new Obj3", 5));

        assertEquals(1, h.getParentIndex(2));
        assertEquals(h.getParentIndex(4), 2);
        assertEquals(h.getParentIndex(5), 2);
        assertEquals(Dh.getParentIndex(2), 1);
        assertEquals(Dh.getParentIndex(3), 1);

    }

    @Test
    public void getLeftChildIndexTest() throws NoLeftChildE {
        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        h.insert(new Item<>(8, "new Obj3", 4));
        h.insert(new Item<>(9, "new Obj3", 5));
        h.insert(new Item<>(10, "new Obj3", 6));
        h.insert(new Item<>(11, "new Obj3", 7));

        assertEquals(h.getLeftChildIndex(2), 4);
        assertEquals(h.getLeftChildIndex(1), 2);
        assertEquals(h.getLeftChildIndex(3), 6);
    }

    @Test
    public void getChildIndexTest() throws NoChildE{
        DHeap Dh = new DHeap(2);
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));
        Dh.insert(new Item<>(8, "new Obj3", 4));
        Dh.insert(new Item<>(9, "new Obj3", 5));
        Dh.insert(new Item<>(10, "new Obj3", 6));
        Dh.insert(new Item<>(11, "new Obj3", 7));

        assertEquals(Dh.getChildIndex(2, 1), 4);
        assertEquals(Dh.getChildIndex(1, 1), 2);
        assertEquals(Dh.getChildIndex(3, 1), 6);
    }

    @Test
    public void getRightChildIndexTest() throws NoRightChildE{

        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        h.insert(new Item<>(8, "new Obj3", 4));
        h.insert(new Item<>(9, "new Obj3", 5));
        h.insert(new Item<>(10, "new Obj3", 6));
        h.insert(new Item<>(11, "new Obj3", 7));

        assertEquals(h.getRightChildIndex(2), 5);
        assertEquals(h.getRightChildIndex(1), 3);
        assertEquals(h.getRightChildIndex(3), 7);

    }

    @Test
    public void swapTest(){

        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        h.insert(new Item<>(8, "new Obj3", 4));
        h.insert(new Item<>(9, "new Obj3", 5));
        h.insert(new Item<>(10, "new Obj3", 6));
        h.insert(new Item<>(11, "new Obj3", 7));

        BinaryHeap Dh = new BinaryHeap();
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));
        Dh.insert(new Item<>(8, "new Obj3", 4));
        Dh.insert(new Item<>(9, "new Obj3", 5));
        Dh.insert(new Item<>(10, "new Obj3", 6));
        Dh.insert(new Item<>(11, "new Obj3", 7));

        System.out.print(h.toString());
        h.swap(2, 3);
        System.out.print(h.toString());
        h.swap(4, 5);
        System.out.print(h.toString());
        h.swap(6, 7);
        System.out.print(h.toString());

        System.out.print(h.toString());
        h.swap(2, 3);
        System.out.print(h.toString());
        h.swap(4, 5);
        System.out.print(h.toString());
        h.swap(6, 7);
        System.out.print(h.toString());

    }

    @Test
    public void getKeyTest(){

        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        h.insert(new Item<>(8, "new Obj3", 4));
        h.insert(new Item<>(9, "new Obj3", 5));
        h.insert(new Item<>(10, "new Obj3", 6));
        h.insert(new Item<>(11, "new Obj3", 7));

        BinaryHeap Dh = new BinaryHeap();
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));
        Dh.insert(new Item<>(8, "new Obj3", 4));
        Dh.insert(new Item<>(9, "new Obj3", 5));
        Dh.insert(new Item<>(10, "new Obj3", 6));
        Dh.insert(new Item<>(11, "new Obj3", 7));

        assertEquals(h.getKey(1), 1);
        assertEquals(h.getKey(3), 3);
        assertEquals(h.getKey(5), 5);

        assertEquals(Dh.getKey(2), 2);
        assertEquals(Dh.getKey(4), 4);
        assertEquals(Dh.getKey(6), 6);

    }

    @Test
    public void insertTest(){

        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(1, "new Obj", 1));
        h.insert(new Item<>(2, "new Obj2", 2));
        h.insert(new Item<>(3, "new Obj3", 3));
        h.insert(new Item<>(4, "new Obj4", 4));
        h.insert(new Item<>(5, "new Obj5", 5));
        h.insert(new Item<>(6, "new Obj6", 6));
        h.insert(new Item<>(7, "new Obj7", 7));

        BinaryHeap Dh = new BinaryHeap();
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));
        Dh.insert(new Item<>(8, "new Obj4", 4));
        Dh.insert(new Item<>(9, "new Obj5", 5));
        Dh.insert(new Item<>(10, "new Obj6", 6));
        Dh.insert(new Item<>(11, "new Obj7", 7));

        //assertTrue(h.getElems().contains(1));
        //assertTrue(h.getElems().contains(3));
        //assertTrue(h.getElems().contains(5));
        System.out.print(Dh.getElems());

        //assertTrue(Dh.getElems().contains(1));
        //assertTrue(Dh.getElems().contains(3));
        //assertTrue(Dh.getElems().contains(5));

    }

    @Test
    public void extractMinTest(){

        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        h.insert(new Item<>(8, "new Obj3", 4));
        h.insert(new Item<>(9, "new Obj3", 5));
        h.insert(new Item<>(10, "new Obj3", 6));
        h.insert(new Item<>(11, "new Obj3", 7));

        BinaryHeap Dh = new BinaryHeap();
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));
        Dh.insert(new Item<>(8, "new Obj3", 4));
        Dh.insert(new Item<>(9, "new Obj3", 5));
        Dh.insert(new Item<>(10, "new Obj3", 6));
        Dh.insert(new Item<>(11, "new Obj3", 7));

        assertEquals(h.extractMin().getValue(), 1);
        assertEquals(h.extractMin().getValue(), 2);
        assertEquals(h.extractMin().getValue(), 3);

        assertEquals(Dh.extractMin().getValue(), 1);
        assertEquals(Dh.extractMin().getValue(), 2);
        assertEquals(Dh.extractMin().getValue(), 3);


    }

    @Test
    public void minChildIndexTest() throws NoLeftChildE{

        BinaryHeap h = new BinaryHeap();
        h.insert(new Item<>(5, "new Obj", 1));
        h.insert(new Item<>(6, "new Obj2", 2));
        h.insert(new Item<>(7, "new Obj3", 3));
        h.insert(new Item<>(8, "new Obj3", 4));
        h.insert(new Item<>(9, "new Obj3", 5));
        h.insert(new Item<>(10, "new Obj3", 6));
        h.insert(new Item<>(11, "new Obj3", 7));

        BinaryHeap Dh = new BinaryHeap();
        Dh.insert(new Item<>(5, "new Obj", 1));
        Dh.insert(new Item<>(6, "new Obj2", 2));
        Dh.insert(new Item<>(7, "new Obj3", 3));
        Dh.insert(new Item<>(8, "new Obj3", 4));
        Dh.insert(new Item<>(9, "new Obj3", 5));
        Dh.insert(new Item<>(10, "new Obj3", 6));
        Dh.insert(new Item<>(11, "new Obj3", 7));

        assertEquals(h.minChildIndex(1), 2);
        assertEquals(h.minChildIndex(3), 6);
        assertEquals(h.minChildIndex(2), 4);

        assertEquals(Dh.minChildIndex(2), 4);
        assertEquals(Dh.minChildIndex(1), 2);
        assertEquals(Dh.minChildIndex(3), 6);

    }

    @Test
    public void BFSTest() {

        Item<String> a = new Item<>("a", "a", 0);
        Item<String> b = new Item<>("b", "b", 0);
        Item<String> c = new Item<>("c", "c", 0);
        ArrayList<Item<String>> nodes = new ArrayList<>(Arrays.asList(a, b, c));
        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();

        neighbors.put(a,new ArrayList<>(Arrays.asList(b,c)));
        neighbors.put(b,new ArrayList<>(Arrays.asList(c)));
        neighbors.put(c,new ArrayList<>(Arrays.asList()));

        BFS<String> g = new BFS<>(neighbors);
        g.setSource(nodes, a);
        g.relax(a, c);

        nodes.forEach(Item::reset);

        Item<String> d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors2 = new Hashtable<>();
        neighbors2.put(a, new ArrayList<>(Arrays.asList(b, c)));
        neighbors2.put(b, new ArrayList<>(Arrays.asList(c, d, e)));
        neighbors2.put(c, new ArrayList<>(Arrays.asList()));
        neighbors2.put(d, new ArrayList<>(Arrays.asList(e)));
        neighbors2.put(e, new ArrayList<>(Arrays.asList()));

        Hashtable<Pair<Item<String>, Item<String>>, Integer> weights = new Hashtable<>();
        weights.put(new Pair<>(a, b), 2);
        weights.put(new Pair<>(a, c), 2);
        weights.put(new Pair<>(b, c), 1);
        weights.put(new Pair<>(b, d), 2);
        weights.put(new Pair<>(b, e), 2);
        weights.put(new Pair<>(d, e), 1);

        BFS<String> gg = new BFS<>(neighbors2);
        gg.setSource(nodes, a);
        gg.relax(a, c);

    }

    @Test
    public void DFSTest() {

        Item<String> a = new Item<>("a", "a", 0);
        Item<String> b = new Item<>("b", "b", 0);
        Item<String> c = new Item<>("c", "c", 0);
        ArrayList<Item<String>> nodes = new ArrayList<>(Arrays.asList(a, b, c));
        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();

        neighbors.put(a,new ArrayList<>(Arrays.asList(b,c)));
        neighbors.put(b,new ArrayList<>(Arrays.asList(c)));
        neighbors.put(c,new ArrayList<>(Arrays.asList()));

        DFS<String> g = new DFS<>(neighbors);
        g.setSource(nodes, a);
        g.relax(a, c);

        nodes.forEach(Item::reset);

        Item<String> d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors2 = new Hashtable<>();
        neighbors2.put(a, new ArrayList<>(Arrays.asList(b, c)));
        neighbors2.put(b, new ArrayList<>(Arrays.asList(c, d, e)));
        neighbors2.put(c, new ArrayList<>(Arrays.asList()));
        neighbors2.put(d, new ArrayList<>(Arrays.asList(e)));
        neighbors2.put(e, new ArrayList<>(Arrays.asList()));

        Hashtable<Pair<Item<String>, Item<String>>, Integer> weights = new Hashtable<>();
        weights.put(new Pair<>(a, b), 2);
        weights.put(new Pair<>(a, c), 2);
        weights.put(new Pair<>(b, c), 1);
        weights.put(new Pair<>(b, d), 2);
        weights.put(new Pair<>(b, e), 2);
        weights.put(new Pair<>(d, e), 1);

        DFS<String> gg = new DFS<>(neighbors2);
        gg.setSource(nodes, a);
        gg.relax(a, c);
    }

    @Test
    public void SPTest() {

        Item<String> a, b, c, d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(b, c)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(c, d, e)));
        neighbors.put(c, new ArrayList<>(Arrays.asList()));
        neighbors.put(d, new ArrayList<>(Arrays.asList(e)));
        neighbors.put(e, new ArrayList<>(Arrays.asList()));

        Hashtable<Pair<Item<String>, Item<String>>, Integer> weights = new Hashtable<>();
        weights.put(new Pair<>(a, b), 2);
        weights.put(new Pair<>(a, c), 2);
        weights.put(new Pair<>(b, c), 1);
        weights.put(new Pair<>(b, d), 2);
        weights.put(new Pair<>(b, e), 2);
        weights.put(new Pair<>(d, e), 1);

        SP<String> g = new SP<>(neighbors, weights);
        g.setSource(nodes, a);
        g.relax(a, c);

        nodes.forEach(Item::reset);

    }

    @Test
    public void SPDTest() {

        Item<String> a, b, c, d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(b, c)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(c, d, e)));
        neighbors.put(c, new ArrayList<>(Arrays.asList()));
        neighbors.put(d, new ArrayList<>(Arrays.asList(e)));
        neighbors.put(e, new ArrayList<>(Arrays.asList()));

        Hashtable<Pair<Item<String>, Item<String>>, Integer> weights = new Hashtable<>();
        weights.put(new Pair<>(a, b), 2);
        weights.put(new Pair<>(a, c), 2);
        weights.put(new Pair<>(b, c), 1);
        weights.put(new Pair<>(b, d), 2);
        weights.put(new Pair<>(b, e), 2);
        weights.put(new Pair<>(d, e), 1);

        SPD<String> g = new SPD<>(neighbors, weights);
        g.setSource(nodes, a);
        g.relax(a, c);

        nodes.forEach(Item::reset);

    }

    @Test
    public void MSPTest() {

        Item<String> a, b, c, d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(b, c)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(c, d, e)));
        neighbors.put(c, new ArrayList<>(Arrays.asList()));
        neighbors.put(d, new ArrayList<>(Arrays.asList(e)));
        neighbors.put(e, new ArrayList<>(Arrays.asList()));

        Hashtable<Pair<Item<String>, Item<String>>, Integer> weights = new Hashtable<>();
        weights.put(new Pair<>(a, b), 2);
        weights.put(new Pair<>(a, c), 2);
        weights.put(new Pair<>(b, c), 1);
        weights.put(new Pair<>(b, d), 2);
        weights.put(new Pair<>(b, e), 2);
        weights.put(new Pair<>(d, e), 1);

        MSP<String> g = new MSP<>(neighbors, weights);
        g.setSource(nodes, a);
        g.relax(a, c);

        nodes.forEach(Item::reset);
    }
}
