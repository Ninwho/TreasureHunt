package enums;

public enum DirectionEnum {

    AVANCER("A"),
    TOURNER_A_DROITE("D"),
    TOURNER_A_GAUCHE("G");

    private final String valeur;

    private DirectionEnum(final String valeur) {
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
