package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    private By cartItem = By.xpath("//div[@data-test='inventory-item']");
    private By cartItemName = By.xpath("//div[@data-test='inventory-item-name']");
    private By cartItemDescription = By.xpath("//div[@data-test='inventory_item_desc']");
    private By cartItemPrice = By.xpath("//div[@data-test='inventory-item-price']");
    private By cartItemRemoveBtn = By.xpath("//button[contains(@data-test, 'remove')]");
    private By continueBtn =  By.xpath("//button[@data-test='continue-shopping']");
    private By checkoutBtn = By.xpath("//button[@data-test='checkout']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }


    public String getCartItemName() {
        return driver.findElement(cartItemName).getText().trim();
    }

    public String getCartItemDescription() {
        return driver.findElement(cartItemDescription).getText().trim();
    }

    public double getCartItemPrice() {
        String rawPrice = driver.findElement(cartItemPrice).getText().trim().replace("$", "");
        return Double.parseDouble(rawPrice);
    }

    public int getCartItemCount() {
        List<WebElement> cartItems = driver.findElements(cartItem);
        return cartItems.size();
    }

    public List<String> getAllCartItemsNames() {
        List <String> cartItemsNames = new ArrayList<>();
        List <WebElement> itemName = driver.findElements(cartItemName);
        for (WebElement item : itemName) {
            cartItemsNames.add(item.getText().trim());
        }
        return cartItemsNames;
    }

    public List<String> getAllCartItemsDescriptions() {
        List <String> cartItemsDescriptions = new ArrayList<>();
        List <WebElement> itemsDesc = driver.findElements(cartItemDescription);
        for (WebElement item : itemsDesc) {
            cartItemsDescriptions.add(item.getText().trim());
        }
        return cartItemsDescriptions;
    }

    public List<Double> getAllCartItemsPrices() {
        List <Double> cartProductPrices = new ArrayList<>();
        List<WebElement> cartPrices = driver.findElements(cartItemPrice);

        for(WebElement price : cartPrices) {
            double doublePrice = Double.parseDouble(price.getText().trim().replace("s", ""));
            cartProductPrices.add(doublePrice);
        }
        return cartProductPrices;
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
