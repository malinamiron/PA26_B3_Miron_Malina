import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clasa Solution calculeaza ruta optima intre doua locatii dintr-o instanta de problema data.
 * Utilizeaza algoritmul Dijkstra pentru a gasi drumul critic.
 */
public class Solution {
    public enum RouteType { FAST, SHORT }

    private final ProblemInstance problemInstance;
    private RouteType routeType;
    private Location locationA;
    private Location locationB;


    private double[] minTime;
    private int[] minDistance;
    private boolean[] visited;

    private int travelDistance = 0;
    private double travelTime = 0;

    /**
     * Constructor principal care initiaza procesul de cautare a solutiei.
     */
    public Solution(ProblemInstance problemInstance, RouteType routeType, Location locationA, Location locationB) {
        this.problemInstance = problemInstance;
        this.routeType = routeType;
        this.locationA = locationA;
        this.locationB = locationB;

        if (!this.problemInstance.isValid()) {
            System.out.println("Instanta data nu este valida!");
            return;
        }

        int n = problemInstance.getLocations().length;
        this.visited = new boolean[n];
        this.minTime = new double[n];
        this.minDistance = new int[n];


        if (!problemInstance.areConnected(locationA, locationB)) {
            System.out.println("Nu exista drum intre cele 2 locatii!");
        } else {
            if (routeType == RouteType.FAST) {
                calculateDijkstra(true);
            } else {
                calculateDijkstra(false);
            }
        }
    }

    /**
     * Implementare unificată a algoritmului Dijkstra.
     * @param optimizeForTime daca e true, optimizeaza timpul, altfel distanta.
     */
    private void calculateDijkstra(boolean optimizeForTime) {
        Location[] allLocs = problemInstance.getLocations();
        Road[] allRoads = problemInstance.getRoads();

        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < allLocs.length; i++) {
            if (allLocs[i].equals(locationA)) startIndex = i;
            if (allLocs[i].equals(locationB)) endIndex = i;

            minTime[i] = Double.MAX_VALUE;
            minDistance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        minTime[startIndex] = 0;
        minDistance[startIndex] = 0;

        for (int count = 0; count < allLocs.length; count++) {
            int u = getNextNode(optimizeForTime);
            if (u == -1 || u == endIndex) break;

            visited[u] = true;

            for (Road road : allRoads) {
                int v = -1;

                if (road.getLocInitial().equals(allLocs[u])) {
                    v = findLocationIndex(road.getLocFinal());
                } else if (road.getLocFinal().equals(allLocs[u])) {
                    v = findLocationIndex(road.getLocInitial());
                }

                if (v != -1 && !visited[v]) {
                    double weightTime = (double) road.getLength() / road.getSpeedLimit();
                    int weightDist = road.getLength();

                    if (optimizeForTime) {
                        if (minTime[u] + weightTime < minTime[v]) {
                            minTime[v] = minTime[u] + weightTime;
                            minDistance[v] = minDistance[u] + weightDist;
                        }
                    } else {
                        if (minDistance[u] + weightDist < minDistance[v]) {
                            minDistance[v] = minDistance[u] + weightDist;
                            minTime[v] = minTime[u] + weightTime;
                        }
                    }
                }
            }
        }

        this.travelTime = minTime[endIndex];
        this.travelDistance = minDistance[endIndex];
    }

    private int getNextNode(boolean optimizeForTime) {
        double minVal = Double.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                double currentVal = optimizeForTime ? minTime[i] : minDistance[i];
                if (currentVal <= minVal) {
                    minVal = currentVal;
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    private int findLocationIndex(Location loc) {
        Location[] allLocs = problemInstance.getLocations();
        for (int i = 0; i < allLocs.length; i++) {
            if (allLocs[i].equals(loc)) return i;
        }
        return -1;
    }


    public double getTravelTime() { return travelTime; }
    public int getTravelDistance() { return travelDistance; }

    @Override
    public String toString() {
        return String.format("Solutie [%s]: Distanta = %d km, Timp = %.2f ore",
                routeType, travelDistance, travelTime);
    }
}