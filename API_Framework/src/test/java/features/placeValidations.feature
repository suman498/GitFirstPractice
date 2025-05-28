Feature: Validating AddPlace API

Scenario Outline: Verify is place is successfully added using AddPlace API
	Given Add Place Payload with "<name>" "<address>" "<language>"
	When user calls AddPlaceAPI with post http method
	Then the API got response with success code 200
	And "status" in response code is "OK" 
	And "scope" in response code is "APP"
	
	Examples:
	|name        |address     |language|
	|GalliNo420  |Mumbai,India|English |