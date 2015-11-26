package clustering;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private final List<Point> points = new ArrayList<>();
    private Point centroid = null;
    private final int id;

    public Cluster(int id) {
        this.id = id;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void clear() {
        points.clear();
    }

    @Override
    public String toString() {
        String str = "\n[Cluster: " + id + "]" +
                " [Centroid: " + centroid + "]" +
                " \n[Points: ";
        for (Point p : points) str += p;
        return str + "]\n";
    }

}
