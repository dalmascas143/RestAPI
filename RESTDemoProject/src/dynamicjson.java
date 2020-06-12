
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import files.reusemethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class dynamicjson {
	
	
	@Test(dataProvider="Booksdata")
	public void testing(String booksdata1, String booksdata2) {
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().header("Content-Type","application/json")
		.body(payload.dynamicpay(booksdata1,booksdata2))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js = reusemethods.convertjson(response);
		String id = js.get("ID");
		Assert.assertEquals(id, booksdata1+booksdata2);
		//System.out.println(id);
	}
	
	@DataProvider(name="Booksdata")
	public Object[][] multidarray() {
		return new Object[][] {{"Testing array1","Array1Testiung"},{"Array2 Testing","Testing2?"},{"Array3?","TestinArray3?"}};
		
	}
	
	

}
