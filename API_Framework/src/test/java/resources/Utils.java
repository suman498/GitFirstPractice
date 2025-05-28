package resources;

import static io.restassured.RestAssured.baseURI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public RequestSpecification requestSpecification() throws FileNotFoundException {
		PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));
		RequestSpecification req = new RequestSpecBuilder()
				.setBaseUri(baseURI)
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log ))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				
				.setContentType(ContentType.JSON).build();
		
		return req;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream("config/config.properties");
		properties.load(file);
		file.close();
		
		return properties.getProperty(key);
	}
	
}
