package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {
    static Properties properties;

    static{
        try{
            FileInputStream file = new FileInputStream("config/config.properties");
            properties = new Properties();
            properties.load(file);
            file.close();

        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Unable to read config file");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);


    }
}
