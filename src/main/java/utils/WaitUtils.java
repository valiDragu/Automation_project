package utils;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    private static WebDriverWait getWait() {
        // Automatically fetches the correct driver instance for the executing thread
        return new WebDriverWait(DriverFactory.getDriver(), DEFAULT_TIMEOUT);
    }

    /**
     * Waits until an element is visible in the DOM.
     */
    public static WebElement waitForVisibility(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until an element is fully clickable (visible and enabled).
     */
    public static WebElement waitForClickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Dynamically waits for an element to completely disappear from the DOM.
     */
    public static Boolean waitForInvisibility(By locator) {
        return getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}