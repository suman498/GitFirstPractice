package googleMapsAPI_Serialization;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlacePojo;
import pojo.LocationPojo;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class AddPlaceAPI {
	
	
	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
	}
	
	
	
	@Test
	public static void addPlaceSerialization() {
		String endPoint = "/maps/api/place/add/json";
		List<String>lst = new ArrayList<String>();
		lst.add("shoe park");
		lst.add("shop");
		
		
		LocationPojo loc = new LocationPojo();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		AddPlacePojo obj = new AddPlacePojo();
		obj.setAccuracy(50);
		obj.setName("Frontline house");
		obj.setAddress("29, side layout, cohen 09");
		obj.setPhone_number("(+91) 983 893 3937");
		obj.setWebsite("http://google.com");
		obj.setLanguage("French-IN");
		obj.setTypes(lst);
		obj.setLocation(loc);

		
		
		Response response = 
				given()
					.header("Content-Type","application/json")
					.queryParam("key", "qaclick123")
					.body(obj)
				.when()
					.post(endPoint)
				.then()
					.extract().response();
		
		System.out.println(response.asPrettyString());
		
	}
}
