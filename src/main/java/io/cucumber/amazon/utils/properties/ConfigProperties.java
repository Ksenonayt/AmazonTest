package io.cucumber.amazon.utils.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private static FileInputStream fileInputStream;
    private static Properties PROPERTIES;
    private static String PROPERTY_PATH = "src/main/resources/config.properties";

    static {
        try {
            fileInputStream = new FileInputStream(PROPERTY_PATH);
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}