package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage{

    private By cartItem = By.xpath("//div[@data-test='inventory-item']");
    private By cartItemName = By.xpath("//div[@data-test='inventory-item-name']");
    private By cartItemDescription = By.xpath("//div[@data-test='inventory_item_desc']");
    private By cartItemPrice = By.xpath("//div[@data-test='inventory-item-price']");
    private By cartItemRemoveBtn = By.xpath("//button[contains(@data-test, 'remove')]");
    private By continueBtn =  By.xpath("//button[@data-test='continue-shopping']");
    private By checkoutBtn = By.xpath("//button[@data-test='checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
    }


    public String getCartItemName() {
        return driver.findElement(cartItemName).getText().trim();
    }

    public String getCartItemDescription() {
        return driver.findElement(cartItemDescription).getText().trim();
    }

    public double getCartItemPrice() {
        return cleanAndParsePrice(driver.findElement(cartItemPrice).getText());
    }

    public int getCartItemCount() {
        List<WebElement> cartItems = driver.findElements(cartItem);
        return cartItems.size();
    }

    public List<String> getAllCartItemsNames() {
        return getTextFromElements(cartItemName);
    }

    public List<String> getAllCartItemsDescriptions() {
        return getTextFromElements(cartItemDescription);
    }

    public List<Double> getAllCartItemsPrices() {
        return getPricesFromElements(cartItemPrice);
    }

    public void removeFromCart() {
        driver.findElement(cartItemRemoveBtn).click();
    }

    public boolean isCartItemRemoved() {
        return driver.findElements(cartItem).isEmpty();
    }

    public void continueShopping() {
        driver.findElement(continueBtn).click();
    }

    public void checkOut() {
        driver.findElement(checkoutBtn).click();
    }
}
