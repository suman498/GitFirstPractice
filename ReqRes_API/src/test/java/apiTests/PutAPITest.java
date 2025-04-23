package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.createUser;

import static io.restassured.RestAssured.given;

public class PutAPITest {

    @BeforeMethod
    public void setUP(){
        RestAssured.baseURI = "https://reqres.in/" ;
    }

    @Test
    public void putUserTest(){
        createUser updatedUser = new createUser("Suraj Rathi","Developer");
        String endPoint = "/api/users/822";

        Response response = given()
                .header("Content-Type","application/json")
                .body(updatedUser)
                .when()
                    .put(endPoint)
                .then()
                    .extract().response();

        Assert.assertEquals(response.getStatusCode(),200,"Status Code should be 200");

        System.out.println(response.asPrettyString());

        String nameCheck = response.jsonPath().getString("name");
        Assert.assertEquals(nameCheck,"Suraj Rathi","Name Not Matching");


    }
}
