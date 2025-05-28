package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlacePojo;
import pojo.LocationPojo;

public class TestDataBuild {
	public AddPlacePojo addAPIDataBuild(String name,String address,String language) {
		List<String>lst;
		LocationPojo loc;
		AddPlacePojo obj;
		

		lst = new ArrayList<String>();
		lst.add("shoe park");
		lst.add("shop");
		
		
		loc = new LocationPojo();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		obj = new AddPlacePojo();
		obj.setAccuracy(50);
		obj.setName(name);
		obj.setAddress(address);
		obj.setPhone_number("(+91) 983 893 3937");
		obj.setWebsite("http://google.com");
		obj.setLanguage(language);
		obj.setTypes(lst);
		obj.setLocation(loc);
		
		return obj;
	}
}
