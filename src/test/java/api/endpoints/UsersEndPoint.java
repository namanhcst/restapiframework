package api.endpoints;

import api.payloads.pojo.users.User;
import api.utilities.ExtentReportManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class UsersEndPoint {

	public static Response createUser(User payload) {

		RequestSpecification requestSpec = given().contentType(ContentType.JSON).body(payload);
				
        Response res = requestSpec.post(Routes.users_post);

		return res;

	}

	public static Response getUsers() {

		Response res = given().contentType(ContentType.JSON).when().get(Routes.users_get);

		return res;

	}

	public static Response getUser(Integer userId) {

		Response res = given().contentType(ContentType.JSON).pathParam("userId", userId).log().all()
				.get(Routes.users_get + "{userId}");

		return res;

	}

	public static Response updateUser(Integer userId, User updateUserPayload) {

		Response res = given().contentType(ContentType.JSON).body(updateUserPayload).pathParam("userId", userId).log().all().
				when()
				.put(Routes.users_put + "{userId}");

		return res;

	}

	public static Response deleteUser(Integer userId) {

		Response res = given().contentType(ContentType.JSON).pathParam("userId", userId).log().all().when().delete(Routes.users_delete + "{userId}");

		return res;

	}

}
