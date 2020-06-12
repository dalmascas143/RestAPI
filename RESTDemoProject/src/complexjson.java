import files.payload;
import io.restassured.path.json.JsonPath;

public class complexjson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(payload.dummy());
		
		
		int count = js.getInt("courses.size()");
		//Print count
		System.out.println(count);
		//purchase amount:
		String purchamount = js.getString("dashboard.purchaseAmount");
		System.out.println(purchamount);
		//print first tile
		String firstitle = js.get("courses[0].title");
		System.out.println(firstitle);
		//Looping all values
		for(int i= 0; i<count; i++) {
			System.out.println(js.get("courses["+i+"].title").toString());
			System.out.println(js.get("courses["+i+"].price").toString());
		}
		//Looping specific values
		for(int i= 0; i<count; i++) {
			if(js.get("courses["+i+"].title").toString().equalsIgnoreCase("Cypress")) {
			int copies = js.get("courses["+i+"].copies");
			System.out.println(copies);
			//to stop break loop if found
			break;
			}
		}
		
		
		
		
	}

}
