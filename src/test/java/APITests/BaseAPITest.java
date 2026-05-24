package APITests;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseAPITest {

    protected RequestSpecification cleanSpec;

    @BeforeClass
    public void setupAPI() {
        // Clear out any stale, conflicting global interceptors completely
        RestAssured.reset();

        this.cleanSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("api.baseUri"))
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .build();
    }
}