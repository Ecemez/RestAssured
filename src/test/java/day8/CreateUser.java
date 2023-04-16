package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import org.json.JSONObject;

public class CreateUser {

	@Test
	void test_createUser(ITestContext context) 
	{
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken = "5487dafa42317ac6ac4d37158851033c9b96baa4423c877b7c05528e12310acf";
		
		int id = given()
			.headers("Authorization", "Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id"); //capture the id in the response
		
		System.out.println("Generated id is: "+id);
		context.setAttribute("user_id", id);
	}
}
