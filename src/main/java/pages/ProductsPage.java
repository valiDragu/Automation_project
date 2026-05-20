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
    private By productName = By.xpath("//div[@data-test='inventory-item-name']");
    private By productPrice = By.xpath("//div[@data-test='inventory-item-price']");
    private By productDescription = By.xpath("//div[@data-test='inventory-item-desc']");
    private By addToCartBtn = By.xpath("//button[contains(@id, 'add-to-cart')]");
    private By removeFromCartBtn = By.xpath("//button[contains(@id, 'remove')]");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectFilter(String option) {
        Select select = new Select(driver.findElement(filterBtn));
        select.selectByVisibleText(option);
    }

    public String getFilterName() {
        return driver.findElement(filterName).getText().trim();
    }

    public String getProductName(int itemIndex) {
        String productNamePath = productName.toString().replace("By.xpath: ", "");
        String indexedProductName = "(" + productNamePath + ")[" + itemIndex + "]";
        return driver.findElement(By.xpath(indexedProductName)).getText().trim();
    }

    public String getProductDescription(int itemIndex) {
        String productDescriptionPath = productDescription.toString().replace("By.xpath: ", "");
        String indexedProductDescription = "(" + productDescription + ")[" + itemIndex + "]";
        return driver.findElement(By.xpath(indexedProductDescription)).getText().trim();
    }

    public double getProductPrice(int itemIndex) {
        String productPricePath = productPrice.toString().replace("By.xpath: ", "");
        String indexedProductPrice = "(" + productPricePath + ")[" + itemIndex + "]";
        String price = driver.findElement(By.xpath(indexedProductPrice)).getText().trim();
        price.replace("$", "");
        double finalPrice = Double.parseDouble(price);
        return finalPrice;
    }

    public void addProductToCart(int itemIndex) {
        String productCartBtn = addToCartBtn.toString().replace("By.xpath: ", "");
        String indexedCartBtn = "(" + addToCartBtn + ")[" + itemIndex + "]";
    }

    public void removeProductFromCart(int itemIndex) {
        String productCartBtnR = removeFromCartBtn.toString().replace("By.xpath: ", "");
        String indexedCartBtnR = "(" + removeFromCartBtn + ")[" + itemIndex + "]";
    }

    public int getProductsCount() {
        List <WebElement> totalProducts = driver.findElements(productName);
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

    public List <String> filterNamesASC() {
        List <String> namesASC = new ArrayList<>(getAllProductsNames());
        Collections.sort(namesASC);
        return namesASC;
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

    public List<Double> filterPriceASC() {
        List <Double> pricesASC = new ArrayList<>(getAllProductsPrices());
        Collections.sort(pricesASC);
        return  pricesASC;
    }
}
