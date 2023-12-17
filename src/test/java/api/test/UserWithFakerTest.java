package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UsersEndPoint;
import api.payloads.pojo.users.User;
import io.restassured.response.Response;

public class UserWithFakerTest {

	public User user;
	public int id; 
	
	@BeforeClass
	public void createRandomUser() {

		Faker faker = new Faker();
		user = new User();

		user.setEmail(faker.internet().safeEmailAddress());
		user.setFirstName(faker.name().firstName());
		user.setLastName(faker.name().lastName());
		user.setUsername(faker.name().username());
		user.setPassword(faker.internet().password());
		user.setPhone(faker.phoneNumber().cellPhone());
		user.setUserStatus(faker.number().numberBetween(0, 9));

	}

	@Test(priority=1)
	public void createUserTest() {

		Response res = UsersEndPoint.createUser(user);
		res.then().log().all();
		int statusCode = res.getStatusCode();
		 id = res.jsonPath().getInt("id");
		 System.out.println("user created with id: "+id);
		Assert.assertEquals(statusCode, 201, "Verifying If statuscode is 201");

	}

	@Test(priority=2)
	public void readUserTest() {

		Response res = UsersEndPoint.getUser(id);
		res.then().log().all();
		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Verifying If statuscode is 200");

	}
	
	@Test(priority=3)
	public void updateUserTest() {
		
		user.setFirstName("UpdatedFirstName");
		Response res = UsersEndPoint.updateUser(id,user);
		res.then().log().all();
		int statusCode = res.getStatusCode();
		String updatedName = res.jsonPath().getString("firstName");
		
		Assert.assertEquals(statusCode, 200, "Verifying If userName is updated");
		Assert.assertEquals(updatedName, "UpdatedFirstName", "Verifying If firstName is updated");

	}
	
	@Test(priority=4)
	public void deleteUserTest() {

		Response res = UsersEndPoint.deleteUser(id);
		res.then().log().all();
		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Verifying If statuscode is 200");

	}
}
