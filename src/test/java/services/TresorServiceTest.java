package services;

import enums.DirectionEnum;
import enums.OrientationEnum;
import models.Aventurier;
import models.Carte;
import models.Position;
import models.Tresor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class TresorServiceTest {

    @Test
    void collecterUnTresorEtResteTest() {
        Tresor tresor1 = creerTresor(0,0,3);
        List<Tresor> tresors = Collections.singletonList(tresor1);
        Carte carte = new Carte(5,5,tresors,null, new Aventurier("Dorian", OrientationEnum.O, new Position(1,0), new ArrayList<>(), Arrays.asList(DirectionEnum.A)));

        TresorService.collecterTresor(carte, new Position(0, 0));

        Assertions.assertEquals(1, carte.getAventurier().getTresors().size());
        Assertions.assertEquals(2, tresor1.getNombre());
    }

    @Test
    void collecterUnTresorSansResteTest() {
        Tresor tresor1 = creerTresor(0,0,1);
        List<Tresor> tresors = new LinkedList<>(Collections.singletonList(tresor1));
        Carte carte = new Carte(5,5,tresors,null, new Aventurier("Dorian", OrientationEnum.O, new Position(1,0), new ArrayList<>(), Arrays.asList(DirectionEnum.A)));

        TresorService.collecterTresor(carte, new Position(0, 0));

        Assertions.assertEquals(1, carte.getAventurier().getTresors().size());
        Assertions.assertEquals(0, tresor1.getNombre());
        Assertions.assertEquals(0, carte.getTresors().size());
    }

    @Test
    void collecterAucunTresorTest() {
        List<Tresor> tresors = new ArrayList<>();
        Carte carte = new Carte(5,5,tresors,null, new Aventurier("Dorian", OrientationEnum.O, new Position(1,0), new ArrayList<>(), Arrays.asList(DirectionEnum.A)));

        TresorService.collecterTresor(carte, new Position(0, 0));

        Assertions.assertEquals(0, carte.getAventurier().getTresors().size());
    }

    Tresor creerTresor(int x, int y, int nombre) {
        return new Tresor(new Position(x,x),nombre);
    }
}
