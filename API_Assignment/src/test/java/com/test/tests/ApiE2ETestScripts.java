package com.test.tests;

import java.util.Properties;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.utils.Utils;
import com.test.models.CreateUserPOJO;
import com.test.models.CreateUserResponsePOJO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

//Test the change at 6:48
	
public class ApiE2ETestScripts {
	
	Response res;
	int extractedId;
	
	@BeforeClass
	public static void setUp() {
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1/";
	}
	
	@Test
	public void testCase_1() {
		
		res=RestAssured.given()
		.contentType(ContentType.JSON)
		.when()
		.get("employees");
		
		res
		.then()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.time(Matchers.lessThan(5000L));
		
		res.prettyPrint();
		 
		String actualStatus = res.jsonPath().getString("status");
	    Assert.assertEquals(actualStatus, "success");   
		
		//int num = res.body().jsonPath().get("length()");
	    
	    System.out.println("Number of list : " +res.body(). jsonPath().getInt("data.size()"));
	
	   }
	
	@Test
	public void testCase_2() {
		
		CreateUserPOJO data = new CreateUserPOJO();
		data.setName("test");
		data.setAge("24");
		data.setSalary("124");
		
	    //String requestBody = "{\"name\":\"test\",\"salary\":\"124\",\"age\":\"24\"}";
	    
	          res=RestAssured.given()
	            .contentType(ContentType.JSON)
	            .body(data)
	            .when()
	            .post("create");
	          
	          CreateUserResponsePOJO ob = new CreateUserResponsePOJO(); 
	          res.getBody();
	          
	          
	          res.then().statusCode(200);
	          res.prettyPrint();
	    
	   String actualStatus = res.jsonPath().getString("status");
	   System.out.println(actualStatus);
	   Assert.assertEquals(actualStatus, "success"); 
	   
	   String actualName = res.jsonPath().getString("data.name");
	   System.out.println(actualName);
	   Assert.assertEquals(actualName, "test");
	   
	   String actualSalary = res.jsonPath().getString("data.salary");
	   System.out.println(actualSalary);
	   Assert.assertEquals(actualSalary, "124");
	   
	   String actualAge = res.jsonPath().getString("data.age");
	   System.out.println(actualAge);
	   Assert.assertEquals(actualAge, "24");
	   
	   extractedId = res.jsonPath().getInt("data.id");
	   System.out.println("Extracted ID = "+extractedId);
	   
	}
	
	
	@Test(dependsOnMethods = "testCase_2")
	public void testCase_3() {
		
		//int extractedId = 1838;
		Response res=RestAssured.given()
		.contentType(ContentType.JSON)
		.when()
		.delete("delete/"+extractedId);
		System.out.println(extractedId);
		res
		.then()
		.statusCode(200)
		.contentType(ContentType.JSON);
		res.prettyPrint();
		
		String actualStatus = res.jsonPath().getString("status");
		System.out.println(actualStatus);
		Assert.assertEquals(actualStatus, "success"); 
		
		String id = Integer.toString(extractedId);
		String responseData = res.jsonPath().getString("data");
		System.out.println(responseData);
		Assert.assertEquals(id, responseData);
	}
	
	
	
	@Test()
	public void testCase_4() {
		
		int extractedId = 0;
		Response res=RestAssured.given()
		.contentType(ContentType.JSON)
		.when()
		.delete("delete/"+extractedId);
		
		res
		.then()
		.contentType(ContentType.JSON);
		res.prettyPrint();
		
		
		
		String responseCode = res.jsonPath().getString("code");
		System.out.println("Status Code is : "+responseCode);
		//Assert.assertEquals(responseCode, "200"); 
		
		String responseBodyStatus = res.jsonPath().getString("status");
		System.out.println("ResponseBody Status is : "+responseBodyStatus);
		//Assert.assertEquals(responseBodyStatus, "success"); 
		
		
		String responseMessage = res.jsonPath().getString("message");
		System.out.println("Response Message = "+responseMessage);
	}
	
	
	@Test
	public void testCase_5() {
		
		res=RestAssured.given()
		.contentType(ContentType.JSON)
		.when()
		.get("employee/2");
		
		res
		.then()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.time(Matchers.lessThan(5000L));
		
		res.prettyPrint();
		 
		String actualStatus = res.jsonPath().getString("status");
	    Assert.assertEquals("success", "success");   
		
	    String employeeName = res.jsonPath().getString("data.employee_name");
	    System.out.println("Name of the employee is "+employeeName);
	    
	    String employeeSalary = res.jsonPath().getString("data.employee_salary");
	    System.out.println("employee salary is "+employeeSalary);
	    
	    String employeeAge = res.jsonPath().getString("data.employee_age");
	    System.out.println("employee age  is "+employeeAge);
	    
	
	   }
	

	
	
}
	
	

