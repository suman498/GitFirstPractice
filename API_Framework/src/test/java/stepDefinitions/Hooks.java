package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlaceAPICheck")
	public void beforeDeletePlaceAPIRun() throws IOException {
		//this will run first in case DeletePlaceAPI is run independently
		StepDefinitions m = new StepDefinitions();
		if(StepDefinitions.place_id==null) {
			m.add_place_payload_with("Ravi Shastri", "Mumbai", "hindi");
			m.user_calls_with_http_method("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("Ravi Shastri", "getPlaceAPI");
		}
	}
}
