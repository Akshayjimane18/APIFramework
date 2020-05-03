Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "addPlaceAPI" with "POST" http Request
	Then The API calls is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id maps to "<name>" using "getPlaceAPI"
	
	
	
	
Examples:
	|name   |language|address           |
	|AAhouse|English |World cross center|
#	|BBhouse|Spanish |Sea cross center|

@DeletePlace
Scenario: Verify if delete functinoality is working
	
	Given DeletePlace Payload
	When User calls "deletePlaceAPI" with "POST" http Request
	Then The API calls is success with status code 200
	And "status" in response body is "OK"
	