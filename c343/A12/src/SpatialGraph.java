import java.util.*;
import java.util.stream.Collectors;

public class SpatialGraph {
    private Rect grid;
    private ArrayList<Point> points;

    private ArrayList<Item<Point>> nodes;
    private Hashtable<Item<Point>, ArrayList<Item<Point>>> neighbors;
    private Hashtable<Edge, Integer> weights;

    private XTree kdtree; // initialized by preprocess
    private Hashtable<Edge, HashSet<Region>> regionalEdges; // initialized by preprocess

    SpatialGraph(
            Rect grid,
            ArrayList<Point> points,
            ArrayList<Item<Point>> nodes,
            Hashtable<Item<Point>, ArrayList<Item<Point>>> neighbors,
            Hashtable<Edge, Integer> weights) {
        this.grid = grid;
        this.points = points;
        this.nodes = nodes;
        this.neighbors = neighbors;
        this.weights = weights;
    }

    SpatialGraph(Random r, int numberNodes, int maxNeighbors, int maxWeight, int gridSize) {
        this.grid = new Rect(0, 0, gridSize, gridSize);
        this.points = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.neighbors = new Hashtable<>();
        this.weights = new Hashtable<>();

        for (int i = 0; i < numberNodes; i++) {
            Point p = new Point(r.nextInt(gridSize), r.nextInt(gridSize));
            Item<Point> n = new Item<>(p, p.toString(), Integer.MAX_VALUE);
            while (nodes.contains(n)) {
                p = new Point(r.nextInt(gridSize), r.nextInt(gridSize));
                n = new Item<>(p, p.toString(), Integer.MAX_VALUE);
            }
            points.add(p);
            nodes.add(n);
        }

        for (int i = 0; i < numberNodes; i++) {
            ArrayList<Item<Point>> neighbors = new ArrayList<>();
            int numberNeighbors = r.nextInt(maxNeighbors);

            for (int j = 0; j < numberNeighbors; j++) {
                int neighborIndex = r.nextInt(numberNodes);
                while (i == neighborIndex) {
                    neighborIndex = r.nextInt(numberNodes);
                }
                Item<Point> neighbor = nodes.get(neighborIndex);
                neighbors.add(neighbor);
                weights.put(new Edge(nodes.get(i).getData(), neighbor.getData()), r.nextInt(maxWeight));
            }

            this.neighbors.put(nodes.get(i), neighbors);
        }
    }

    // -----

    Rect getGrid() {
        return grid;
    }

    ArrayList<Point> getPoints() {
        return points;
    }

    ArrayList<Item<Point>> getNodes() {
        return nodes;
    }

    Hashtable<Item<Point>, ArrayList<Item<Point>>> getNeighbors() {
        return neighbors;
    }

    Hashtable<Edge, Integer> getWeights() {
        return weights;
    }

    XTree getKdtree() {
        return kdtree;
    }

    Hashtable<Edge, HashSet<Region>> getRegionalEdges() {
        return regionalEdges;
    }

    // -----
    // Computes all shortest paths from a given node
    // Nodes are marked with the shortest path to the source

    void allShortestPaths(Item<Point> source) {

        nodes.forEach(Item::reset);
        nodes.forEach(node -> node.setValue(Integer.MAX_VALUE));
        source.setValue(0);
        BinaryHeap h = new BinaryHeap(nodes);

        while(!h.isEmpty() && (h.findMin().getValue() != Integer.MAX_VALUE)) {

            Item<Point> current = h.extractMin();

            if (!current.isVisited()) {
                current.setVisited(true);
                for (Item<Point> neighbor : neighbors.get(current)) {
                    Edge ee = new Edge(current.getData(), neighbor.getData());
                    int val = (current.getValue() == Integer.MAX_VALUE) ? Integer.MAX_VALUE : current.getValue() + weights.get(ee);
                    if (val < neighbor.getValue()) {
                        h.updateKey(neighbor.getPosition(), val);
                        neighbor.setPrevious(current);
                    }
                }
            }
        }
    }

    // -----
    // Point-to-point shortest path; stops as soon as it reaches destination

    ArrayList<Edge> shortestPath(Item<Point> source, Item<Point> dest) {

        nodes.forEach(Item::reset);
        nodes.forEach(node -> node.setValue(Integer.MAX_VALUE));
        source.setValue(0);
        BinaryHeap h = new BinaryHeap(nodes);

        while(!h.isEmpty() && (h.findMin().getValue() != Integer.MAX_VALUE)) {

            Item<Point> current = h.extractMin();
            if (current == dest){break;}

            if (!current.isVisited()) {
                current.setVisited(true);
                //if (current == dest){break;}
                for (Item<Point> neighbor : neighbors.get(current)) {
                    Edge ee = new Edge(current.getData(), neighbor.getData());
                    int val = (current.getValue() == Integer.MAX_VALUE) ? Integer.MAX_VALUE : current.getValue() + weights.get(ee);
                    if (val < neighbor.getValue()) {
                        h.updateKey(neighbor.getPosition(), val);
                        neighbor.setPrevious(current);
                    }
                }
            }
        }

        return Item.pathToSource(dest);
    }

    // -----

    void buildKDTree(int bound) {

        List<Point> xPoints = new ArrayList<>(points);
        xPoints.sort(Comparator.comparing(Point::getX));

        List<Point> yPoints = new ArrayList<>(points);
        yPoints.sort(Comparator.comparing(Point::getY));

        this.kdtree = XTree.makeXTree(xPoints, yPoints, bound);
    }

    void preprocess(int bound) {

        nodes.forEach(Item::reset);

        buildKDTree(bound);

        regionalEdges = new Hashtable<>();

        for (Edge e: weights.keySet()){
            regionalEdges.put(e, new HashSet<>());
        }

        for(int s = 0; s < nodes.size(); s++) {
            allShortestPaths(nodes.get(s));
            for (Item<Point> d : nodes) {
                ArrayList<Edge> path = Item.pathToSource(d);
                if (path.size() > 0) {
                    Edge tempE = path.get(path.size()-1).flip();
                    Region r = kdtree.findRegion(d.getData());
                    regionalEdges.get(tempE).add(r);
                }
            }
        }
    }


    // -----

    ArrayList<Edge> regionalShortestPath(Item<Point> source, Item<Point> dest) {

        nodes.forEach(Item::reset);
        nodes.forEach(node -> node.setValue(Integer.MAX_VALUE));
        source.setValue(0);
        BinaryHeap h = new BinaryHeap(nodes);

        Region destR = kdtree.findRegion(dest.getData());

        while(!h.isEmpty() && (h.findMin().getValue() != Integer.MAX_VALUE)) {

            Item<Point> current = h.extractMin();
            if (current == dest){break;}

            if (!current.isVisited()) {

                current.setVisited(true);

                for (Item<Point> neighbor : neighbors.get(current)) {
                    Edge ee = new Edge(current.getData(), neighbor.getData());
                    if (regionalEdges.get(ee).contains(destR)) {
                        int val = (current.getValue() == Integer.MAX_VALUE) ? Integer.MAX_VALUE : current.getValue() + weights.get(ee);
                        if (val < neighbor.getValue()) {
                            h.updateKey(neighbor.getPosition(), val);
                            neighbor.setPrevious(current);
                        }
                    }
                }
            }
        }

        return Item.pathToSource(dest);

    }

    // -----

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Nodes:%n%s%n", nodes));
        res.append(String.format("Neighbors:%n%s%n", neighbors));
        res.append(String.format("Weights:%n%s%n", weights));
        return new String(res);
    }
}
