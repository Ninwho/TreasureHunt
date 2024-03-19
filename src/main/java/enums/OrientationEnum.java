package enums;

public enum OrientationEnum {
    NORD("N"),
    SUD("S"),
    OUEST("O"),
    EST("E");

    public final String valeur;

    private OrientationEnum(final String valeur) {
        this.valeur = valeur;
    }

    /**
     * Méthode accesseur qui renvoie la valeur de l'enum
     * @return la valeur de l'enum
     */
    public String getValeur() {
        return this.valeur;
    }
}
