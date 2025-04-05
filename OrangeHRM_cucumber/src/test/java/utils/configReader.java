package utils;

import java.io.FileInputStream;
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
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Config File not found");
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }


}
