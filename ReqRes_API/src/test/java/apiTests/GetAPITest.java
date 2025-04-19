package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetAPITest {

    @Test
    public void getUserDetails(){
        //Defining Base URL
        RestAssured.baseURI = "https://reqres.in/";

        //Defining End-Points
        String endpoint="/api/users/2";

        //Sending GET Request
        Response response = RestAssured
                .given()
                .when()
                .get(endpoint);

        //Printing the Response
        System.out.println("Response Is : "+response.asPrettyString());

        assertEquals(response.getStatusCode(),
                200,
                "Status Code is not 200");


        //Validating from response
        String firstName = response.jsonPath().getString("data.first_name");
        assertEquals(firstName, "Janet", "First Name does not match");



    }
}
