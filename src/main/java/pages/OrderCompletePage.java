package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderCompletePage extends BasePage{

    private By completeHeader = By.xpath("//h2[@data-test='complete-header']");
    private By completeText = By.xpath("//div[@data-test='complete-text']");
    private By homeBtn = By.xpath("//button[@data-test='back-to-products']");

    public OrderCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        try {
            return driver.findElement(completeHeader).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public String getCompleteHeader() {
       return driver.findElement(completeHeader).getText().trim();
    }

    public String getCompleteText() {
        return driver.findElement(completeText).getText().trim();
    }

    public void goHome() {
        driver.findElement(homeBtn).click();
    }
}
