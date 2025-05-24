package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.LoginDetails;
import pojo.LoginRequest;
import pojo.OrderDetails;
import pojo.Orders;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class E2E_Test {
	static String token="";
	static String userID="";
	static String productID="";
	static String baseUri = "https://rahulshettyacademy.com";
	
		
	
	@Test
	public void endToEnd_Test() {
		Login();
		
		CreateProd();
		
		CreateOrder();
		
		DeleteProd();
		
		
		
	}

	private void DeleteProd() {
		RequestSpecification deleteProd = new RequestSpecBuilder()
				.setBaseUri(baseUri)
				.addHeader("authorization", token)
				.addPathParam("productId", productID)
				.build();
		
		String endpoint = "/api/ecom/product/delete-product/{productId}";//For setting up path parameters
		
		
		Response response = 
				given()
					.spec(deleteProd)
					//.log().all()
				.when()
					.delete(endpoint)
				.then()
					.extract().response();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		System.out.println(response.asPrettyString());
		
		
		
	}

	private void CreateOrder() {
		//Make Object for OrderDetails Class
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setCountry("India");
		orderDetails.setProductOrderedId(productID);
		
		//Make a List of all those Orders
		List<OrderDetails> listOrderDetails = new ArrayList<OrderDetails>();
		listOrderDetails.add(orderDetails);
		
		
		Orders orders = new Orders();
		orders.setOrders(listOrderDetails);//Pass the list in arguments
		
		RequestSpecification createOrder = new RequestSpecBuilder()
				.setBaseUri(baseUri)
				.addHeader("authorization", token)
				.setContentType(ContentType.JSON)
				.build();
		
		String endPoint = "/api/ecom/order/create-order";
		
		Response response = 
				given()
					//.log().all() -- to check all details sent
					.spec(createOrder)
					.body(orders)
				.when()
					.post(endPoint)
				.then()
					.extract().response();
		
		Assert.assertEquals(response.getStatusCode(), 201);
		
		System.out.println(response.asPrettyString());
		
				
		
		
		
		
		
	}

	private void CreateProd() {
		RequestSpecification createProdRes = new RequestSpecBuilder()
				.setBaseUri(baseUri)
				.addHeader("authorization", token)
				.build();
		
		String endPoint = "/api/ecom/product/add-product";
		
		Map <String,String> createProdFormParams = new HashMap<>();
		createProdFormParams.put("productName", "RUN !!");
		createProdFormParams.put("productAddedBy", userID);
		createProdFormParams.put("productCategory", "fashion");
		createProdFormParams.put("productSubCategory", "t-shirts");
		createProdFormParams.put("productPrice", "2000");
		createProdFormParams.put("productDescription", "Puma Originals");
		createProdFormParams.put("productFor", "Men");
		
		Response response =
				given()
					.spec(createProdRes)
					.formParams(createProdFormParams)
					.multiPart("productImage",new File("inputs/image.jpeg"))
				.when()
					.post(endPoint)
				.then()
					.extract().response();
				
		
		Assert.assertEquals(response.getStatusCode(),201);
		
		System.out.println(response.asPrettyString());
		productID = response.jsonPath().getString("productId");
		System.out.println("Product ID: "+productID);
		
		
	}
	
	

	private void Login() {
		
		String endPoint = "/api/ecom/auth/login";
		
		LoginRequest login =  new LoginRequest();
		login.setUserEmail("bluetitan498@gmail.com");
		login.setUserPassword("BlueTitan@498");
		
		RequestSpecification res = new RequestSpecBuilder()
				.setBaseUri(baseUri)
				.setContentType(ContentType.JSON)
				.build();
		
		Response response = 
				given()
					.spec(res)
					.body(login)	//Using Serialization POJO Class
				.when()
					.post(endPoint)
				.then()
					.extract().response();
		
		System.out.println(response.asPrettyString());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		LoginDetails details = response.as(LoginDetails.class);
		
		token = details.getToken();
		System.out.println("Token : "+token);
		userID = details.getUserId();
		System.out.println("UserID : "+userID);
		
		
	}
	
}
