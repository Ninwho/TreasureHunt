package models;

import enums.DirectionEnum;
import enums.OrientationEnum;

import java.util.List;

public class Aventurier extends Objet {

    private String orientation;
    private List<Tresor> tresors;
    private List<String> directions;

    public Aventurier(String orientation, Position position, List<Tresor> tresors, List<String> directions) {
        super(position);
        this.orientation = orientation;
        this.tresors = tresors;
        this.directions = directions;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public List<Tresor> getTresors() {
        return tresors;
    }

    public void setTresors(List<Tresor> tresors) {
        this.tresors = tresors;
    }

    public List<String> getDirections() {
        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

    @Override
    public String toString() {
        return "Aventurier{" +
                "orientation=" + orientation +
                ", position=" + position +
                ", tresors=" + tresors +
                ", directions=" + directions +
                '}';
    }
}
