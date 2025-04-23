package googleMapsAPITest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import payloads.AddTestPayload;

public class AddTest {
	
	@BeforeMethod
	public void setUP() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
	}
	
	@Test
	public void addAPITest() {
		String endPoint = "/maps/api/place/add/json";
		
		Response response = given().
					header("Content-Type","application/json")
					.queryParam("key", "qaclick123")
					.body(AddTestPayload.addPlace())
				.when()
					.post(endPoint)
				.then()
					.extract().response();
				
		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200,"Status Code Does not match");
		
	}
	
}
