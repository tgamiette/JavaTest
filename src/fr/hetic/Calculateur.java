package fr.hetic;


import fr.hetic.Interface.Operation;
import fr.hetic.factory.OperationFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Calculateur {
    private static final String PATH = "/Users/dev-tgamiette/Documents/GitHub/Hetic/JavaTest/src/Ressource";

    public static void main(String[] args) {
        try (Stream<Path> paths = Files.walk(Paths.get(PATH))
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".op"))) {
            paths.forEach(path -> processFile(path.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processFile(File fichier) {
        String nameFile = fichier.getAbsolutePath().replace(".op", ".res");

        try (Stream<String> lines = Files.lines(fichier.toPath())) {
            lines.forEach(line -> {
                String[] elements = line.split(" ");
                double result = calculer(Double.parseDouble(elements[0]), Double.parseDouble(elements[2]), elements[1]);
                writeResult(nameFile, result);
            });
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