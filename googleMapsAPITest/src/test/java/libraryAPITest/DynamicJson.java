package libraryAPITest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import payloads.AddBookPayload;

import static io.restassured.RestAssured.given;

public class DynamicJson {
	
	@DataProvider(name = "bookData")
	public Object[][] data() {
		 
		return new Object [][] {
			
								{"sums02","0909"},
								{"east46","8009"},
								{"west91","7009"}
								
								};
	}
		
	@BeforeMethod
	public void setUP() {
		RestAssured.baseURI = "http://216.10.245.166";
	}
	
	
	@Test(dataProvider = "bookData")
	public void addBook(String isbn,String aisle) {
		String endPoint = "Library/Addbook.php";
		
		Response response =
				given()
					.header("Content-Type","application/json")
					.body(AddBookPayload.addBook(isbn,aisle))
				.when()
					.post(endPoint)
				.then()
					.extract().response();
		
		Assert.assertEquals(response.jsonPath().getString("Msg"), "successfully added","Message does not match during Addition");
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(dataProvider = "bookData")
	public void delBook(String isbn,String aisle) {
		String endPoint = "Library/DeleteBook.php";
		
		Response response = 
				given()
					.header("Content-Type","application/json")
					.body(AddBookPayload.delBook(isbn, aisle))
				.when()
					.post(endPoint)
				.then()
					.extract().response();
		
		Assert.assertEquals(response.jsonPath().getString("msg"), "book is successfully deleted","Message does not match during Deletion");
		
		System.out.println(response.asPrettyString());
				
	}
}
