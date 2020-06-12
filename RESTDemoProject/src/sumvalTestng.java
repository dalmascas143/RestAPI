import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class sumvalTestng {

	
	@Test
	public void testing() {
		JsonPath js = new JsonPath(payload.dummy());
		int count = js.getInt("courses.size()");
		int sum = 0;
		for(int i= 0; i<count; i++) {
			//System.out.println(js.get("courses["+i+"].title").toString());
			//System.out.println(js.get("courses["+i+"].price").toString());
			int price = js.get("courses["+i+"].price");
			int copies = js.get("courses["+i+"].copies");
			int amount = price*copies;
			sum = sum+ amount;
			
		}
		System.out.println(sum);
		int purchamount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchamount);
	}
	
}
