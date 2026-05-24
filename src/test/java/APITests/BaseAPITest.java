package APITests;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseAPITest {

    protected RequestSpecification cleanSpec;

    @BeforeClass
    public void setupAPI() {
        // 🧼 1. Reset global configurations
        RestAssured.reset();

        // 🌟 2. Initialize your isolated instance variable instead of the global RestAssured object
        this.cleanSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("api.baseUri"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .build();

        // 3. Keep the logging response specification separate
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .build();
    }
}