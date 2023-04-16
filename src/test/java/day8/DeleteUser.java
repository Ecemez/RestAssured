package day8;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class DeleteUser 
{
	@Test
	void test_DeleteUser(ITestContext context) 
	{
		String bearerToken = "5487dafa42317ac6ac4d37158851033c9b96baa4423c877b7c05528e12310acf";
		
		int id =(Integer) context.getAttribute("user_id");	
		given()
			.headers("Authorization", "Bearer "+bearerToken)
			.pathParam("id",id)
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(204)
			.log().all();

	}
}
	