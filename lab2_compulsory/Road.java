

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Road {

    public enum RoadType{
        highway, express, country;
    }

    private Location locInitial;
    private Location locFinal;
    private RoadType type;
    private int speedLimit;
    private int length;


    public Road(RoadType type, int speedLimit, int length, Location loc1, Location loc2) {
        this.type = type;
        this.speedLimit = speedLimit;
        this.length = length;
        this.locInitial = loc1;
        this.locFinal = loc2;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public int getLength() {
        return length;
    }

    public RoadType getType() {
        return type;
    }

    public Location getLocInitial() {
        return locInitial;
    }

    public Location getLocFinal() {
        return locFinal;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setType(RoadType type) {
        this.type = type;
    }

    public void setLocInitial(Location locInitial) {
        this.locInitial = locInitial;
    }

    public void setLocFinal(Location locFinal) {
        this.locFinal = locFinal;
    }

    @Override
    public String toString() {
        return "Road{" +
                "locInitial=" + locInitial +
                ", locFinal=" + locFinal +
                ", type=" + type +
                ", speedLimit=" + speedLimit +
                ", length=" + length +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Road road)) return false;
        return getSpeedLimit() == road.getSpeedLimit() && getLength() == road.getLength() && Objects.equals(getLocInitial(), road.getLocInitial()) && Objects.equals(getLocFinal(), road.getLocFinal()) && getType() == road.getType();
    }

}
