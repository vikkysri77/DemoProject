import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class postmethods {

	String e_Token;
	String allQuestions;
	@Test
	public void postLogin() {

//		Set up base URI

		RestAssured.baseURI = "https://qa-ms.revature.com";
		String str1 = "{" + "\"password\" : \"Pass123$\"," + "\"timeZone\" : \"America/New_York\","
				+ "\"userName\" : \"shuta001@yopmail.com\"\r\n" + "}";

		Response resp = given().body(str1).contentType(ContentType.JSON)

				.when().post("/security/admin/login").then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("description", equalTo("Authentication successful")).and()
				.body("data.id", equalTo(2541)).extract().response();
		String responseString = resp.asString();
//		System.out.println(responseString);

		JsonPath jsonPath = new JsonPath(responseString);
		e_Token = jsonPath.get("data.encryptedLoginToken");
//		System.out.println(e_Token);
	}

	@Test
	public void getAllQuestions() {
		RestAssured.baseURI = "https://72sbhrpxeb.execute-api.us-east-2.amazonaws.com";
		Response resp1  = given().contentType(ContentType.JSON).header("encryptedToken",
				"7rL8RebbpebrWj1OwMowZwC2xR6f7J7J68tKw8DZbuzpt+Mgk7Bt0WBcKMVCIdsSEIvINCsnkP47ErJOF0LS5RztWLuoVAyWx9eWFFZC3KonSuNomkK/YHkdzNRLvKzBqJb5E6T1d8XZihkETcsJdHp6ZdPC4Jlnl5/uGPGhliifRnO6Cvfx9JdO+ebuXpp7X1OGnL7X6YBEo4ApMMDOgqfUM6S7AGPJCeB7WP6kx29TmRT+THa/EPqq2+YvHMS7u514w6i9US0jdAe4I7AaEISjSOAl5V3fz7TtdPZWSROSRt+VnYuYUn3724YP2qh9oLkrBWDc0CunMvk1LH2kVzKUmHhyF8ZDQXjXYRIWhtK/Nks8/k4ThTsaLshqHrCvaP15uhySy54HwaSyPbHU4wRx1/JTvEnLxK84fbth27e4suUDcxwCmCduJ8UekRKchU6S3ToPAvwwSMrIHdOkbYWXE+S/VJ/qfg4BUbmW1vh29s8B7L12clfNnEpEPDyJUxnLrSQNTEl+ZtWBc2TXTw==")
				.when().post("/qa/quiz/secure/questions").then().assertThat().extract().response();
		String allQuestionsResponse = resp1.asString();
//		System.out.println(allQuestionsResponse);
		
		JsonPath jsonPath = new JsonPath(allQuestionsResponse);
		allQuestions = jsonPath.getString("data");
		System.out.println(allQuestions);
	}
}
