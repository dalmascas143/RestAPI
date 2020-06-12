package files;

import io.restassured.path.json.JsonPath;

public class reusemethods {
	
	public static JsonPath convertjson(String rawfile) {		
		JsonPath js1 = new JsonPath(rawfile);
		return js1;
	}

}
