package Core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class Configuration {

    private String configFilePath = "src/main/resources/configuration.properties";
    private Properties properties;
    private static Configuration config;

    public Configuration() {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(configFilePath));
            properties = new Properties();
            properties.load(reader);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Configuration getInstance() {
        if (config == null) {
            config = new Configuration();
        }
        return config;
    }

    public String getProperty(String property) {
        String result = properties.getProperty(property);
        if (result != null) {
            return result;
        } else {
            throw new RuntimeException("Property not found at configuration.properties");
        }
    }

    public void setProperty(String property, String value) {
        properties.setProperty(property, value);
    }

    public boolean hasProperty(String property) {
        return properties.containsKey(property);
    }


}
