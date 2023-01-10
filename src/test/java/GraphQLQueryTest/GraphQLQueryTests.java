package GraphQLQueryTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GraphQLQueryTests {

	@Test
	public void getTop10Userstest() {

		RestAssured.baseURI ="https://hasura.io";
		String query = "{\r\n"
				+ "  \"query\": \"{\\n  users {\\n    id\\n    name\\n    todos {\\n      title\\n    }\\n  }\\n}\\n\",\r\n"
				+ "  \"variables\": null\r\n"
				+ "}";
		
		given().log().all()
		.contentType("application/json")
		.header("Authorization", 
				"Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYzYjZjZTNjNTliYWE1ZDAwYzZiMTI5NyJ9LCJuaWNrbmFtZSI6Im1hbm9oYXJhdXRvbWF0aW9uMjIiLCJuYW1lIjoibWFub2hhcmF1dG9tYXRpb24yMkBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvZDlkMGViMjliZDA1NjJhNTFiOGRjZjNhOTExMTUzYTA_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZtYS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMy0wMS0xMFQwNjowMjoyOC4yNjlaIiwiaXNzIjoiaHR0cHM6Ly9ncmFwaHFsLXR1dG9yaWFscy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjNiNmNlM2M1OWJhYTVkMDBjNmIxMjk3IiwiYXVkIjoiUDM4cW5GbzFsRkFRSnJ6a3VuLS13RXpxbGpWTkdjV1ciLCJpYXQiOjE2NzMzMzA1NTAsImV4cCI6MTY3MzM2NjU1MCwiYXRfaGFzaCI6ImJrTTNZSG1xM0pldmY2UXg2R09uSUEiLCJzaWQiOiJ6Y0loR1AxdlYtbnZ6NnRuSWlkUUl6VW9udDh0aUtaNCIsIm5vbmNlIjoiNlJKbE1VY01BRXpGOEJ-ZTEySlJOSHpSck8zfjIyQ1IifQ.qN-NTMH3cbq_JsYfQ4QLEdWJ5oSKEw88Gsuk--wlwctJDxwBtIXmW-ATPyQNM-76Z4mDnwmc_tTUocismEulZTRrK3GgWFZGtVvQXS3fiAcerpXSqntE7Dvbv2kb4Uuzny2GXNup3f9JxCg8bv0qYNoBHA249S8g72vjJNGKV8qf0_f8o_xpGhwj3xbGSGM6m9smsbRV1sE2dGPRYIFIOKUC5CgefVe8Db2DYqnGhq-fZHIza0YxhAZ1v_CLep-Vr-W2JHzguQVzAwtDq950CKql4nBsP7CuhmXIRlesdE5Vghe1mg_B0iOy6wVsCp8yfDXZLqprr-dI9u0GKoAzgA")
		.body(query)
		.when().log().all()
			.post("/learn/graphql")
				.then().log().all()
					.assertThat()
						.statusCode(200)
							.body("data.users[0].name", equalTo("tui.glen"));				
	
	}
	
	@DataProvider
	public Object[][] getQueryData() {
		return new Object[][] {{"10", "akshayapsangi123", "Flutter development"}, };
	}
	

	@Test(dataProvider = "getQueryData")
	public void getUserswithTestData(String limit, String name, String title) {
		RestAssured.baseURI ="https://hasura.io";
		String query = "{\"query\":\"{\\n  users(limit: "+limit+", where: {name: {_eq: \\\""+name+"\\\"}}) {\\n    id\\n    name\\n    todos(where: {title: {_eq: \\\""+title+"\\\"}}) {\\n      title\\n    }\\n  }\\n}\\n\",\"variables\":null}";
		
		given().log().all()
		.contentType("application/json")
		.header("Authorization", 
				"Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYzYjZjZTNjNTliYWE1ZDAwYzZiMTI5NyJ9LCJuaWNrbmFtZSI6Im1hbm9oYXJhdXRvbWF0aW9uMjIiLCJuYW1lIjoibWFub2hhcmF1dG9tYXRpb24yMkBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvZDlkMGViMjliZDA1NjJhNTFiOGRjZjNhOTExMTUzYTA_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZtYS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMy0wMS0xMFQwNjowMjoyOC4yNjlaIiwiaXNzIjoiaHR0cHM6Ly9ncmFwaHFsLXR1dG9yaWFscy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjNiNmNlM2M1OWJhYTVkMDBjNmIxMjk3IiwiYXVkIjoiUDM4cW5GbzFsRkFRSnJ6a3VuLS13RXpxbGpWTkdjV1ciLCJpYXQiOjE2NzMzMzA1NTAsImV4cCI6MTY3MzM2NjU1MCwiYXRfaGFzaCI6ImJrTTNZSG1xM0pldmY2UXg2R09uSUEiLCJzaWQiOiJ6Y0loR1AxdlYtbnZ6NnRuSWlkUUl6VW9udDh0aUtaNCIsIm5vbmNlIjoiNlJKbE1VY01BRXpGOEJ-ZTEySlJOSHpSck8zfjIyQ1IifQ.qN-NTMH3cbq_JsYfQ4QLEdWJ5oSKEw88Gsuk--wlwctJDxwBtIXmW-ATPyQNM-76Z4mDnwmc_tTUocismEulZTRrK3GgWFZGtVvQXS3fiAcerpXSqntE7Dvbv2kb4Uuzny2GXNup3f9JxCg8bv0qYNoBHA249S8g72vjJNGKV8qf0_f8o_xpGhwj3xbGSGM6m9smsbRV1sE2dGPRYIFIOKUC5CgefVe8Db2DYqnGhq-fZHIza0YxhAZ1v_CLep-Vr-W2JHzguQVzAwtDq950CKql4nBsP7CuhmXIRlesdE5Vghe1mg_B0iOy6wVsCp8yfDXZLqprr-dI9u0GKoAzgA")	
				.body(query)
					.when().log().all()
						.post("/learn/graphql")
							.then().log().all()
								.assertThat()
									.statusCode(200);
			
	}
}
