package models;

import enums.DirectionEnum;
import enums.OrientationEnum;

import java.util.ArrayList;
import java.util.List;

public class Aventurier extends Objet {

    private String nom;
    private OrientationEnum orientation;
    private List<Tresor> tresors = new ArrayList<>();
    private List<DirectionEnum> directions;

    public Aventurier(String nom, OrientationEnum orientation, Position position, List<Tresor> tresors, List<DirectionEnum> directions) {
        super(position);
        this.nom = nom;
        this.orientation = orientation;
        this.tresors = tresors;
        this.directions = directions;
    }

    public String getNom() {
        return nom;
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
