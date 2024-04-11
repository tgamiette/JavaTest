package fr.test;

import fr.hetic.Calculateur;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateurTest {

    @Test
    public  void testAddition () {
        double num1 = 5;
        double num2 = 5;
        String operator = "+";

        double result = Calculateur.calculer(num1, num2, operator);
        assertEquals(10, result);
    }

    @Test
    public  void testSubstraction () {
        double num1 = 5;
        double num2 = 5;
        String operator = "-";

        double result = Calculateur.calculer(num1, num2, operator);
        assertEquals(0, result);
    }

    @Test
public  void testNegativesubstraction() {
          double num1 = 5;
            double num2 = 10;
            String operator = "-";

        double result = Calculateur.calculer(num1, num2, operator);
        assertEquals(25, result);
    }

    @Test
    public  void testMultiplication () {
        double num1 = 5;
        double num2 = 5;
        String operator = "*";

        double result = Calculateur.calculer(num1, num2, operator);
        assertEquals(25, result);
    }

    @Test
    public void testInvalidOperation() {
        double num1 = 5;
        double num2 = 5;
        String operator = "/";

        try {
            double result = Calculateur.calculer(num1, num2, operator);
        } catch (IllegalArgumentException e) {
            assertEquals("Unexpected value: /", e.getMessage());

        }
    }

    public void invalidFileLine() {
        File file = new File("src/test/resources/invalidFile.op");

        try {
            Calculateur.processFile(file);
        } catch (IllegalArgumentException e) {
            assertEquals("Erreur lors du traitement de la ligne :  dans le fichier invalidFile.op", e.getMessage());
        Calculateur.processFile(file);


    }

}
