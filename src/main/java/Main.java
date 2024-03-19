import models.Carte;
import services.FileService;
import services.GameService;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello Carbon IT!");
        System.out.println("Bienvenue dans la carte aux trésors : la Madre de Dios");

        try {
            Carte carte = FileService.lireFichierEntree("src/main/resources/input/inputDonnees2.txt");
            GameService.lancerJeu();
            FileService.ecrireFichierDeSortie();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}