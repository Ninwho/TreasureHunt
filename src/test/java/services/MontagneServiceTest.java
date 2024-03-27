package services;

import enums.DirectionEnum;
import enums.OrientationEnum;
import models.Aventurier;
import models.Carte;
import models.Montagne;
import models.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MontagneServiceTest {

    @Test
    void verifierAbsenceMontagneAbsente() {
        Montagne montagne = creerMontagne(1,1);
        List<Montagne> montagnes = Collections.singletonList(montagne);
        Carte carte = new Carte(5,5, null, montagnes, new Aventurier("Pimouss", OrientationEnum.O, new Position(1,0), new ArrayList<>(), Arrays.asList(DirectionEnum.A)));

        boolean estMontagneAbsente = MontagneService.verifierAbsenceMontagne(carte,new Position(1, 0));

        Assertions.assertTrue(estMontagneAbsente);
    }

    @Test
    void verifierAbsenceMontagnePresente() {
        Montagne montagne = creerMontagne(1,1);
        List<Montagne> montagnes = Collections.singletonList(montagne);
        Carte carte = new Carte(5,5, null, montagnes, new Aventurier("Pimouss", OrientationEnum.O, new Position(1,0), new ArrayList<>(), Arrays.asList(DirectionEnum.A)));

        boolean estMontagneAbsente = MontagneService.verifierAbsenceMontagne(carte,new Position(1, 1));

        Assertions.assertTrue(estMontagneAbsente);
    }

    Montagne creerMontagne(int x, int y) {
        return new Montagne(new Position(x, y));
    }

}
