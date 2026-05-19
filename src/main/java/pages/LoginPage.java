package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By inputUsername = By.xpath("//input[@data-test='username']");
    private By inputPassword = By.xpath("//input[@data-test='password']");
    private By loginBtn = By.xpath("//input[@data-test='login-button']");
    private By errorMessage = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sendUsername(String username) {
        driver.findElement(inputUsername).sendKeys(username);
    }

    public void sendPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }

    public void login() {
        driver.findElement(loginBtn).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText().trim();
    }
}
