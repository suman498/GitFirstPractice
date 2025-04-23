package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.createUser;

import static io.restassured.RestAssured.given;

public class PostAPITest {

    @BeforeMethod
    public void setUP(){
        RestAssured.baseURI="https://reqres.in/";

    }

    @Test
    public void createUserTest(){
        //Call to POJO Class
        createUser user = new createUser("Ravi Shastri","Tester");
        String endPoint="/api/users";

        Response response = given()
                .header("Content-Type","application/json")
                .body(user)
                .when()
                    .post(endPoint)
                .then()
                    .extract().response();

        Assert.assertEquals(response.getStatusCode(),201,"Status Code is not 201");

        System.out.println(response.asPrettyString());

        //Assertions
        String name = response.jsonPath().getString("name");
        Assert.assertEquals(name,"Ravi Shastri","Name Not Matching");


    }
}
