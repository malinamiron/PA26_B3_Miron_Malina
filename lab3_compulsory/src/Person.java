import java.util.Objects;

public class Person implements Profile, Comparable<Profile> {
    private String id;
    private String name;
    private String role; // e.g., "Programmer", "Designer"

    public Person(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    @Override
    public int compareTo(Profile other) {

        return this.name.compareTo(other.getName());
    }

    @Override
    public String toString() {
        return "Person: " + name + " (" + role + ")";
    }

}