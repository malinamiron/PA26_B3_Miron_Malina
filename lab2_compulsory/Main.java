public class Main{
    public static void main(String[] args){

        Location locatie1 = new Location("Iasi", Location.LocationType.city, 150, 150 );
        Location locatie2 = new Location("Aeroportul Iasi", Location.LocationType.airport, 100, 100 );

        Road road1 = new Road(Road.RoadType.highway, 60, 150, locatie1, locatie2);
        Road road2 = new Road(Road.RoadType.express, 100, 200 , locatie1, locatie2);

        System.out.println(locatie1);
        System.out.println(locatie2);
        System.out.println(road1);
        System.out.println(road2);
    }
}