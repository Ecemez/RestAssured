package day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.given;


public class Authentications {

	@Test(priority=1)
	void testBasicAuthentication() 
	{	
		given()
			.auth().basic("postman", "password")	
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	
	@Test(priority=2)
	void testDigestAuthentication() 
	{
		given()
			.auth().digest("postman", "password")	
		.when()
			.get("https://postman-echo.com/digest-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	
	@Test(priority=3)
	void testPreemptiveAuthentication() 
	{
			
		given()
			.auth().preemptive().basic("postman", "password")	
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test(priority=4)
	void testBearerTokenAuthentication() 
	{
		String bearerToken = "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";
		
		given()
			.headers("Authorization","Bearer " + bearerToken) //inside the Postman: Bearer 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
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//@Test
	void testOauth1Authentication() 
	{
			//No example
		given()
			.auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")	
		.when()
			.get("url")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority=5)
	void testOauth2Authentication() 
	{
		given()
			.auth().oauth2("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")	
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
	@Test(priority=6)
	void testAPIKeyAuthentication() 
	{
		//Method1
		given()
			.queryParam("appid","fe9c5cddb7e01d747b4611c3fc9eaf2c") //appid is API Key	
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		.then()
			.statusCode(200)
			.log().all();
		
		//Method2
		given()
			.queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
			.pathParam("mypath", "data/2.5/forecast/daily")
			.queryParam("q", "Delhi")
			.queryParam("units", "metric")
			.queryParam("cnt", "7")
			
		.when()
			.get("https://api.openweathermap.org/{mypath}")
		.then()
			.statusCode(200)
			.log().all();
	}
	
}
