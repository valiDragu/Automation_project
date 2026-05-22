package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsPage extends BasePage{


    private By filterBtn = By.xpath("//select[@data-test='product-sort-container']");
    private By filterName = By.xpath("//span[@data-test='active-option']");
    private By productContainer = By.xpath("//div[@data-test='inventory-item']");
    private By productName = By.xpath("//div[@data-test='inventory-item-name']");
    private By productPrice = By.xpath("//div[@data-test='inventory-item-price']");
    private By productDescription = By.xpath("//div[@data-test='inventory-item-desc']");
    private By addToCartBtn = By.xpath("//button[contains(@id, 'add-to-cart')]");
    private By removeFromCartBtn = By.xpath("//button[contains(@id, 'remove')]");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // Filter actions
    public void selectFilter(String option) {
        Select select = new Select(driver.findElement(filterBtn));
        select.selectByVisibleText(option);
    }

    public String getFilterName() {
        return driver.findElement(filterName).getText().trim();
    }

    public List <String> filterNamesASC() {
        List <String> namesASC = new ArrayList<>(getAllProductsNames());
        Collections.sort(namesASC);
        return namesASC;
    }

    public List<Double> filterPriceASC() {
        List <Double> pricesASC = new ArrayList<>(getAllProductsPrices());
        Collections.sort(pricesASC);
        return  pricesASC;
    }

    // Actions for specific product
    public String getProductName(String productName) {
        return xpathHelper(productName).findElement(this.productName).getText().trim();
    }

    public String getProductDescription(String productName) {
        return xpathHelper(productName).findElement(productDescription).getText().trim();
    }

    public double getProductPrice(String productName) {
        String rawPrice = xpathHelper(productName).findElement(productPrice).getText().trim().replace("$", "");
        return Double.parseDouble(rawPrice);
    }

    public void addProductToCart(String productName) {
        xpathHelper(productName).findElement(addToCartBtn).click();
    }

    public void removeProductFromCart(String productName) {
        xpathHelper(productName).findElement(removeFromCartBtn).click();
    }

    public void goToProductDetails(String productName) {
        xpathHelper(productName).click();
    }

    // Bulk actions
    public int getProductsCount() {
        List <WebElement> totalProducts = driver.findElements(productContainer);
        return totalProducts.size();
    }

    public List <String> getAllProductsNames() {
        return getTextFromElements(productName);
    }

    public List<String> getAllProductsDescriptions() {
        return getTextFromElements(productDescription);
    }


    public List<Double> getAllProductsPrices() {
        return getPricesFromElements(productPrice);
    }

    //Helper method for actions targeting a specific product
    private WebElement xpathHelper(String itemName) {
        String productXpath = "//div[@data-test='inventory-item'][.//div[text()='" + itemName + "']]";
        return driver.findElement(By.xpath(productXpath));
    }
}
