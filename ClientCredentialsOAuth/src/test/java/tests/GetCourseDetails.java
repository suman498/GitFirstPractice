package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import pojo.GetCourses;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class GetCourseDetails {
	
	
	@Test
	public void getCourseDetails() {
		String userToken = getToken();
		System.out.println("User Token Generated : "+userToken);
		
		String endPoint = "https://rahulshettyacademy.com/oauthapi/getCourseDetails";
		
		Response response = 
				given()
					.queryParam("access_token", userToken)
				.when()
					.get(endPoint)
				.then()
				.extract().response();
		
		
		System.out.println("Response from GetCourseDetails API\n"+response.asPrettyString());
		
		//Deserialization
		GetCourses gc = response.as(GetCourses.class);
		System.out.println("Name of Instructor : "+gc.getInstructor());
		System.out.println("Url Specified  : "+gc.getUrl());
		System.out.println("Courses available in mobile testing: "+ gc.getCourses().getMobile().get(0).getCourseTitle());
		System.out.println("Courses present in webautomation");
		for(int i=0;i<gc.getCourses().getWebAutomation().size();i++) {
			System.out.println(gc.getCourses().getWebAutomation().get(i).getCourseTitle());
		}
				
				
	}

	public String getToken() {
		String endPoint = "https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
		
		Map<String, String> formParameters = new HashMap<>();
		formParameters.put("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com");
		formParameters.put("client_secret", "erZOWM9g3UtwNRj340YYaK_W");
		formParameters.put("grant_type", "client_credentials");
		formParameters.put("scope", "trust");
		
		
		
		Response response = 
				given()
					.formParams(formParameters)
				.when()
					.post(endPoint)
				.then()
					.extract().response();
		
		System.out.println("Response from getToken API\n"+response.asPrettyString());
		String token = response.jsonPath().getString("access_token");
		
		
		return token;
	}
	
}
