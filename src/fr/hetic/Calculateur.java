package fr.hetic;

import java.io.*;
import java.util.Scanner;

public class Calculateur {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Calculateur <chemin_dossier>");
            return;
        }

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
        double resultat;
        switch (operator) {
            case "+":
                resultat = num1 + num2;
                break;
            case "-":
                resultat = num1 - num2;
                break;
            case "*":
                resultat = num1 * num2;
                break;
            case "/":
                resultat = num1 / num2;
                break;
            default:
                System.out.println("Opérateur inconnu");
                return 0;
        }

        return resultat;
    }

    public static void calculeFileOp(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");

                if (parts.length != 3) {
                    System.out.println("Ligne mal formée: " + line);
                    continue;
                }

                double num1;
                double num2;
                try {
                    num1 = Double.parseDouble(parts[0]);
                    num2 = Double.parseDouble(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Les deux premiers arguments doivent être des nombres: " + line);
                    continue;
                }
                String operator = parts[2];

                double resultat = calculer(num1, num2, operator);

                writeResult("src/fr/hetic/resultat.res", resultat);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable");
            e.printStackTrace();
        }
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