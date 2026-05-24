package APITests;

import io.restassured.response.Response;
import models.UserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestUserAPI extends BaseAPITest {

    @Test(description = "Verify single user retrieval and validate fields via Data Models")
    public void testGetSingleUser() {
        UserResponse userResponse =
                given()
                        .spec(cleanSpec)
                        .when()
                        .get("/users/2") // Appends directly onto https://reqres.in/api
                        .then()
                        .statusCode(200)
                        .time(lessThan(2000L)) // Validates performance latency under 2 seconds
                        .extract().as(UserResponse.class); // 🌟 Deserialization to POJO

        // Assertions leveraging our Lombok models
        Assert.assertNotNull(userResponse.getData(), "User data block should not be null!");
        Assert.assertEquals(userResponse.getData().getId(), 2);
        Assert.assertEquals(userResponse.getData().getFirstName(), "Janet");
        Assert.assertEquals(userResponse.getData().getEmail(), "janet.weaver@reqres.in");

        System.out.println("🎉 API Model verified successfully for: " + userResponse.getData().getFirstName());
    }

    @Test(description = "Verify new user creation via POST method payload")
    public void testCreateNewUser() {
        // Constructing a payload map to simulate a user registration form body
        Map<String, String> payload = new HashMap<>();
        payload.put("name", "Vali Dragu");
        payload.put("job", "Automation Engineer");

        given()
                .spec(cleanSpec)
                .body(payload)
                .when()
                .post("/users")
                .then()
                .statusCode(201) // HTTP 201 Created Status Code verification
                .body("name", equalTo("Vali Dragu"))
                .body("job", equalTo("Automation Engineer"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());
    }
}