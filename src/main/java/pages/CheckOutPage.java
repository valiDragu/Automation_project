package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class CheckOutPage {
    private WebDriver driver;

    private By inputFirstName = By.xpath("//input[@data-test='firstName']");
    private By inputLastName = By.xpath("//input[@data-test='lastName']");
    private By inputZipCode = By.xpath("//input[@data-test='postalCode']");
    private By cancelBtn = By.xpath("//button[@data-test='cancel']");
    private By continueBtn = By.xpath("//input[@data-test='continue']");
    private By errorContainer = By.xpath("//div[@class='error-message-container error']");
    private By errorMessage = By.xpath("//h3[@data-test='error']");
    private By closeError = By.xpath("//button[@data-test='error-button']");

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillForm(String fname, String lname, String zip) {
        WaitUtils.waitForVisibility(inputFirstName).sendKeys(fname);
        WaitUtils.waitForVisibility(inputLastName).sendKeys(lname);
        WaitUtils.waitForVisibility(inputZipCode).sendKeys(zip);
        WaitUtils.waitForClickable(continueBtn).click();

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

    public void continueShopping() {
        driver.findElement(continueBtn).click();
    }

    public void cancelToCart() {
        driver.findElement(cancelBtn).click();
    }
}
