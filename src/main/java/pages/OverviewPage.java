package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {
    private WebDriver driver;

    private By itemCard = By.xpath("//div[@data-test='inventory-item']");
    private By cartItemPrice = By.xpath("//div[@data-test='inventory-item-price']");
    private By itemTotal = By.xpath("//div[@data-test='subtotal-label']");
    private By tax = By.xpath("//div[@data-test='tax-label']");
    private By totalPrice = By.xpath("//div[@data-test='total-label']");
    private By cancelBtn = By.xpath("//button[@data-test='cancel']");
    private By finishBtn = By.xpath("//button[@data-test='finish']");

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public void getAllProductsPrices() {}



}
