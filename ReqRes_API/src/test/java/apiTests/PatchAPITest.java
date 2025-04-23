package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.createUser;

import static io.restassured.RestAssured.given;

public class PatchAPITest {

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    public void patchUserTest(){
        createUser partialUpdate = new createUser();
        partialUpdate.setJob("Lead QA Engineer");
        String endPoint = "/api/users/822";

        Response response  =
                given()
                    .header("Content-Type","application/json")
                    .body(partialUpdate)
                .when()
                    .patch(endPoint)
                .then()
                    .extract().response();

        Assert.assertEquals(response.getStatusCode(),200,"Status Code should be 200");

        System.out.println(response.asPrettyString());

        String jobCheck = response.jsonPath().getString("job");
        Assert.assertEquals(jobCheck,"Lead QA Engineer","Job Not Matching after changing");

    }
}
