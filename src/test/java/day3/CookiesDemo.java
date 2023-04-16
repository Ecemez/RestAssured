package day3;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.util.Map;
import io.restassured.response.Response;

public class CookiesDemo {

	@Test(priority=1)
	void testCookies() 
	{
		given()
		
		.when()
			.get("https://www.google.com/")
		.then()
			.cookie("AEC","AUEFqZciqXHtSAmWefu3U3j8HvoXvgc5qnmxobZVSZ6CWljYr76YUI710X0")
			.log().all();
	}
	
	
	@Test(priority=2)
	void getCookiesInfo() 
	{
		Response res = given()
		
		.when()
			.get("https://www.google.com/");
		
		/*get single cookie info
		String cookie_value = res.getCookie("AEC");
		System.out.println("Value of cookie is ===>"+cookie_value);*/
		
		//get all cookies info
		Map<String, String>cookies_values = res.getCookies();
		//System.out.println(cookies_values.keySet()); //get only keys not values
			
		for(String k:cookies_values.keySet())
		{
			String cookie_value=res.getCookie(k);
			System.out.println(k+"   "+cookie_value);
		}
	}
	
	
	
	
	
}
