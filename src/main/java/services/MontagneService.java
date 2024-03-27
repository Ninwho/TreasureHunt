package services;

import models.Carte;
import models.Montagne;
import models.Position;

import java.util.Optional;

public class MontagneService {

    private MontagneService() {
    }

    public static boolean verifierAbsenceMontagne(Carte carte, Position position) {
        Montagne montagnePresente = carte.getMontagnes().stream().filter(montagne -> montagne.getPosition().equals(position)).findFirst().orElse(null);
        return montagnePresente == null;
    }
}
