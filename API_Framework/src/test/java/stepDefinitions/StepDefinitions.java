package stepDefinitions;

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
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;

public class StepDefinitions extends Utils {
	
	String endPoint;
	
	RequestSpecification request;
	ResponseSpecification resp;
	Response response;
	TestDataBuild testData = new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException{
		//sending name, address,language dynamically
		
		RestAssured.baseURI = getGlobalValue("baseURI");//Getting data from config file
		endPoint = getGlobalValue("addAPI_endPoint");//Getting data from config file
		
		request = given().spec(requestSpecification())//requestSpecification() comes from Utils Class return the requestSpecification required
				.body(testData.addAPIDataBuild(name,address,language));
		
		
		
	}
	@When("user calls AddPlaceAPI with post http method")
	public void user_calls_add_place_api_with_post_http_method() {
		resp = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
		
		response = request.when().post(endPoint)
				.then().spec(resp).extract().response();
		
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
}
