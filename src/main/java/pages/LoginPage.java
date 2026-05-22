package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By inputUsername = By.xpath("//input[@data-test='username']");
    private By inputPassword = By.xpath("//input[@data-test='password']");
    private By loginBtn = By.xpath("//input[@data-test='login-button']");
    private By errorContainer = By.xpath("//div[@class='error-message-container error']");
    private By errorMessage = By.xpath("//h3[@data-test='error']");
    private By closeError = By.xpath("//button[@data-test='error-button']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginUser(String username,  String password) {
        driver.findElement(inputUsername).sendKeys(username);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(loginBtn).click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(errorContainer).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public String getErrorMessage() {
        if(isErrorMessageDisplayed()) {
            return driver.findElement(errorMessage).getText().trim();
        }
        return "No error message found";
    }

    public void closeError() {
        if(isErrorMessageDisplayed()) {
            driver.findElement(closeError).click();
        }
    }
}
