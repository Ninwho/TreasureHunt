package models;

public class Tresor extends Objet {

    private int nombre;

    public Tresor(Position position, int nombre) {
        super(position);
        this.nombre = nombre;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Tresor{" +
                "position=" + position +
                ", nombre=" + nombre +
                '}';
    }
}
