Feature: Validating Place APIs

@AddPlaceAPICheck
Scenario Outline: Verify is place is successfully added using AddPlace API
	Given Add Place Payload with "<name>" "<address>" "<language>"
	When user calls "AddPlaceAPI" with "POST" http method
	Then the API got response with success code 200
	And "status" in response code is "OK" 
	And "scope" in response code is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	
	Examples:
	|name					|address			|language	|
	|GalliNo420		|Mumbai,India	|English 	|
	|JV House			|Chennai,India|Hindi		|

@DeletePlaceAPICheck
Scenario: Verify funtionality of DeletePlaceAPI
	Given DeletePlaceAPI Payload
	When user calls "deletePlaceAPI" with "POST" http method
	Then the API got response with success code 200
	And "status" in response code is "OK"