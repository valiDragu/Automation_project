package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage {
    private WebDriver driver;

    private By filterOptions = By.xpath("//select[@data-test='product-sort-container']");
    private By activeFilterName = By.xpath("//span[@data-test='active-option']");
    private By productName = By.xpath("//div[@data-test='inventory-item-name']");
    private By productPrice = By.xpath("//div[@data-test='inventory-item-price']");
    private By productDescription = By.xpath("//div[@data-test='inventory-item-desc']");
    private By addToCartBtn = By.xpath("//button[contains(@id, 'add-to-cart')]");
    private By removeFromCartBtn = By.xpath("//button[contains(@id, 'remove')]");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectFilterOption(String option) {
        Select select = new Select(driver.findElement(filterOptions));
        select.selectByVisibleText(option);
    }

    public String getActiveFilterName() {
        return driver.findElement(activeFilterName).getText().trim();
    }

    public String getProductDescription(String productName) {
        return xpathHelper(productName).findElement(productDescription).getText().trim();
    }

    public String getProductName(int itemIndex) {
        String productNamePath = productName.toString().replace("By.xpath: ", "");
        String indexedProductName = "(" + productNamePath + ")[" + itemIndex + "]";
        return driver.findElement(By.xpath(indexedProductName)).getText().trim();
    }

    public String getProductPrice(String productName) {
        return xpathHelper(productName).findElement(productPrice).getText().trim();
    }

    public void addProductToCart(String productName) {
        xpathHelper(productName).findElement(addToCartBtn).click();
    }

    public void removeProductFromCart(String productName) {
        xpathHelper(productName).findElement(removeFromCartBtn).click();
    }


    private WebElement xpathHelper(String productName) {
        String xpath = "//div[@data-test='inventory-item'][.//div[text()='" + productName + "']]";
        return driver.findElement(By.xpath(xpath));
    }


}
