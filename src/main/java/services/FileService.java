package services;

import enums.DirectionEnum;
import enums.OrientationEnum;
import exceptions.CarteNotFoundException;
import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileService {


    public static Carte lireFichierEntree(String filename) throws CarteNotFoundException {
        Carte carte = new Carte();
        // 1. Construction de la carte
        List<Montagne> montagnes = new ArrayList<>();
        List<Tresor> tresors = new ArrayList<>();
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
                        System.out.println("Il s'agit de la carte");
                        carte = new Carte(Integer.parseInt(listeString[1]), Integer.parseInt(listeString[2]));
                        break;
                    case "M":
                        System.out.println("Il s'agit d'une montagne");
                        Montagne nouvelleMontagne = new Montagne(new Position(Integer.parseInt(listeString[1]), Integer.parseInt(listeString[2])));
                        montagnes.add(nouvelleMontagne);
                        break;
                    case "T":
                        System.out.println("Il s'agit d'un trésor");
                        Tresor nouveauTresor = new Tresor(new Position(Integer.parseInt(listeString[1]), Integer.parseInt(listeString[2])), Integer.parseInt(listeString[3]));
                        tresors.add(nouveauTresor);
                        break;
                    case "A":
                        System.out.println("Il s'agit d'un aventurier");
                        List<DirectionEnum> directions = getDirectionsAventurier(listeString[5].split(""));
                        aventurier = new Aventurier(OrientationEnum.valueOf(listeString[4]), new Position(Integer.parseInt(listeString[2]), Integer.parseInt(listeString[3])), null, directions);
                        break;
                    default:
                        break;
                }
                // Lecture de la ligne suivante
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        setCarteAvecObjets(carte, montagnes, tresors, aventurier);
        return carte;
    }

        public static void ecrireFichierDeSortie(String outputFilename, Carte carte) throws IOException {
            try {
            // Création d'un fileWriter pour écrire dans un fichier
                FileWriter fileWriter = new FileWriter(outputFilename, false);
                fileWriter.write(carte.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void setCarteAvecObjets(Carte carte, List<Montagne> montagnes, List<Tresor> tresors, Aventurier aventurier) throws CarteNotFoundException {
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
            for(String direction : directionsString) {
                directionsEnum.add(DirectionEnum.valueOf(direction));
            }
            return directionsEnum;
        }
    }

