package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static Properties properties;

    static {
        try{
            FileInputStream file = new FileInputStream("config/config.properties");
            properties = new Properties();
            properties.load(file);
            file.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

}
