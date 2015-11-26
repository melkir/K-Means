package clustering;

import java.util.ArrayList;
import java.util.List;

public class KMeans {

    private final int NUM_CLUSTERS = 3;                                 // Number of Clusters
    private final int NUM_POINTS = 15;                                  // Number of Points
    private static final int MIN_COORDINATE = 0, MAX_COORDINATE = 10;   // Min and Max value of coordinate
    private List<Point> points = new ArrayList<>();                     // List of points
    private final List<Cluster> clusters = new ArrayList<>();           // List of clusters

    public static void main(String[] args) {
        KMeans kMeans = new KMeans();
        kMeans.init();
        // Print initial stage

        System.out.println("Liste des points :");
        System.out.println(kMeans.points);
        System.out.println("Liste des clusters :");
        System.out.println(kMeans.clusters);

        kMeans.calculate();
        // Print final stage
        System.out.println("Etat des clusters apres assignation :");
        System.out.println(kMeans.clusters);
    }

    private void init() {
        // Create a list of random points
        points = Point.randomPoints(MIN_COORDINATE, MAX_COORDINATE, NUM_POINTS);
        // Create a list of random clusters
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster cluster = new Cluster(i);
            Point centroid = Point.randomPoint(MIN_COORDINATE, MAX_COORDINATE);
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }
    }

    // Calculate K Means with iterating method
    private void calculate() {
        boolean finish = false;
        int iter = 0;

        // Add in new data, one at a time, recalculating centroids with each new one
        while (!finish) {
            // Clear custer state
            clearClusters();
            List<Point> lastCentroids = getCentroids();
            // Assign points to the closer cluster
            assignCluster();
            // Calculate new centroids
            calculateCentroids();
            ++iter;
            List<Point> currentCentroids = getCentroids();
            double distance = 0;
            for (int i = 0; i < lastCentroids.size(); i++) {
                distance += Point.distance(lastCentroids.get(i), currentCentroids.get(i));
                System.out.println("################");
                System.out.println("Iteration : " + iter);
                System.out.println("Centroid distances: " + distance);
            }

            if (0 == distance) finish = true;
        }
    }

    private void clearClusters() {
        clusters.forEach(Cluster::clear);
    }

    private List<Point> getCentroids() {
        List<Point> centroids = new ArrayList<>(NUM_CLUSTERS);
        for (Cluster cluster : clusters) {
            Point aux = cluster.getCentroid();
            Point point = new Point(aux.getX(), aux.getY());
            centroids.add(point);
        }
        return centroids;
    }

    private void assignCluster() {
        double min, max = Double.MAX_VALUE, distance;
        int cluster = 0;

        for (Point p : points) {
            min = max;
            for (int i = 0; i < NUM_CLUSTERS; i++) {
                distance = Point.distance(p, clusters.get(i).getCentroid());
                if (distance < min) {
                    min = distance;
                    cluster = i;
                }
            }
            p.setCluster(cluster);
            clusters.get(cluster).addPoint(p);
        }
    }

    private void calculateCentroids() {
        for (Cluster cluster : clusters) {
            double sumX = 0, sumY = 0;
            List<Point> list = cluster.getPoints();
            int n = list.size();

            for (Point p : list) {
                sumX += p.getX();
                sumY += p.getY();
            }

            Point centroid = cluster.getCentroid();
            if (n > 0) {
                double newX = sumX / n;
                double newY = sumY / n;
                centroid.setX(newX);
                centroid.setY(newY);
            }
        }
    }
}
