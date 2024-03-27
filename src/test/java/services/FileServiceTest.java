package services;

import exceptions.CarteNotFoundException;
import models.Carte;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

class FileServiceTest {

    @Test
    void lireFichierEntreeOKTest() throws CarteNotFoundException {
        Carte carte = FileService.lireFichierEntree("src/main/resources/input/donnees1.txt");

        Assertions.assertEquals(4, carte.getHauteur());
        Assertions.assertEquals(3, carte.getLargeur());
        Assertions.assertEquals(2, carte.getMontagnes().size());
        Assertions.assertEquals(2, carte.getTresors().size());
    }

    @Test
    void ecrireFichierDeSortieOKTest() throws CarteNotFoundException, IOException {
        Carte carte = FileService.lireFichierEntree("src/test/resources/input/testEcrireFichierDeSortieInput.txt");
        File attendu = new File("src/test/resources/input/testEcrireFichierDeSortieExpected.txt");

        FileService.ecrireFichierDeSortie("src/test/resources/output/testEcrireFichierDeSortieOutput.txt", carte);

        boolean estIdentique = FileUtils.contentEquals(attendu, new File("src/main/resources/output/testEcrireFichierDeSortieOutput.txt"));
        Assertions.assertTrue(estIdentique);
    }

}
