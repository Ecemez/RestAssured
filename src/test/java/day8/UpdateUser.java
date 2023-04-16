package day8;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import org.json.JSONObject;

public class UpdateUser {
	
		@Test
		void test_updateUser(ITestContext context) 
		{
			Faker faker = new Faker();
			
			JSONObject data = new JSONObject();
			data.put("name", faker.name().fullName());
			data.put("gender", "Female");
			data.put("email", faker.internet().emailAddress());
			data.put("status", "active");
			
			String bearerToken = "5487dafa42317ac6ac4d37158851033c9b96baa4423c877b7c05528e12310acf";
			
			int id = (Integer) context.getAttribute("user_id");
			
			given()
				.headers("Authorization", "Bearer "+bearerToken)
				.contentType("application/json")
				.pathParam("id", id)
				.body(data.toString())
			.when()
				.put("https://gorest.co.in/public/v2/users/{id}")
			.then()
				.statusCode(200)
				.log().all();
			
		}
	}


