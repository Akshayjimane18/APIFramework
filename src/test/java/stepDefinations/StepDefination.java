package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {

	ResponseSpecification respspecification;
	RequestSpecification rsSpecification;
	Response response;
	JsonPath js;
	static String place_id;
	TestDataBuild data = new TestDataBuild();

	@Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_Place_Payload_with(String name, String language, String address) throws Throwable {
		rsSpecification = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}

	@When("^User calls \"([^\"]*)\" with \"([^\"]*)\" http Request$")
	public void user_calls_with_Post_http_Request(String resource, String httpMethod) throws Throwable {

		APIResources resourceAPI = APIResources.valueOf(resource);
		
		respspecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (httpMethod.equalsIgnoreCase("POST"))
			response = rsSpecification.when().post(resourceAPI.getResources());
		else if (httpMethod.equalsIgnoreCase("GET"))
			response = rsSpecification.when().get(resourceAPI.getResources());

	}

	@Then("^The API calls is success with status code (\\d+)$")
	public void the_API_calls_is_success_with_status_code(int arg1) throws Throwable {
		assertEquals(200, response.getStatusCode());

	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String keyValue, String expectedVale) throws Throwable {
		assertEquals(expectedVale, getJsonPath(response, keyValue));
	}

	@Then("^verify place_id maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_id_maps_to_using(String expectedName, String resource) throws Throwable {

		place_id = getJsonPath(response, "place_id");
		rsSpecification = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_Post_http_Request(resource,"GET");
		
		String actualName = getJsonPath(response, "name");
		assertEquals(expectedName, actualName);
		
		
	}
	
	@Given("^DeletePlace Payload$")
	public void deleteplace_Payload() throws Throwable {

		rsSpecification = given().spec(requestSpecification())
		.body(data.deletePlacePayload(place_id));
	}

}
