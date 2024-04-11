package fr.test;

import fr.hetic.FileReader;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateurTest {

    @Test
    public void testAddition() {
        double num1 = 5;
        double num2 = 5;
        String operator = "+";

        double result = FileReader.calculer(num1, num2, operator);
        assertEquals(10, result);
    }

    @Test
    public void testSubstraction() {
        double num1 = 5;
        double num2 = 5;
        String operator = "-";

        double result = FileReader.calculer(num1, num2, operator);
        assertEquals(0, result);
    }

//    @Test
//    public void testNegativesubstraction() {
//        double num1 = 5;
//        double num2 = 10;
//        String operator = "-";
//
//        double result = FileReader.calculer(num1, num2, operator);
//        assertEquals(25, result);
//    }

    @Test
    public void testMultiplication() {
        double num1 = 5;
        double num2 = 5;
        String operator = "*";

        double result = FileReader.calculer(num1, num2, operator);
        assertEquals(25, result);
    }

    @Test
    public void testInvalidOperation() {
        double num1 = 5;
        double num2 = 5;
        String operator = "/";

        try {
            double result = FileReader.calculer(num1, num2, operator);
        } catch (IllegalArgumentException e) {
            assertEquals("Unexpected value: /", e.getMessage());

        }
    }

    @Test
    public void invalidFileLine() {
        File file = new File("src/test/resources/invalidFile.op");

        try {
            FileReader.processFile(file);
        } catch (IllegalArgumentException e) {
            assertEquals("Erreur lors du traitement de la ligne :  dans le fichier invalidFile.op", e.getMessage());
            FileReader.processFile(file);
        }
    }
}
