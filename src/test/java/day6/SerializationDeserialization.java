package day6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import day2.Pojo_PostRequest;

//POJO ----Serialize---> JSON Object ----Deserialize---> POJO

public class SerializationDeserialization {

		//Serialization
	@Test
	void convertPojo2Json() throws JsonProcessingException {
		
		//created java object using pojo class
		Student stupojo = new Student(); //pojo
		stupojo.setName("Scott");
		stupojo.setLocation("France");
		stupojo.setPhone("123456");
	
		String courseArr[]= {"C","C++"};
		stupojo.setCourses(courseArr);
		
		//convert java object ---> json object (serialization)
		ObjectMapper objMapper = new ObjectMapper();
		
		String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
		System.out.println(jsondata);
	}
	
		//De-serialization
	@Test
	void convertJson2Pojo() throws JsonProcessingException { //copy-paste the json response in " "
		String jsondata = "{\r\n"
				+ "  \"name\" : \"Scott\",\r\n"
				+ "  \"location\" : \"France\",\r\n"
				+ "  \"phone\" : \"123456\",\r\n"
				+ "  \"courses\" : [ \"C\", \"C++\" ]\r\n"
				+ "}";
		
		//convert json data ---> pojo object
		
		ObjectMapper objMapper = new ObjectMapper();
		
		Student stupojo = objMapper.readValue(jsondata, Student.class);
		System.out.println("Name: "+stupojo.getName());
		System.out.println("Location: "+stupojo.getLocation());
		System.out.println("Phone: "+stupojo.getPhone());
		System.out.println("Courses1: "+stupojo.getCourses()[0]);
		System.out.println("Course2: "+stupojo.getCourses()[1]);
		
		
				
	}
	
	
	
	
	
}
