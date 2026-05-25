package APITests;

import io.restassured.response.Response;
import models.AuthRequest;
import models.BookingDates;
import models.BookingRequest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestBookingAPI extends BaseAPITest {

    private String authToken;
    private int savedBookingId;

    @Test(priority = 1, description = "Authenticate user and store token for future requests")
    public void testUserAuthentication() {
        AuthRequest authCredentials = new AuthRequest("admin", "password123");

        // Extract and store the token directly into the class variable
        this.authToken = given()
                .spec(cleanSpec)
                .body(authCredentials)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .path("token");

        System.out.println("Auth Token initialized: " + authToken);
    }

    @Test(priority = 2, description = "Verify new booking creation via POST")
    public void testCreateNewBooking() {
        BookingDates dates = new BookingDates("2026-06-01", "2026-06-15");
        BookingRequest payload = new BookingRequest("John", "Doe", 150, true, dates, "Breakfast");

        Response response = given()
                .spec(cleanSpec)
                .body(payload)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("booking.firstname", equalTo(payload.getFirstname()))
                .body("booking.lastname", equalTo(payload.getLastname()))
                .body("booking.totalprice", equalTo(payload.getTotalprice()))
                .body("booking.depositpaid", equalTo(payload.isDepositpaid()))
                .body("booking.additionalneeds", equalTo(payload.getAdditionalneeds()))
                .extract()
                .response();

        this.savedBookingId = response.path("bookingid");
        System.out.println("Booking ID saved: " + savedBookingId);
    }

    @Test(
            priority = 3,
            description = "Verify booking update via PUT",
            dependsOnMethods = {"testUserAuthentication", "testCreateNewBooking"}
    )
    public void testUpdateBooking() {
        org.testng.Assert.assertTrue(savedBookingId > 0, "Saved Booking ID must be initialized and greater than 0!");

        BookingDates updatedDates = new BookingDates("2026-06-01", "2026-06-20");
        BookingRequest updatedPayload = new BookingRequest("James", "Brown", 111, true, updatedDates, "Breakfast");

        given()
                .spec(cleanSpec)
                .cookie("token", authToken)
                .body(updatedPayload)
                .when()
                .put("/booking/" + savedBookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo(updatedPayload.getFirstname()))
                .body("lastname", equalTo(updatedPayload.getLastname()))
                .body("totalprice", equalTo(updatedPayload.getTotalprice()))
                .body("depositpaid", equalTo(updatedPayload.isDepositpaid()))
                .body("additionalneeds", equalTo(updatedPayload.getAdditionalneeds()));
    }

    @Test(
            priority = 4,
            description = "Verify booking delete",
            dependsOnMethods = {"testUserAuthentication", "testCreateNewBooking"}
    )
    public void testDeleteBooking() {
        given()
                .spec(cleanSpec)
                .cookie("token", authToken)
                .when()
                .delete("/booking/" + savedBookingId)
                .then()
                .statusCode(201)
                .body(equalTo("Created"));
    }

    @Test(
            priority = 4,
            description = "Verify booking was deleted",
            dependsOnMethods = {"testUserAuthentication", "testDeleteBooking"}
    )
    public void testGetDeletedBooking() {
        given()
                .spec(cleanSpec)
                .when()
                .get("/booking/" + savedBookingId)
                .then()
                .statusCode(404);
    }
}