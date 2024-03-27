import exceptions.CarteNotFoundException;
import exceptions.DirectionInvalideException;
import exceptions.OrientationInvalideException;
import exceptions.PositionInvalideException;
import models.Carte;
import services.FileService;
import services.GameService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CarteAuxTresorsMain {

    private static final Logger logger = Logger.getLogger(CarteAuxTresorsMain.class.getName());

    public static void main(String[] args) throws DirectionInvalideException, OrientationInvalideException, CarteNotFoundException, PositionInvalideException {

        logger.log(Level.INFO, "Hello Carbon IT!");
        logger.log(Level.INFO, "Bienvenue dans la carte aux trésors : la Madre de Dios ! Le jeu va commencer...");

        try {
            if(args.length == 2) {
                String repertoireInput = "src/main/resources/input/donnees2.txt";
                String repertoireOutput = "src/main/resources/output/donnees2.txt";
                Carte carte = FileService.lireFichierEntree(repertoireInput);
                GameService.lancerJeu(carte);
                FileService.ecrireFichierDeSortie(repertoireOutput, carte);
                logger.log(Level.INFO, "Votre fichier de sortie est disponible, merci d'avoir joué !");
            }
            else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}