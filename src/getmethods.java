
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
//import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
//import org.testng.Assert;
import org.testng.annotations.Test;
//import static io.restassured.RestAssured.*;

public class getmethods {
//
//	@Test
//	public void testResponseCode() {
//		Response resp = get("https://68c6dw3mmf.execute-api.us-west-1.amazonaws.com/qa2/security/status/info");
//		int scode = resp.getStatusCode();
//		System.out.println("Status code is" + scode);
//		Assert.assertEquals(scode, 200);
//	}
//
//	@Test
//	public void getbody() {
//		Response resp = get("https://68c6dw3mmf.execute-api.us-west-1.amazonaws.com/qa2/security/status/info");
//		String sbody = resp.asString();
//		System.out.println("Response body is" + sbody);
//		System.out.println("Response time is " + resp.getTime());
//	}

	@Test
	public void Testget() {
//		Mention BASEURI
		RestAssured.baseURI = "https://68c6dw3mmf.execute-api.us-west-1.amazonaws.com";
		given().when().

//		To mention the method type
				get("/qa2/security/status/info").

//		To check response body
				then().assertThat().
				statusCode(200).and().statusLine("HTTP/1.1 200 OK").and().contentType(ContentType.JSON).and()
				.body("description", equalTo("Status info retrieved successfully")).and()
				.body("data.gitHash", equalTo("89ca09f7b14d7a2e5f349df9ed44cf383457fe64")).and()
				.body("data.appName", equalTo("security")).and().body("data.gitBranch", equalTo("origin/OM")).and()
				.time(lessThan(10000L)).and().

//		To check response headers
				header("Content-Type", "application/json;charset=UTF-8");

	}

}
