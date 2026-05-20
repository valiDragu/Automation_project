package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;

    private By filterBtn = By.xpath("//select[@data-test='product-sort-container']");
    private By filterName = By.xpath("//span[@data-test='active-option']");
    private By productContainer = By.xpath("//div[@data-test='inventory-item']");
    private By productName = By.xpath("//div[@data-test='inventory-item-name']");
    private By productPrice = By.xpath("//div[@data-test='inventory-item-price']");
    private By productDescription = By.xpath("//div[@data-test='inventory-item-desc']");
    private By addToCartBtn = By.xpath("//button[contains(@id, 'add-to-cart')]");
    private By removeFromCartBtn = By.xpath("//button[contains(@id, 'remove')]");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
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

    // Bulk actions
    public int getProductsCount() {
        List <WebElement> totalProducts = driver.findElements(productContainer);
        return totalProducts.size();
    }

    public List <String> getAllProductsNames() {
        List <String> productNames = new ArrayList<>();
        List <WebElement> products = driver.findElements(productName);
        for (WebElement product : products) {
            productNames.add(product.getText().trim());
        }
        return productNames;
    }


    public List<Double> getAllProductsPrices() {
        List <Double> productPrices = new ArrayList<>();
        List<WebElement> prices = driver.findElements(productPrice);

        for(WebElement price : prices) {
            String stringPrice = price.getText().trim();
            String cutPrice = stringPrice.replace("$", "");
            Double doublePrice = Double.parseDouble(cutPrice);
            productPrices.add(doublePrice);
        }
        return productPrices;
    }

    //Helper method for actions targeting specific products
    private WebElement xpathHelper(String itemName) {
        String productXpath = "//div[@data-test='inventory-item'][.//div[text()='" + itemName + "']]";
        return driver.findElement(By.xpath(productXpath));
    }
}
