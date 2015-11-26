package clustering;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Point {
    private double x = 0, y = 0;
    private int cluster_number = 0;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getCluster() {
        return cluster_number;
    }

    public void setCluster(int cluster_number) {
        this.cluster_number = cluster_number;
    }

    // Calcul the distance between two points
    protected static double distance(Point p, Point centroid) {
        return Math.sqrt(Math.pow(centroid.getY() - p.getY(), 2) + Math.pow(centroid.getX() - p.getX(), 2));
    }

    protected static Point randomPoint(int min, int max) {
        Random r = new Random();
        double x = min + (max - min) * r.nextDouble();
        double y = min + (max - min) * r.nextDouble();
        return new Point(x, y);
    }

    protected static List<Point> randomPoints(int min, int max, int number) {
        ArrayList<Point> points = new ArrayList<>(number);
        for (int i = 0; i < number; i++) points.add(randomPoint(min, max));
        return points;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return "(" + df.format(x) + ", " + df.format(y) + ")";
    }

}
