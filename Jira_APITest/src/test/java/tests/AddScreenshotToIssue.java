package tests;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class AddScreenshotToIssue {
		
	@BeforeMethod
	public void setUP() {
		RestAssured.baseURI = "https://bluetitan498.atlassian.net";
	}
	
	@Test
	public void addScreenshotTest() {
		Map<String, String> headers = new HashMap<>();
		
		String userToken = "Basic Ymx1ZXRpdGFuNDk4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBIN1BDSHFXRDg4d2x6cEtnOC02Ml8z"
				+ "bGp6YWdSVXdoS3JISTZOSEhZVERjNk9QNkZGUHlfVDRDWGVGbWpLTEJpVkdjcU5ScFFhamt2Q0hrQVBEeWRKMmtt"
				+ "T3l1ejRsSGNHb0YyeTUxZFNNY01TQ2F5VVFOZGlINjNlaFFYN3RheGRzekFhTGFmY0ZjaXBxdlllcHExaHRYWWtTZ"
				+ "lBsaFRKTXpwVjNVTFJWSDA9MTc3MTkxODM=";
		
		String endPoint = "/rest/api/2/issue/10009/attachments";
		
		//adding headers to map
		headers.put("Authorization", userToken);
		headers.put("Accept", "application/json");
		headers.put("X-Atlassian-Token", "no-check");
		
		Response response = 
				given()
					.headers(headers)
					.multiPart("file", new File("inputs/screenshot.png")) // sending image file in API
				.when()
					.post(endPoint)
				.then()
					.extract().response();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		System.out.println(response.asPrettyString());
				
		
	}
}
