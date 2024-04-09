package fr.hetic;


import fr.hetic.Interface.Operation;
import fr.hetic.factory.OperationFactory;

import java.io.*;
import java.util.Scanner;

public class Calculateur {

    public static void main(String[] args) {

        String path = args[0];

        File[] files = new File(path).listFiles();

        if (files == null) {
            System.out.println("Le dossier spécifié est vide.");
            return;
        }

        for (File fichier : files) {
            if (fichier.isFile() && fichier.getName().endsWith(".op")) {
                processFile(fichier);
            }
        }
    }

    private static void processFile(File fichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String nameFile = fichier.getAbsolutePath().replace(".op", ".res");
            BufferedWriter writer = new BufferedWriter(new FileWriter(nameFile));

            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] elements = ligne.split(" ");
                try {
                    double num1 = Double.parseDouble(elements[0]);
                    double num2 = Double.parseDouble(elements[1]);
                    String operateur = elements[2];

                    double resultat = calculer(num1, num2, operateur);
                    writer.write(String.valueOf(resultat));
                    writer.newLine();
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    writer.write("N/A");
                    writer.newLine();
                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double calculer(Double num1, Double num2, String operator) {

        Operation operation = OperationFactory.getOperation(operator);

        assert operation != null;
        return operation.calcul(num1, num2);
    }

    public static void writeResult(String path, double result) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(result + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Impossible d'écrire le résultat dans le fichier");
            e.printStackTrace();
        }
    }
}