package day6;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.module.jsv.JsonSchemaValidator;

//json-->jsonschema converter
//  https://jsonformatter.org/json-to-jsonschema

public class JSONSchemaValidation {

	@Test
	void jsonschemavalidation()
	{
		given()
		
		.when()
			.get("http://localhost:3000/store")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));
	}
	
	
}
