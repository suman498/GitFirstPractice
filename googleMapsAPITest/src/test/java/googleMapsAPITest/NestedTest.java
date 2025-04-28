package googleMapsAPITest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import payloads.AddTestPayload;

import static io.restassured.RestAssured.given;

public class NestedTest {
	
	@BeforeMethod
	public void setUP() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
	}
	
	@Test
	public void nestedAPITest() {
		
		String endPointADD = "/maps/api/place/add/json";
		String endPointPUT = "/maps/api/place/update/json";
		String endPointGET = "/maps/api/place/get/json";
		String address = "90 winter walk, USA";
		
		
		//First Add API(POST) will run then we update address using the place ID(PUT) , then we use GET API(GET)
		//ADD -- POST Method
		Response response = 
				given()
					.header("Content-Type","application/json")
					.queryParam("key", "qaclick123")
					.body(AddTestPayload.addPlace())
				.when()
					.post(endPointADD)
				.then()
					.assertThat()
					.statusCode(200)
					.extract().response();
		
		//Print Response
		System.out.println(response.asPrettyString());
		
		//ExtractPlace ID
		String placeID = response.jsonPath().getString("place_id");
		System.out.println(placeID);
		
		
		//PUT Method
		Response putResponse = 
				given()
				.header("Content-Type","application/json")
				.queryParam("key", "qaclick123")
				.body("{\r\n"
						+ "\"place_id\":\""+placeID+"\",\r\n"
						+ "\"address\":\""+address+"\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n"
						+ "}\r\n"
						+ "")
				.when()
					.put(endPointPUT)
				.then()
					.assertThat()
					.statusCode(200)
					.extract().response();
		
		Assert.assertEquals(putResponse.jsonPath().getString("msg"), "Address successfully updated");
		System.out.println(putResponse.asPrettyString());
		
		
		
		//GET Method
		Response getResponse = 
				given()
					.queryParam("place_id", placeID)
					.queryParam("key", "qaclick123")
				.when()
					.get(endPointGET)
				.then()
					.extract().response();
		
		Assert.assertEquals(getResponse.jsonPath().getString("address"),address);
		System.out.println(getResponse.asPrettyString());
				
				
	}
}
