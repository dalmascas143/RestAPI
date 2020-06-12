import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;
import files.reusemethods;

public class basics {

	public static void main(String[] args) {
		//End to end
		// TODO Auto-generated method stub
		//Given all the input needed
		//When is the request type
		//Then expected output
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		

		//update place
		String setnewValue = "NewAddress";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"    \"place_id\": \""+ placeId+"\",\r\n" + 
				"     \"address\": \""+setnewValue+"\",\r\n" + 
				"    \"key\": \"qaclick123\"\r\n" + 
				"}")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		//Get value
		String getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		//JsonPath js1 = new JsonPath(getResponse);
		JsonPath js1 = reusemethods.convertjson(getResponse);
		String getValue = js1.getString("address");
		System.out.println(getValue + " Frrancis");
		Assert.assertEquals(getValue, setnewValue);
		
		
	}

}
