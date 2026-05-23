package base;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Inițializează automat driverul de Chrome
        WebDriverManager.chromedriver().setup();

        // 1. Configure the Chrome Preferences to block popups globally
        ChromeOptions options = getChromeOptions();

        // FIX: Pass the options object right here!
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navighează către URL-ul din fișierul de config
        driver.get(ConfigReader.getProperty("ui.url"));
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();

        // Existing settings to stop saving prompts
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);

        // 🌟 THE FIX: Explicitly disable Google's Breach & Leak Detection features
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        // 🌟 THE FIX PART 2: Turn off the Feature Flag at the browser process level
        options.addArguments("--disable-features=PasswordLeakDetection");

        // Remaining stability arguments
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--start-maximized");

        return options;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}