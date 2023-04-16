package day3;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class LoggingDemo {
	
	@Test
	void testLogs() 
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			//.log().body(); //response body
			//.log().cookies(); only cookies
			//.log().headers(); //only headers
		.log().all();
	}

}
