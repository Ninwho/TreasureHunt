package models;

public class Objet {

    public Position position;

    public Objet(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Objet{" +
                "position=" + position +
                '}';
    }
}
