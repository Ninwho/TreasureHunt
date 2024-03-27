package services;

import enums.DirectionEnum;
import enums.OrientationEnum;
import exceptions.DirectionInvalideException;
import exceptions.OrientationInvalideException;
import exceptions.PositionInvalideException;
import models.Aventurier;
import models.Carte;
import models.Position;
import models.Tresor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GameServiceTest {

    @Test
    void lancerJeuNordAvancerDroiteAvancerTest() throws DirectionInvalideException, OrientationInvalideException, PositionInvalideException {
        Carte carte = new Carte(5, 7, new ArrayList<>(), new ArrayList<>(), new Aventurier("Pimouss", OrientationEnum.N, new Position(1,2), new ArrayList<>(), Arrays.asList(DirectionEnum.A, DirectionEnum.D, DirectionEnum.A)));

        GameService.lancerJeu(carte);

        Assertions.assertEquals(OrientationEnum.E, carte.getAventurier().getOrientation());
        Assertions.assertEquals(new Position(2,1).toString(), carte.getAventurier().getPosition().toString());
    }

    @Test
    void lancerJeuSudAvancerGaucheAvancerTest() throws DirectionInvalideException, OrientationInvalideException, PositionInvalideException {
        Carte carte = new Carte(5, 7, new ArrayList<>(), new ArrayList<>(), new Aventurier("Pimouss", OrientationEnum.S, new Position(3,4), new ArrayList<>(), Arrays.asList(DirectionEnum.A, DirectionEnum.G, DirectionEnum.A)));

        GameService.lancerJeu(carte);

        Assertions.assertEquals(OrientationEnum.E, carte.getAventurier().getOrientation());
        Assertions.assertEquals(new Position(4,5).toString(), carte.getAventurier().getPosition().toString());
    }

    @Test
    void deplacerAventurierExceptionTest() throws DirectionInvalideException, OrientationInvalideException, PositionInvalideException {
        Carte carte = new Carte();

        assertThrows(DirectionInvalideException.class, () -> GameService.deplacerAventurier(carte, null));
    }

    @Test
    void deplacerAventurierOKTest() throws DirectionInvalideException, OrientationInvalideException, PositionInvalideException {
        Carte carte = new Carte(2, 7, null, null, new Aventurier("Biscotte", OrientationEnum.S, new Position(1,1), null, null));

        GameService.deplacerAventurier(carte, DirectionEnum.D);

        Assertions.assertEquals(OrientationEnum.O, carte.getAventurier().getOrientation());
    }


    @Test
    void avancerAventurierExceptionTest() throws OrientationInvalideException, PositionInvalideException {
        List<Tresor> tresors = Arrays.asList(new Tresor(new Position(1,0), 1));
        Carte carte = new Carte(2, 7, tresors, null, new Aventurier("Biscotte", null, new Position(1,1), null, null));

        assertThrows(OrientationInvalideException.class, () -> GameService.avancerAventurier(carte, null));
    }

    @Test
    void avancerAventurierOKTest() throws OrientationInvalideException, PositionInvalideException {
        List<Tresor> tresors = new LinkedList<>(Arrays.asList(new Tresor(new Position(1,0), 1)));
        Carte carte = new Carte(2, 7, tresors, new ArrayList<>(), new Aventurier("Biscotte", OrientationEnum.N, new Position(1,1), new ArrayList<>(), new ArrayList<>()));

        GameService.avancerAventurier(carte, OrientationEnum.N);

        Aventurier aventurier = carte.getAventurier();
        Assertions.assertEquals(1, aventurier.getPosition().getX());
        Assertions.assertEquals(0, aventurier.getPosition().getY());
        Assertions.assertEquals(0, tresors.size());
        Assertions.assertEquals(1, aventurier.getTresors().size());
    }

    @Test
    void estPositionDansLaCarteOKTest() {
        Carte carte = new Carte(5,5, null, null, new Aventurier("Pimouss", OrientationEnum.O, new Position(1,0), new ArrayList<>(), Arrays.asList(DirectionEnum.A)));
        Position position = new Position(1,1);

        boolean estPosDansLaCarte = GameService.estPositionDansLaCarte(carte, position);

        Assertions.assertTrue(estPosDansLaCarte);
    }

}
