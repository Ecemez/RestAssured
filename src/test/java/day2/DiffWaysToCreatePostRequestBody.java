package day2;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
/*
 Different ways to create POST request body
 Post request body using Hashmap
 Post request body creation using org.JSON
 Post request body creation using POJO class
 Post request body using external json file data
 */


public class DiffWaysToCreatePostRequestBody {
	
	//Post Request body using org.JSON
	//@Test(priority=1)
	void testPostusingJsonLibrary() 
	{
		JSONObject data = new JSONObject();
		data.put("name","Scott");
		data.put("location","France");
		data.put("phone","123456");
		
		String courseArr[]= {"C","C++"};
		data.put("courses",courseArr);
		
		given()	
		    .contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("Scott"))
			.body("location",equalTo("France"))
			.body("phone",equalTo("123456"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
	}
	
	//Deleting student record
	@Test(priority=2)
	void testDelete() 
	{
		given()
		
		.when()
			.delete("http://localhost:3000/students/16")
			
		.then()
			.statusCode(200);
	}
	
	
	//Post Request body using POJO
	//@Test(priority=1)
	void testPostusingPOJO() 
	{
		Pojo_PostRequest data = new Pojo_PostRequest();
		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("123456");
	
		String courseArr[]= {"C","C++"};
		data.setCourses(courseArr);
		
		given()	
		    .contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("Scott"))
			.body("location",equalTo("France"))
			.body("phone",equalTo("123456"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
	}
	
	
	//Post Request using external .json file
	@Test(priority=1)
	void testPostusingExternalJsonFile() throws FileNotFoundException 
	{
		File f = new File(".\\body.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		given()	
		    .contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("Starxo"))
			.body("location",equalTo("Poland"))
			.body("phone",equalTo("432565"))
			.body("courses[0]",equalTo("java"))
			.body("courses[1]",equalTo("python"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
	}

}
