package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.UsersEndPoint;
import api.payloads.pojo.users.User;
import io.restassured.response.Response;

public class UserWithDDApproachTest extends BaseTest {

	

	@Test(priority=1, dataProvider = "getAllUsersData", dataProviderClass = api.utilities.DataProvider.class)
	public void createUserTest(String uId, String uName, String uFirstName, String uLastName, String uEmail, String uPassword, String uPhn, String uStatus) {

		Response res = UsersEndPoint.createUser(new User((int)Double.parseDouble(uId), uName, uFirstName, uLastName, uEmail, uPassword, uPhn, (int)Double.parseDouble(uStatus)));
		res.then().log().all();
		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 201, "Verifying If statuscode is 201");

	}

	
	@Test(priority=2, dataProvider = "getAllUserIDs", dataProviderClass = api.utilities.DataProvider.class)
	public void deleteUserTest(String uId) {

		Response res = UsersEndPoint.deleteUser((int)Double.parseDouble(uId));
		res.then().log().all();
		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Verifying If statuscode is 200");

	}
}
