import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Profile> network = new ArrayList<>();

        network.add(new Person("P1", "Alice", "Programmer"));
        network.add(new Company("C1", "Centric"));
        network.add(new Person("P2", "Bob", "Designer"));
        network.add(new Company("C2", "Aumovio"));

        network.sort(Profile::compareTo);

        System.out.println("network sortat dupa nume:");
        for (Profile p : network) {
            System.out.println(p);
        }
    }
}