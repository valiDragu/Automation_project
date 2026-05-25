package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    // ThreadLocal container protects the driver instance during parallel test execution
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * Initializes the driver based on the browser string provided.
     *
     * @param browser e.g., "chrome", "firefox", "edge"
     */
    public static synchronized void initDriver(String browser) {
        String targetBrowser = browser.toLowerCase().trim();
        System.out.println("Launching browser framework configuration for: " + targetBrowser);

        if (tlDriver.get() == null) {
            switch (targetBrowser) {
                case "chrome":
                    // WebDriverManager removed; Selenium 4 handles driver binaries natively
                    tlDriver.set(new ChromeDriver(getChromeOptions()));
                    break;
                case "firefox":
                    tlDriver.set(new FirefoxDriver());
                    break;
                case "edge":
                    tlDriver.set(new EdgeDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser
                            + ". Please use 'chrome', 'firefox', or 'edge'.");
            }
        }
        getDriver();
    }

    /**
     * Retrieves the thread-allocated driver instance.
     */
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * Securely closes the driver instance and nullifies the ThreadLocal reference.
     */
    public static synchronized void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove(); // Prevents memory leaks by clearing the thread storage
        }
    }

    /**
     * Encapsulates our optimized Chrome settings
     */
    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();

        // Block popups and credential saving tracking overlays
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--start-maximized");

        // CI/CD MUST-HAVE: Run headless without a visual GUI if executing inside GitHub Actions
        if (System.getenv("GITHUB_ACTIONS") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080"); // Forces a desktop-sized viewport
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        return options;
    }
}