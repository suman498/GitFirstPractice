package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import payloads.AddIssuePayload;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class AdditionOfIssue {
	
	@BeforeMethod
	public void setUP() {
		RestAssured.baseURI = "https://bluetitan498.atlassian.net";
	}
	
	
	@Test
	public void addIssue() {
		Map <String, String> headers= new HashMap<>();
		String userToken = "Basic Ymx1ZXRpdGFuNDk4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBIN1BDSHFXRDg4d2x6cEtnOC02Ml8z"
				+ "bGp6YWdSVXdoS3JISTZOSEhZVERjNk9QNkZGUHlfVDRDWGVGbWpLTEJpVkdjcU5ScFFhamt2Q0hrQVBEeWRKMmtt"
				+ "T3l1ejRsSGNHb0YyeTUxZFNNY01TQ2F5VVFOZGlINjNlaFFYN3RheGRzekFhTGFmY0ZjaXBxdlllcHExaHRYWWtTZ"
				+ "lBsaFRKTXpwVjNVTFJWSDA9MTc3MTkxODM=";
		
		String endPoint = "/rest/api/2/issue";
		
		
		
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		headers.put("Authorization", userToken);
		
		
		Response response = 
				given()
					.headers(headers)
					.body(AddIssuePayload.getJson())
				.when()
					.post(endPoint)
				.then()
					.extract().response();
		
		Assert.assertEquals(response.getStatusCode(), 201);
		
		System.out.println(response.asPrettyString());
		
				
				
				
	}
	
}
