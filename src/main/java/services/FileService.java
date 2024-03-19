package services;

import enums.OrientationEnum;
import exceptions.CarteNotFoundException;
import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileService {

    public static Carte lireFichierEntree(String filename) {
        Carte carte = new Carte();
        try {
            // FileReader pour lire le fichier contenant les données
            FileReader fileReader = new FileReader(filename);
            // BufferedReader qui lit le fileReader
            BufferedReader reader = new BufferedReader(fileReader);

            // Lecture de la ligne suivante
            String line = reader.readLine();

            // 1. Construction de la carte
            List<Montagne> montagnes = new ArrayList<>();
            List<Tresor> tresors = new ArrayList<>();

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
                        Aventurier aventurier = new Aventurier(OrientationEnum.valueOf(listeString[4]).getValeur(), new Position(Integer.parseInt(listeString[2]), Integer.parseInt(listeString[3])), null, Arrays.stream(listeString[4].split("")).toList());
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
        return carte;
    }

        public static void ecrireFichierDeSortie() throws IOException {
            String filename = "";
            // Création d'un fileWriter pour écrire dans un fichier
            FileWriter fileWriter = new FileWriter("src/main/resources/output/" + filename, false);
            // Création d'un bufferedWriter qui utilise le fileWriter
            BufferedWriter writer = new BufferedWriter(fileWriter);
        }

        private static void setCarteAvecObjets(Carte carte, List<Montagne> montagnes, List<Tresor> tresors) throws CarteNotFoundException {
            carte.setMontagnes(montagnes);
            carte.setTresors(tresors);
        }
    }

