package day1;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;


import org.testng.annotations.Test;

public class HTTPRequests {

	int id;
	
	@Test(priority=1)
	void getUser()
	{
		given()
		.when()
		.get("https://reqres.in/api/users?page=2")
		.then()
		.statusCode(200)
		.body("page",equalTo(2))
		.log().all();
		
	}
	
	
	@Test(priority=2)
	void createUser()
	{
		JSONObject data = new JSONObject(); //Instead of HashMap
		data.put("name","pavan");
		data.put("job","trainer");
		
		id = given()
			.contentType("application/json") //ContentType.JSON
			.body(data.toString())
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		/*.then()
			/.statusCode(201) //status code of create
			/.log().all();*/
		
	}
	
	
	@Test(priority=3, dependsOnMethods = {"createUser"})
	void updateUser()
	{
		JSONObject data = new JSONObject(); //Instead of HashMap
		data.put("name","pavan");
		data.put("job","trainer");
		
		 given()
			.contentType("application/json") //ContentType.JSON
			.body(data.toString())
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200) //status code of create
			.log().all();
	}
	
	@Test(priority=4)
	void deleteUser()
	{
		given()
		
		.when()
			.delete("https://reqres.in/api/users"+id)
		
		.then()
		.statusCode(204)
		.log().all();
		
	}
	
}
