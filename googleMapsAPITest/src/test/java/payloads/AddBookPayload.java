package payloads;

public class AddBookPayload {
	public static String addBook(String isbn,String aisle) {
		String s1 = "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ "";
		
		return s1;
	}
	
	public static String delBook(String isbn,String aisle) {
		String s2 = "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+isbn+aisle+"\"\r\n"
				+ " \r\n"
				+ "} \r\n"
				+ "";
		
		//ystem.out.println(s2);
		
		return s2;
	}
}
