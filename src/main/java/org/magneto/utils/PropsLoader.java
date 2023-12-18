package org.magneto.utils;

import java.io.IOException;
import java.util.Properties;

public class PropsLoader {

    private static final String APP_PROPERTIES = "app.properties";

    public static Properties loadProperties() {
        var properties = new Properties();
        try (var inputStream = ClassLoader.getSystemResourceAsStream(APP_PROPERTIES)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load property file.");
        }
        return properties;
    }
}
