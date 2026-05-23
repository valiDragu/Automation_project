package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class OverviewPage extends BasePage{

    private By itemCard = By.xpath("//div[@data-test='inventory-item']");
    private By itemName = By.xpath("//div[@data-test='inventory-item-name']");
    private By cartItemPrice = By.xpath("//div[@data-test='inventory-item-price']");
    private By itemTotal = By.xpath("//div[@data-test='subtotal-label']");
    private By tax = By.xpath("//div[@data-test='tax-label']");
    private By totalPrice = By.xpath("//div[@data-test='total-label']");
    private By cancelBtn = By.xpath("//button[@data-test='cancel']");
    private By finishBtn = By.xpath("//button[@data-test='finish']");

    public OverviewPage(WebDriver driver) {
        super(driver);
    }

    public List<Double> getAllProductsPrices() {
        return getPricesFromElements(cartItemPrice);
    }

    public double getTax() {
        return Double.parseDouble(driver.findElement(tax).getText().trim().replace("Tax: $", ""));
    }

    public double getItemTotal() {
        return Double.parseDouble(driver.findElement(itemTotal).getText().trim().replace("Item total: $", ""));
    }

    public double getTotal() {
        return Double.parseDouble(driver.findElement(totalPrice).getText().trim().replace("Total: $", ""));
    }

    public double calculateItemTotal() {
        double itemTotal = 0.0;
        List<Double> productPrices = getAllProductsPrices();

        for (double price : productPrices) {
            itemTotal += price;
        }
        return itemTotal;
    }

    public double calculateTotal() {
        double total = 0.0;
        return getItemTotal() + getTax();
    }


    public void goToProductDetails() {
        driver.findElement(itemName).click();
    }

    public void cancelToInventory() {
        driver.findElement(cancelBtn).click();
    }

    public void finish() {
        driver.findElement(finishBtn).click();
    }

    public boolean isItemDisplayed() {
        List<WebElement> item = driver.findElements(itemCard);
        return !item.isEmpty() && item.getFirst().isDisplayed();
    }


}
