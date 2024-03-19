package models;

import java.util.List;

public class Carte {

    private int hauteur;
    private int largeur;
    private List<Tresor> tresors;
    private List<Montagne> montagnes;
    private Aventurier aventurier;

    public Carte() {
    }
    public Carte(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public Carte(int hauteur, int largeur, List<Tresor> tresors, List<Montagne> montagnes, Aventurier aventurier) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.tresors = tresors;
        this.montagnes = montagnes;
        this.aventurier = aventurier;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public List<Tresor> getTresors() {
        return tresors;
    }

    public void setTresors(List<Tresor> tresors) {
        this.tresors = tresors;
    }

    public List<Montagne> getMontagnes() {
        return montagnes;
    }

    public void setMontagnes(List<Montagne> montagnes) {
        this.montagnes = montagnes;
    }

    public Aventurier getAventurier() {
        return aventurier;
    }

    public void setAventurier(Aventurier aventurier) {
        this.aventurier = aventurier;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "hauteur=" + hauteur +
                ", largeur=" + largeur +
                ", tresors=" + tresors +
                ", montagnes=" + montagnes +
                ", aventurier=" + aventurier +
                '}';
    }
}
