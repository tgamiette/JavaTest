package fr.hetic;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String[] args) throws IOException {
        String readerType = ConfigReader.getProperties("reader.type");
        Reader reader;

        if ("FILE".equals(readerType)) {
            reader = new fr.hetic.FileReader();
        } else if ("JDBC".equals(readerType)) {
            reader = new JDBCReader();
        } else {
            throw new IllegalArgumentException("Invalid reader type: " + readerType);
        }

        reader.read();
    }
}