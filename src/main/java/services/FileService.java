package services;

import enums.DirectionEnum;
import enums.OrientationEnum;
import exceptions.CarteNotFoundException;
import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileService {

private FileService(){
}

    public static Carte lireFichierEntree(String filename) throws CarteNotFoundException {
        Carte carte = new Carte();
        // Construction de la carte
        List<Montagne> montagnes = new ArrayList<>();
        List<Tresor> tresors = new LinkedList<>();
        Aventurier aventurier = null;
        try {
            // FileReader pour lire le fichier contenant les données
            FileReader fileReader = new FileReader(filename);
            // BufferedReader qui lit le fileReader
            BufferedReader reader = new BufferedReader(fileReader);
            // Lecture de la ligne suivante
            String line = reader.readLine();

            // Si la ligne n'est pas null
            while (line != null) {
                // Concaténation de la ligne
                String[] listeString = line.split(" - ");
                // Analyse de la première lettre de la ligne
                switch (listeString[0]) {
                    case "C":
                        // Si C, on créé la carte
                        carte = new Carte(Integer.parseInt(listeString[1]), Integer.parseInt(listeString[2]));
                        break;
                    case "M":
                        // Si M, on place la montagne en l'ajoutant dans la carte
                        Montagne nouvelleMontagne = new Montagne(new Position(Integer.parseInt(listeString[1]), Integer.parseInt(listeString[2])));
                        montagnes.add(nouvelleMontagne);
                        break;
                    case "T":
                        // Si T, on place les trésors en les ajoutant dans la carte
                        Tresor nouveauTresor = new Tresor(new Position(Integer.parseInt(listeString[1]), Integer.parseInt(listeString[2])), Integer.parseInt(listeString[3]));
                        tresors.add(nouveauTresor);
                        break;
                    case "A":
                        // Si A, on place l'aventurier sur la carte
                        List<DirectionEnum> directions = getDirectionsAventurier(listeString[5].split(""));
                        aventurier = new Aventurier(listeString[1], OrientationEnum.valueOf(listeString[4]), new Position(Integer.parseInt(listeString[2]), Integer.parseInt(listeString[3])), new ArrayList<>(), directions);
                        break;
                    default:
                        break;
                }
                // Lecture de la ligne suivante
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // On set la carte avec les objets
        setCarteAvecObjets(carte, montagnes, tresors, aventurier);
        return carte;
    }

        public static void ecrireFichierDeSortie(String outputFilename, Carte carte) throws IOException {
            try {
                // Création d'un fileWriter pour écrire dans un fichier
                FileWriter fileWriter = new FileWriter(outputFilename, false);
                fileWriter.write(creerContenuFichierSortie(carte));
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void setCarteAvecObjets(Carte carte, List<Montagne> montagnes, List<Tresor> tresors, Aventurier aventurier) throws CarteNotFoundException {
            // Si la carte n'est pas null, on lui set ses objets
            if (carte != null) {
                carte.setMontagnes(montagnes);
                carte.setTresors(tresors);
                carte.setAventurier(aventurier);
            } else {
                throw new CarteNotFoundException("Les données de la carte n'existent pas");
            }
        }

        private static List<DirectionEnum> getDirectionsAventurier(String[] directionsString) {
            List<DirectionEnum> directionsEnum = new ArrayList<>();
            // On convertit la liste de String en DirectionEnum
            for(String direction : directionsString) {
                directionsEnum.add(DirectionEnum.valueOf(direction));
            }
            return directionsEnum;
        }

        private static String creerContenuFichierSortie(Carte carte) {
            // Ajout de la ligne Carte
            String contenu = String.format("C - %d - %d", carte.getLargeur(), carte.getHauteur());
            // Création des lignes Montagne
            for(Montagne montagne : carte.getMontagnes()) {
                Position posMontagne = montagne.getPosition();
                contenu = String.format("%s%nM - %d - %d", contenu, posMontagne.getX(), posMontagne.getY());
            }
            // Création des lignes Trésor
            for(Tresor tresor : carte.getTresors()) {
                Position posTresor = tresor.getPosition();
                contenu = String.format("%s%nT - %d - %d - %d", contenu, posTresor.getX(), posTresor.getY(), tresor.getNombre());
            }
            Aventurier aventurier = carte.getAventurier();
            if(aventurier != null) {
                // Création de la ligne Aventurier
                Position posAventurier = aventurier.getPosition();
                contenu = String.format("%s%nA - %s - %d - %d - %s - %d", contenu, aventurier.getNom(), posAventurier.getX(), posAventurier.getY(), aventurier.getOrientation(), aventurier.getTresors() == null ? 0 : aventurier.getTresors().size());
            }
            return contenu;
        }
    }

