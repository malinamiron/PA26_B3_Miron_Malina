import java.util.stream.IntStream;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Location iasi = new City("Iasi", 47, 27, 290000);
        Location bucuresti = new City("Bucuresti", 44, 26, 1800000);
        Location otopeni = new AirPort("Henri Coanda", 44, 26, 10);
        Location benzinarie = new GasStation("Petrom A1", 45, 25, 5.5);

        Location[] locatii = {iasi, bucuresti, otopeni, benzinarie};

        Road r1 = new Road(Road.RoadType.highway, 130, 400, iasi, bucuresti);
        Road r2 = new Road(Road.RoadType.express, 100, 20, bucuresti, otopeni);
        Road r3 = new Road(Road.RoadType.country, 50, 10, otopeni, benzinarie);

        Road[] drumuri = {r1, r2, r3};

        ProblemInstance problema = new ProblemInstance(locatii, drumuri);

        System.out.println("Test Instanta Mica");
        if (problema.isValid()) {
            Solution solutieRapida = new Solution(problema, Solution.RouteType.FAST, iasi, benzinarie);
            System.out.println(solutieRapida);

            Solution solutieScurta = new Solution(problema, Solution.RouteType.SHORT, iasi, benzinarie);
            System.out.println(solutieScurta);
        }

        System.out.println("\nTest Large Instance");
        testPerformance(1000, 5000);
    }

    private static void testPerformance(int numLocations, int numRoads) {
        Random rand = new Random();

        Location[] locations = IntStream.range(0, numLocations)
                .mapToObj(i -> new City("City " + i, rand.nextInt(1000), rand.nextInt(1000), rand.nextInt(100000)))
                .toArray(Location[]::new);

        Road[] roads = new Road[numRoads];
        for (int i = 0; i < numRoads; i++) {
            Location l1 = locations[rand.nextInt(numLocations)];
            Location l2 = locations[rand.nextInt(numLocations)];
            roads[i] = new Road(Road.RoadType.highway, 50 + rand.nextInt(80), 10 + rand.nextInt(500), l1, l2);
        }

        ProblemInstance bigProblem = new ProblemInstance(locations, roads);

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        long startTime = System.nanoTime();

        Solution bigSolution = new Solution(bigProblem, Solution.RouteType.FAST, locations[0], locations[numLocations - 1]);

        long endTime = System.nanoTime();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Locatii: " + numLocations + " | Drumuri: " + numRoads);
        System.out.println("Timp executie: " + (endTime - startTime) / 1_000_000.0 + " ms");
        System.out.println("Memorie utilizata: " + (memoryAfter - memoryBefore) / 1024 + " KB");
    }
}