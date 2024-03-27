package services;

import enums.DirectionEnum;
import enums.OrientationEnum;
import exceptions.DirectionInvalideException;
import exceptions.OrientationInvalideException;
import exceptions.PositionInvalideException;
import models.Carte;
import models.Position;

public class GameService {

    private static final String ORIENTATION_INVALIDE = "L'orientation est invalide";
    private static final String DIRECTION_INVALIDE = "La direction est invalide";
    private static final String POSITION_INVALIDE = "La position est invalide";

    private GameService() {
    }

    public static void lancerJeu(Carte carte) throws DirectionInvalideException, OrientationInvalideException, PositionInvalideException {
        // Lancement du jeu
        if(carte.getAventurier() == null) {
            // Si pas d'aventurier, on arrête
            return;
        }
        // Pour chaque direction de l'aventurier, on le déplace sur la carte
        for(DirectionEnum direction : carte.getAventurier().getDirections()) {
            deplacerAventurier(carte, direction);
        }
    }

    public static void deplacerAventurier(Carte carte, DirectionEnum direction) throws DirectionInvalideException, OrientationInvalideException, PositionInvalideException {
        switch(direction) {
            case DirectionEnum.A:
                // Si A, l'aventurier avance
                avancerAventurier(carte, carte.getAventurier().getOrientation());
                break;
            case DirectionEnum.D:
                // Si D, l'aventurier tourne à droite
                carte.getAventurier().setOrientation(determinerNouvelleOrientation(carte.getAventurier().getOrientation(), DirectionEnum.D));
                break;
            case DirectionEnum.G:
                // Si G, l'aventurier tourne à gauche
                carte.getAventurier().setOrientation(determinerNouvelleOrientation(carte.getAventurier().getOrientation(), DirectionEnum.G));
                break;
            case null, default:
                throw new DirectionInvalideException(DIRECTION_INVALIDE);
        }
    }

    public static void avancerAventurier(Carte carte, OrientationEnum orientation) throws OrientationInvalideException, PositionInvalideException {
        // Cas où l'aventurier doit avancer
        // On calcule sa nouvelle position selon son orientation
        Position nouvellePosition = calculerNouvellePosition(carte.getAventurier().getPosition(), orientation);
        // Si la position est valide et qu'il n'y a pas de montagne, on lui set une nouvelle position
        if (estPositionDansLaCarte(carte, nouvellePosition) && MontagneService.verifierAbsenceMontagne(carte, nouvellePosition)) {
            carte.getAventurier().setPosition(nouvellePosition);
            // On vérifie s'il y a un trésor sur la nouvelle position
            TresorService.collecterTresor(carte, nouvellePosition);
        } else {
            throw new PositionInvalideException(POSITION_INVALIDE);
        }
    }

    public static OrientationEnum determinerNouvelleOrientation(OrientationEnum orientationActuelle, DirectionEnum direction) throws OrientationInvalideException, DirectionInvalideException {
        // On détermine la nouvelle orientation en fonction de l'orientation actuelle et de la direction
        if(direction.equals(DirectionEnum.D)) {
            return switch (orientationActuelle) {
                case OrientationEnum.N -> OrientationEnum.E;
                case OrientationEnum.S -> OrientationEnum.O;
                case OrientationEnum.O -> OrientationEnum.N;
                case OrientationEnum.E -> OrientationEnum.S;
                case null, default -> throw new OrientationInvalideException(ORIENTATION_INVALIDE);
            };
        } else if (direction.equals(DirectionEnum.G)) {
            return switch (orientationActuelle) {
                case OrientationEnum.N -> OrientationEnum.O;
                case OrientationEnum.S -> OrientationEnum.E;
                case OrientationEnum.O -> OrientationEnum.S;
                case OrientationEnum.E -> OrientationEnum.N;
                case null, default -> throw new OrientationInvalideException(ORIENTATION_INVALIDE);
            };
        } else {
            throw new DirectionInvalideException(DIRECTION_INVALIDE);
        }
    }

    public static Position determinerDeltaPosition(OrientationEnum orientation) throws OrientationInvalideException {
        // On calcule la différence sur les coordonnées dans le référentiel en fonction de l'orientation
        return switch (orientation) {
            case OrientationEnum.N -> new Position(0,-1);
            case OrientationEnum.S -> new Position(0,1);
            case OrientationEnum.E -> new Position(1,0);
            case OrientationEnum.O -> new Position(-1,0);
            case null, default -> throw new OrientationInvalideException(ORIENTATION_INVALIDE);
        };
    }

    public static Position calculerNouvellePosition(Position position, OrientationEnum orientation) throws OrientationInvalideException {
        // On calcule la nouvelle position en fonction du delta obtenu
        Position delta = determinerDeltaPosition(orientation);
        return new Position(position.getX() + delta.getX(), position.getY() + delta.getY());
    }

    public static boolean estPositionDansLaCarte(Carte carte, Position position) {
        // On vérifie que les coordonnées sont bien sur la carte
        return position.getX() >= 0 && position.getY() >= 0 && position.getX() < carte.getLargeur() && position.getY() < carte.getHauteur();
    }

}
