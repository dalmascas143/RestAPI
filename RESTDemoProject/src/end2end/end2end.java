package end2end;

import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import files.reusemethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class end2end {
	
	
	@Test(dataProvider="Booksdata")
	public void testing(String setnewAddress) {
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		//Adds data
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(payload.AddPlaceDynamic())
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200)
				.extract().response().asString();
		JsonPath js = reusemethods.convertjson(response);
		String stats = js.get("status");
		Assert.assertEquals(stats, "OK");
		String placeId = js.getString("place_id");
		
		//Update Address
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.newAddressDynamic(placeId, setnewAddress))
		.when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get the new Address and verify if updated
		String getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("/maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200).extract().response().asString();
				//JsonPath js1 = new JsonPath(getResponse);
				JsonPath js1 = reusemethods.convertjson(getResponse);
				String getValue = js1.getString("address");
				Assert.assertEquals(getValue, setnewAddress);
				
				
		//Delete the new created/updated place
		 given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(payload.deletedata(placeId))
				.when().delete("/maps/api/place/delete/json")
				.then().assertThat().statusCode(200).body("status", equalTo("OK"))
				.extract().response().asString();
		
		
	}
	//Test data in a multi dimensional array
	@DataProvider(name="Booksdata")
	public Object[][] multidarray() {
		return new Object[][] {{"LA"},{"London"},{"Singapore"}};
		
	}
	
	

}
