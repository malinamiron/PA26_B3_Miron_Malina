import java.util.Arrays;
/**
 * Reprezintă o instanță a unei probleme de rute sau grafuri, formată din locații și drumuri.
 * Această clasă oferă metode pentru validarea unicității componentelor și
 * verificarea conectivității între două locații folosind un algoritm de căutare în adâncime (DFS).
 */
public class ProblemInstance {
    private int countLocations;
    private int countRoads;
    private Location[] locations;
    private Road[] roads;
    private int[] visitedLocations;

    /**
     * Construiește o nouă instanță a problemei cu locațiile și drumurile specificate.
     * Inițializează contoarele și tabloul de vizite pe baza datelor de intrare.
     *
     * @param locations Un tablou de obiecte {@link Location}.
     * @param roads     Un tablou de obiecte {@link Road}.
     */

    public ProblemInstance(Location[] locations, Road[] roads) {
        this.locations = locations;
        this.roads = roads;
        this.countLocations = locations.length;
        this.countRoads = roads.length;
        this.visitedLocations = new int[locations.length];
        for(int i = 0; i < countLocations; i++){
            this.visitedLocations[i] = 0;
        }
    }

    public Location[] getLocations() {
        return locations;
    }

    public Road[] getRoads() {
        return roads;
    }

    /**
     * Validează integritatea instanței problemei.
     * Verifică dacă nu există locații duplicate și dacă toate drumurile sunt unice.
     *
     * @return {@code true} dacă toate locațiile și drumurile sunt unice; {@code false} în caz contrar.
     */

    public boolean isValid(){
        for ( int i = 0; i < this.countLocations; i++){
            for(int j = i + 1; j < this.countLocations; j++){
                if(locations[i].equals(locations[j])){
                    return false;
                }
            }
        }

        for ( int i = 0; i < this.countRoads; i++){
            for(int j = i + 1; j < this.countRoads; j++){
                if(roads[i].equals(roads[j])){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Determină dacă există un drum (cale) între două locații specificate.
     * Metoda utilizează un algoritm recursiv de tip Depth-First Search (DFS) și
     * gestionează starea "vizitat" pentru a gestiona corect ciclurile din graf.
     *
     * @param location1 Locația de pornire.
     * @param location2 Locația destinație.
     * @return {@code true} dacă există o secvență de drumuri care conectează cele două locații; {@code false} altfel.
     */

    public boolean areConnected(Location location1, Location location2){

        int index1 = 0, index2 = 0;
        int indexFinal = 0;
        for(int i = 0; i < countLocations; i++){
            if(locations[i].equals(location1)){
                index1 = i;
            }
            if(locations[i].equals(location2)){
                index2 = i;
            }
        }

        //index1 = Arrays.asList(locations).indexOf(location1);

        visitedLocations[index1] = 1;

        if(index1 == index2) {
            visitedLocations[index1] = 0;
            return true;
        }
        else {
            for(int i = 0; i < countRoads; i++){
                if(locations[index1].equals(roads[i].getLocInitial())){
                    for(int j = 0; j < countLocations; j++){
                        if(locations[j].equals(roads[i].getLocFinal())){
                            indexFinal = j;
                        }
                    }
                    if(visitedLocations[indexFinal] == 0 && areConnected(roads[i].getLocFinal(), location2)) {
                        visitedLocations[index1] = 0;
                        return true;
                    }
                }

                if(locations[index1].equals(roads[i].getLocFinal())){
                    for(int j = 0; j < countLocations; j++){
                        if(locations[j].equals(roads[i].getLocInitial())){
                            indexFinal = j;
                        }
                    }
                    if(visitedLocations[indexFinal] == 0 && areConnected(roads[i].getLocInitial(), location2)) {
                        visitedLocations[index1] = 0;
                        return true;
                    }
                }
            }

            visitedLocations[index1] = 0;
            return false;
        }
    }
}
