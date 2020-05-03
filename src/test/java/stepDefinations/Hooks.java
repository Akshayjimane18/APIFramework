package stepDefinations;

import cucumber.api.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		StepDefination sd = new StepDefination();

		if (StepDefination.place_id == null) {
			sd.add_Place_Payload_with("Mane", "Marathi", "Mumbai");
			sd.user_calls_with_Post_http_Request("addPlaceAPI", "POST");
			sd.verify_place_id_maps_to_using("Mane", "getPlaceAPI");
		}

	}

}
