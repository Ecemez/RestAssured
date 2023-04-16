package day5;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.Map;
import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import java.io.File;

public class FileUploadAndDownload {

	@Test
	void singleFileUpload()
	{
		File myfile = new File("C:\\uploads\\test1.txt"); 
		given()
			.multiPart("file", myfile)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadFile")
		.then()
			.statusCode(200)
			.body("fileName", equalTo("test1.txt"))
			.log().all();
	}
	
	
	@Test
	void multiFilesUpload() {
		
		File myfile1 = new File("C:\\uploads\\test1.txt");
		File myfile2 = new File("C:\\uploads\\test2.txt");
		
		given()
			.multiPart("files",myfile1)
			.multiPart("files",myfile2)
			.contentType("multipart/form-data")
		
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")
		
		.then()
			.statusCode(200)
			.body("[0].fileName",equalTo("test1.txt"))
			.body("[1].fileName",equalTo("test2.txt"))
			.log().all();
	}
	
	@Test
	void multiFilesUpload2() { // may not work for all APIs
		
		File myfile1 = new File("C:\\uploads\\test1.txt");
		File myfile2 = new File("C:\\uploads\\test2.txt");
		
		File filearr[] = {myfile1, myfile2};
		
		given()
			.multiPart("files",filearr)
			.contentType("multipart/form-data")
		
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")
		
		.then()
			.statusCode(200)
			.body("[0].fileName",equalTo("test1.txt"))
			.body("[1].fileName",equalTo("test2.txt"))
			.log().all();
	}
	
	@Test
	void fileDownload() {
		given()
		.when()
			.get("http://localhost:8080/downloadFile/test1.txt")
		.then()
			.statusCode(200)
			.log().all();
	}
}
