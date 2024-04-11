package fr.hetic;

import fr.hetic.Interface.Operation;
import fr.hetic.factory.OperationFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class JDBCReader extends Reader {
    private static final String DB_URL = "jdbc:postgresql://SG-hetic-mt4-java-5275-pgsql-master.servers.mongodirector.com:5432/TP";
    private static final String USER = "etudiant";
    private static final String PASS = "MT4@hetic2324";

    public static double calculer(Double num1, Double num2, String operator) {
        Operation operation = OperationFactory.getOperation(operator);

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

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement stmt = conn.createStatement();

            String sql = "SELECT f.NOM, l.PARAM1, l.PARAM2, l.OPERATEUR " +
                    "FROM FICHIER f " +
                    "JOIN LIGNE l ON f.ID = l.FICHIER_ID " +
                    "WHERE f.TYPE = 'OP'";

            try (ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    String nameFile = rs.getString("NOM");
                    double num1 = rs.getDouble("PARAM1");
                    double num2 = rs.getDouble("PARAM2");
                    String operator = rs.getString("OPERATEUR");

                    try {
                        double result = calculer(num1, num2, operator);
                        writeResult(nameFile, result);
                    } catch (Exception e) {
                        System.out.println("Erreur lors du traitement de la ligne : " + num1 + " " + num2 + " " + operator + " dans le fichier " + nameFile);
//                    e.printStackTrace();
                    }

                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return off;
    }

    @Override
    public void close() throws IOException {
    }
}