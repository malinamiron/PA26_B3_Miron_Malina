import java.util.Objects;

public class Location {
    public enum LocationType{
        city, airport, gasStation;
    }
    private String name;
    private LocationType type;
    private int x;
    private int y;

    public Location(String name, LocationType type ,int x, int y) {
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public LocationType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Location location)) return false;
        return getX() == location.getX() && getY() == location.getY() && Objects.equals(getName(), location.getName()) && getType() == location.getType();
    }

}
