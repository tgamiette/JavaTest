package fr.hetic;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final String CONFIG_FILE = "/application.properties";

    public static String getProperties(String key) {

        Properties properties = new Properties();
        try (InputStream inputStream = ConfigReader.class.getResourceAsStream(CONFIG_FILE)) {

            System.out.println("inputStream: " + inputStream);
            properties.load(inputStream);

            return properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}