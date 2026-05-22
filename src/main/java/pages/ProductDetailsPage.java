package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage{

    private By backButton = By.xpath("//button[@data-test='back-to-products']");
    private By detailsProductName = By.xpath("//div[@data-test='inventory-item-name']");
    private By detailsProductDescription = By.xpath("//div[@data-test='inventory-item-desc']");
    private By detailsProductPrice  = By.xpath("//div[@data-test='inventory-item-price']");
    private By detailsAddToCartBtn = By.xpath("//button[@data-test='add-to-cart']");
    private By detailsRemoveBtn  = By.xpath("//button[@data-test='remove']");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return driver.findElement(detailsProductName).getText().trim();
    }

    public String getProductDescription() {
        return driver.findElement(detailsProductDescription).getText().trim();
    }

    public double getProductPrice() {
        return cleanAndParsePrice(driver.findElement(detailsProductPrice).getText());
    }

    public void detailsAddToCart() {
        driver.findElement(detailsAddToCartBtn).click();
    }

    public void detailsRemoveFromCart() {
        driver.findElement(detailsRemoveBtn).click();
    }

    public void returnToProductsPage() {
        driver.findElement(backButton).click();
    }

}
