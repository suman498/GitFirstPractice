package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {
    private static Properties properties;

    static {
        try{
            FileInputStream file=new FileInputStream("config/config.properties");
            properties = new Properties();
            properties.load(file);
            file.close();

        }
        catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to load config file");

        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }

}
