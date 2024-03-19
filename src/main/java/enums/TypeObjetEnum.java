package enums;

public enum TypeObjetEnum {

    CARTE("C"),
    MONTAGNE("M"),
    TRESOR("T"),
    AVENTURIER("A");

    private final String valeur;

    private TypeObjetEnum(final String valeur) {
        this.valeur = valeur;
    }

    /**
     * Méthode accesseur qui renvoie la valeur de l'enum
     * @return la valeur de l'enum
     */
    public String getValeur() {
        return valeur;
    }
}
