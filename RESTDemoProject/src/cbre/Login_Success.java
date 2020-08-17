package cbre;

import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import files.reusemethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class Login_Success {
	
	
	@Test
	public void testing() {
		RestAssured.baseURI = "http://52.68.141.97";
		//Login success
		String response = given().log().all().queryParam("email", "test").queryParam("password", "test")
				.when().post("/api/users/authenticate")
				.then().assertThat().statusCode(200)
				.extract().response().asString();
		JsonPath js = reusemethods.convertjson(response);
		String stats = js.get("user.email");
		Assert.assertEquals(stats, "test");		
	}

}
