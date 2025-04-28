package googleMapsAPITest;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import payloads.AddTestPayload;

public class AddTestExternal {
	//JSON will be present in an external file
	@BeforeMethod
	public void setUP() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
	}
	
	@Test
	public void addAPITest() throws IOException {
		String endPoint = "/maps/api/place/add/json";
		
		Response response = given().
					header("Content-Type","application/json")
					.queryParam("key", "qaclick123")
					//string to byte --> byte to string
					.body(new String(Files.readAllBytes(Paths.get("jsonInput/addPlace.json"))))
				.when()
					.post(endPoint)
				.then()
					.extract().response();
				
		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200,"Status Code Does not match");
		
	}
}
