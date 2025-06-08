package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.*;
import pojo.AddPlacePojo;
import pojo.LocationPojo;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;

public class StepDefinitions extends Utils {
	
	String endPoint;
	
	RequestSpecification request;
	ResponseSpecification resp;
	Response response;
	TestDataBuild testData = new TestDataBuild();
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException{
		//sending name, address,language dynamically
		
		RestAssured.baseURI = getGlobalValue("baseURI");//Getting data from config file
		endPoint = getGlobalValue("addAPI_endPoint");//Getting data from config file
		
		request = given().spec(requestSpecification())//requestSpecification() comes from Utils Class return the requestSpecification required
				.body(testData.addAPIDataBuild(name,address,language));
		
		
		
	}
	@When("user calls {string} with {string} http method")
	public void user_calls_with_http_method(String resource, String method) {
		resp = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		if(method.equalsIgnoreCase("GET")) {
			response = request.when().get(resourceAPI.getResource())
					.then().spec(resp).extract().response();
		}
		else if(method.equalsIgnoreCase("POST")) {
			response = request.when().post(resourceAPI.getResource())
					.then().spec(resp).extract().response();
		}
		else {
			response = request.when().delete(resourceAPI.getResource())
					.then().spec(resp).extract().response();
		}
		
		
		
		System.out.println(response.asPrettyString());
				
		
	}
	@Then("the API got response with success code {int}")
	public void the_api_got_response_with_success_code(Integer ExpectedValue) {
	    Assert.assertEquals(response.getStatusCode(), ExpectedValue);
	    
	}
	
	@Then("{string} in response code is {string}")
	public void in_response_code_is(String string, String ExpectedValue) {
	    Assert.assertEquals(response.jsonPath().getString(string), ExpectedValue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String apiName) throws FileNotFoundException {
		//fetching place_id using utils class
		place_id = getJsonPath(response,"place_id");
		
		//forming the request
		request = given().spec(requestSpecification())
					.queryParam("place_id", place_id);
		
		//running API using GET method
		user_calls_with_http_method(apiName,"GET");
		
		//get value in name from response
		String actualName = getJsonPath(response,"name");
		
		//Assertion Starts
		Assert.assertEquals(actualName, expectedName);
		
		
		
	}
	
	@Given("DeletePlaceAPI Payload")
	public void delete_place_api_payload() throws FileNotFoundException {
		request = given().spec(requestSpecification())
					.body(testData.deletePlaceDataBuild(place_id));
	}
	
	
}
