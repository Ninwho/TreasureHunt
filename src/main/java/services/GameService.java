package services;

import enums.DirectionEnum;
import enums.OrientationEnum;
import exceptions.DirectionInvalideException;
import exceptions.OrientationInvalideException;
import models.Carte;

public class GameService {

    public static void lancerJeu() {

    }

    public static void deplacerAventurier(Carte carte, DirectionEnum direction) throws DirectionInvalideException, OrientationInvalideException {
        switch(direction) {
            case DirectionEnum.AVANCER:
                avancerAventurier(carte);
                break;
            case DirectionEnum.TOURNER_A_DROITE:
                carte.getAventurier().setOrientation(determinerNouvelleOrientation(OrientationEnum.valueOf(carte.getAventurier().getOrientation()), DirectionEnum.TOURNER_A_DROITE));
                break;
            case DirectionEnum.TOURNER_A_GAUCHE:
                carte.getAventurier().setOrientation(determinerNouvelleOrientation(OrientationEnum.valueOf(carte.getAventurier().getOrientation()), DirectionEnum.TOURNER_A_GAUCHE));
                break;
            default:
                throw new DirectionInvalideException();
        }
    }

    public static void avancerAventurier(Carte carte) {
        // Cas où l'aventurier doit avancer

    }

    public static String determinerNouvelleOrientation(OrientationEnum orientationActuelle, DirectionEnum direction) throws OrientationInvalideException, DirectionInvalideException {
        if(direction.equals(DirectionEnum.TOURNER_A_DROITE)) {
            return switch (orientationActuelle) {
                case OrientationEnum.NORD -> OrientationEnum.EST.getValeur();
                case OrientationEnum.SUD -> OrientationEnum.OUEST.getValeur();
                case OrientationEnum.OUEST -> OrientationEnum.NORD.getValeur();
                case OrientationEnum.EST -> OrientationEnum.SUD.getValeur();
                default -> throw new OrientationInvalideException();
            };
        } else if (direction.equals(DirectionEnum.TOURNER_A_GAUCHE)) {
            return switch (orientationActuelle) {
                case OrientationEnum.NORD -> OrientationEnum.OUEST.getValeur();
                case OrientationEnum.SUD -> OrientationEnum.EST.getValeur();
                case OrientationEnum.OUEST -> OrientationEnum.SUD.getValeur();
                case OrientationEnum.EST -> OrientationEnum.NORD.getValeur();
                default -> throw new OrientationInvalideException();
            };
        } else {
            throw new DirectionInvalideException();
        }


    }

}
