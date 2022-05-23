import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import org.testng.Assert;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
 class RestAssured_assignment
{
	 Links L =new Links();
	 endpoints e= new endpoints();
	//1 assertions on fetch user list 
    @Test(groups = { "test-group" })
	void Fetch_User_LIST()
	{
		//Base URI 
		baseURI=L.baseurl;
		given().get(e.getUser).
		then().assertThat().body("total_pages", equalTo(2));
	}
		
		
	@Test(groups = { "test-group" })
	
	void  create_employee()
	{
		        //specifying the base url
                 baseURI=L.baseurl + e.createUser;
		       
				//predefined to get the specification of the request
				RequestSpecification http_req=RestAssured.given(); //so it is a request object created for get
				
				
				//So when using request we need to send some data to the server
				//request payload sending along with post request
				JSONObject requestParams=new JSONObject(); // using json object for data
				requestParams.put("name", "Danish");
				requestParams.put("job", "Leader");
				requestParams.put("id", "13");
				
				http_req.header("Content-Type","application/json");
				
				http_req.body(requestParams.toJSONString());
				
				//So now we need to store the response here is the response object
				Response response=http_req.request(Method.POST);
				
				//Assert is a verification point
				JsonPath extractor= response.jsonPath();
				
				Assert.assertEquals(extractor.get("id"), "13"); //putting the value to check it
				//now we need to see the response 
				//printing it now
				get_Response_info(response);
				
				
	}
	
	@Test(groups = { "test-group" })
	
	void put()
	{
		//specifying the base url
		baseURI="https://reqres.in/api/users";
		
		//predefined to get the specification of the request
		RequestSpecification http_req=RestAssured.given(); //so it is a request object created for get
		
		
		//So when using request we need to send some data to the server
		//request payload sending along with post request
		JSONObject requestParams=new JSONObject(); // using json object for data
		requestParams.put("name", "Danish2");
		requestParams.put("job", "Leader2");
		
		http_req.header("Content-Type","application/json");
		
		http_req.body(requestParams.toJSONString());
		
		//So now we need to store the response here is the response object
		Response response=http_req.request(Method.PUT);
		
		//Assert is a verification point
		JsonPath extractor= response.jsonPath();
		
		Assert.assertEquals(extractor.get("name"), "Danish2");
		Assert.assertEquals(extractor.get("job"), "Leader2");
		//putting the value to check it
		//now we need to see the response 
		//printing it now
		get_Response_info(response);
	}
	
	@Test(groups = { "test-group" })
	
	void patch()
	{
		//specifying the base url
				baseURI="https://reqres.in/api/users";
				
				//predefined to get the specification of the request
				RequestSpecification http_req=RestAssured.given(); //so it is a request object created for get
				
				
				//So when using request we need to send some data to the server
				//request payload sending along with post request
				JSONObject requestParams=new JSONObject(); // using json object for data
				requestParams.put("name", "Danish2");
				
				http_req.header("Content-Type","application/json");
				
				http_req.body(requestParams.toJSONString());
				
				//So now we need to store the response here is the response object
				Response response=http_req.request(Method.PATCH);
				
				//Assert is a verification point
				JsonPath extractor= response.jsonPath();
				
				Assert.assertEquals(extractor.get("name"), "Danish2");
				//putting the value to check it
				//now we need to see the response 
				//printing it now
				get_Response_info(response);
		
	}
	
	@Test (groups = { "test-group" })
	void Del()
	{
		baseURI="https://reqres.in";
		
		when().delete("/api/users/2").then().statusCode(204).log().all();
	}
	
	void get_Response_info(Response response)
	{
		String responsebody= response.getBody().asString();
		System.out.println("Response is : "+ responsebody);		
		//verifying status code aka it's validation
		int statuscode=response.getStatusCode();
		System.out.println("status code is : " + statuscode);
	}
}
