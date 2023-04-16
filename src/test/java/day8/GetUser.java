package day8;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import org.json.JSONObject;
import org.mozilla.javascript.Context;


public class GetUser {

	
	@Test
	void test_getUser(ITestContext context) 
	{
		int id = (Integer) context.getAttribute("user_id");
		String bearerToken = "5487dafa42317ac6ac4d37158851033c9b96baa4423c877b7c05528e12310acf";
		
		given()
			.headers("Authorization", "Bearer "+bearerToken)
			.pathParam("id",id)
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
			
	}

}
