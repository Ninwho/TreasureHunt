package services;

import models.Carte;
import models.Position;
import models.Tresor;

public class TresorService {

    private TresorService() {
    }

    public static void collecterTresor(Carte carte, Position position) {
        Tresor tresorPresent = carte.getTresors().stream().filter(tresor -> tresor.getPosition().getX() == position.getX() && tresor.getPosition().getY() == position.getY()).findFirst().orElse(null);
        if(tresorPresent != null && tresorPresent.getNombre() > 0) {
            carte.getAventurier().getTresors().add(tresorPresent);
            tresorPresent.setNombre(tresorPresent.getNombre() - 1);
            if(tresorPresent.getNombre() == 0) {
                carte.getTresors().remove(tresorPresent);
            }
        }
    }
}
