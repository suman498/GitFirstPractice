package payloads;

public class AddIssuePayload {
	
	public static String getJson() {
		
		String json = "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Create issue using RestAssured\",\r\n"
				+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API with help of RestAssured\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}";
		
		return json;
		
	}
}
