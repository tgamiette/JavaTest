package fr.hetic;

import fr.hetic.Interface.Operation;
import fr.hetic.factory.OperationFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Calculateur {
    private static final String PATH = "/Users/dev-tgamiette/Documents/GitHub/Hetic/JavaTest/src/main/resources";

    public static void main(String[] args) {
        try (Stream<Path> paths = Files.walk(Paths.get(PATH)).filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".op"))) {
            paths.forEach(path -> processFile(path.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processFile(File fichier) {
        String nameFile = fichier.getAbsolutePath().replace(".op", ".res");

        try (Stream<String> lines = Files.lines(fichier.toPath())) {
            lines.forEach(line -> {
                try {
                    String[] elements = line.split(" ");

                    if (elements.length != 3) {
                        throw new Exception("Le fichier " + fichier.getName() + " n'est pas correctement formaté");
                    }

                    double num1 = Double.parseDouble(elements[0]);
                    double num2 = Double.parseDouble(elements[1]);
                    String operator = elements[2];
                    double result = calculer(num1, num2, operator);
                    writeResult(nameFile, result);
                } catch (Exception e) {
                    System.out.println("Erreur lors du traitement de la ligne : " + line + " dans le fichier " + fichier.getName());
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.out.println("Impossible de lire le fichier " + fichier.getName());
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