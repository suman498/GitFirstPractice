package googleMapsAPITest;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	/*
	 * 1. Print No of courses returned by API
	 * 
	 * 2.Print Purchase Amount
	 * 
	 * 3. Print Title of the first course
	 * 
	 * 4. Print All course titles and their respective Prices
	 * 
	 * 5. Print no of copies sold by RPA Course
	 * 
	 * 6. Verify if Sum of all Course prices matches with Purchase Amount
	 */
	
	
		@Test
		public void parseJson() {
			JsonPath js1 = new JsonPath(payloads.CoursePrice.priceOfCourse());
			
			//Print No of courses returned by API
			int size = js1.getList("courses").size();
			System.out.println("No of Courses : "+size);
			
			
			//Print Purchase Amount
			System.out.println("Purchase Amount : "+js1.getInt("dashboard.purchaseAmount"));
			
			//Print Title of the first course
			System.out.println("Title of First Course : "+js1.getString("courses[0].title"));
			
			//Print All course titles and their respective Prices
			for(int i=0;i<js1.getInt("courses.size()");i++) {
				System.out.println("Course Title : "+js1.getString("courses["+i+"].title"));
				System.out.println("Course Price : "+js1.getString("courses["+i+"].price"));
			}
			
			//Print no of copies sold by RPA Course
			for(int i=0;i<js1.getInt("courses.size()");i++) {
				if(js1.getString("courses["+i+"].title").equals("RPA")) {
					System.out.println("Copies sold by RPA Course : "+js1.getInt("courses["+i+"].copies"));
					break;
				}
			}
			
			//Verify if Sum of all Course prices matches with Purchase Amount
			int calTotalPrice = 0;
			for(int i=0;i<js1.getInt("courses.size()");i++) {
				int price = js1.getInt("courses["+i+"].price");
				int copies = js1.getInt("courses["+i+"].copies");
				
				calTotalPrice+=price*copies;
			}
			//System.out.println(calTotalPrice);
			
			int totalPrice = js1.getInt("dashboard.purchaseAmount");
			
			if(calTotalPrice==totalPrice) {
				System.out.println("Calculated Total Price = "+calTotalPrice+" matches with Given Total Price = "+totalPrice);
			}
			
		}
		
}
