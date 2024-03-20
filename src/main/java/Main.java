import exceptions.CarteNotFoundException;
import exceptions.DirectionInvalideException;
import exceptions.OrientationInvalideException;
import exceptions.PositionInvalideException;
import models.Carte;
import services.FileService;
import services.GameService;

public class Main {
    public static void main(String[] args) throws DirectionInvalideException, OrientationInvalideException, CarteNotFoundException, PositionInvalideException {

        System.out.println("Hello Carbon IT!");
        System.out.println("Bienvenue dans la carte aux trésors : la Madre de Dios");

        try {
            String repertoireInput = "src/main/resources/input/";
            String repertoireOutput = "src/main/resources/output/";
            String filename = "donnees2.txt";
            Carte carte = FileService.lireFichierEntree(repertoireInput + filename);
            GameService.lancerJeu(carte);
            FileService.ecrireFichierDeSortie(repertoireOutput + filename, carte);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (DirectionInvalideException e) {
            throw new DirectionInvalideException();
        } catch (OrientationInvalideException e) {
            throw new OrientationInvalideException();
        } catch (CarteNotFoundException e) {
            throw new CarteNotFoundException("Les données de la carte n'existent pas");
        } catch (PositionInvalideException e) {
            throw new PositionInvalideException();
        }

    }
}