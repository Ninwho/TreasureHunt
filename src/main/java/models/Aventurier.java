package models;

import enums.DirectionEnum;
import enums.OrientationEnum;

import java.util.List;

public class Aventurier extends Objet {

    private OrientationEnum orientation;
    private List<Tresor> tresors;
    private List<DirectionEnum> directions;

    public Aventurier(OrientationEnum orientation, Position position, List<Tresor> tresors, List<DirectionEnum> directions) {
        super(position);
        this.orientation = orientation;
        this.tresors = tresors;
        this.directions = directions;
    }

    public OrientationEnum getOrientation() {
        return orientation;
    }

    public void setOrientation(OrientationEnum orientation) {
        this.orientation = orientation;
    }

    public List<Tresor> getTresors() {
        return tresors;
    }

    public void setTresors(List<Tresor> tresors) {
        this.tresors = tresors;
    }

    public List<DirectionEnum> getDirections() {
        return directions;
    }

    public void setDirections(List<DirectionEnum> directions) {
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
