package services;

import enums.DirectionEnum;
import enums.OrientationEnum;
import exceptions.DirectionInvalideException;
import exceptions.OrientationInvalideException;
import exceptions.PositionInvalideException;
import models.Carte;
import models.Position;

public class GameService {

    public static void lancerJeu(Carte carte) throws DirectionInvalideException, OrientationInvalideException, PositionInvalideException {
        for(DirectionEnum direction : carte.getAventurier().getDirections()) {
            deplacerAventurier(carte, direction);
        }
    }

    public static void deplacerAventurier(Carte carte, DirectionEnum direction) throws DirectionInvalideException, OrientationInvalideException, PositionInvalideException {
        switch(direction) {
            case DirectionEnum.A:
                avancerAventurier(carte, carte.getAventurier().getOrientation());
                break;
            case DirectionEnum.D:
                carte.getAventurier().setOrientation(determinerNouvelleOrientation(carte.getAventurier().getOrientation(), DirectionEnum.D));
                break;
            case DirectionEnum.G:
                carte.getAventurier().setOrientation(determinerNouvelleOrientation(carte.getAventurier().getOrientation(), DirectionEnum.G));
                break;
            default:
                throw new DirectionInvalideException();
        }
    }

    public static void avancerAventurier(Carte carte, OrientationEnum orientation) throws OrientationInvalideException, PositionInvalideException {
        // Cas où l'aventurier doit avancer
        Position nouvellePosition = calculerNouvellePosition(carte.getAventurier().getPosition(), orientation);
        if (estPositionDansLaCarte(carte, nouvellePosition)) {
            carte.getAventurier().setPosition(nouvellePosition);
        } else {
            throw new PositionInvalideException();
        }

    }

    public static OrientationEnum determinerNouvelleOrientation(OrientationEnum orientationActuelle, DirectionEnum direction) throws OrientationInvalideException, DirectionInvalideException {
        if(direction.equals(DirectionEnum.D)) {
            return switch (orientationActuelle) {
                case OrientationEnum.N -> OrientationEnum.E;
                case OrientationEnum.S -> OrientationEnum.O;
                case OrientationEnum.O -> OrientationEnum.N;
                case OrientationEnum.E -> OrientationEnum.S;
                default -> throw new OrientationInvalideException();
            };
        } else if (direction.equals(DirectionEnum.G)) {
            return switch (orientationActuelle) {
                case OrientationEnum.N -> OrientationEnum.O;
                case OrientationEnum.S -> OrientationEnum.E;
                case OrientationEnum.O -> OrientationEnum.S;
                case OrientationEnum.E -> OrientationEnum.N;
                default -> throw new OrientationInvalideException();
            };
        } else {
            throw new DirectionInvalideException();
        }
    }

    public static Position determinerDeltaPosition(OrientationEnum orientation) throws OrientationInvalideException {
        return switch (orientation) {
            case OrientationEnum.N -> new Position(0,-1);
            case OrientationEnum.S -> new Position(0,1);
            case OrientationEnum.E -> new Position(1,0);
            case OrientationEnum.O -> new Position(-1,0);
            default -> throw new OrientationInvalideException();
        };
    }

    public static Position calculerNouvellePosition(Position position, OrientationEnum orientation) throws OrientationInvalideException {
        Position delta = determinerDeltaPosition(orientation);
        return new Position(position.getX() + delta.getX(), position.getY() + delta.getY());
    }

    public static boolean estPositionDansLaCarte(Carte carte, Position position) {
        return position.getX() >= 0 && position.getY() >= 0 && position.getX() < carte.getLargeur() && position.getY() < carte.getHauteur();
    }

    public static void collecterTresor(Carte carte, Position position) {

    }

}
