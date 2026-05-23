package base;

import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        String browserType = ConfigReader.getProperty("browser") != null
                ? ConfigReader.getProperty("browser")
                : "chrome";

        // Initialize the isolated thread driver via our factory
        DriverFactory.initDriver(browserType);

        // Configure the browser using the thread-safe getter
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().get(ConfigReader.getProperty("ui.url"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}