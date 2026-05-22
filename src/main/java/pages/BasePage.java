package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BasePage {
    protected WebDriver driver;

    private By logo = By.xpath("//div[@class='app_logo']");
    private By pageTitle = By.xpath("//span[@data-test='title']");
    private By cartBtn = By.xpath("//a[@data-test='shopping-cart-link']");
    private By cartBtnBadge = By.xpath("//span[@data-test='shopping-cart-badge']");
    private By sidePanelBtn = By.xpath("//button[@id='react-burger-menu-btn']");
    private By closeSidePanelBtn = By.xpath("//button[@id='react-burger-cross-btn']");
    private By allItemsBtn = By.xpath("//a[@data-test='inventory-sidebar-link']");
    private By aboutBtn = By.xpath("//a[@data-test='about-sidebar-link']");
    private By logoutBtn = By.xpath("//a[@data-test='logout-sidebar-link']");
    private By resetAppStateBtn = By.xpath("//a[@data-test='reset-sidebar-link']");


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLogo() {
        return driver.findElement(logo).getText();
    }

    public String getPageName() {
        return driver.findElement(pageTitle).getText().trim();
    }

    public boolean isCartBadgeDisplayed() {
        List<WebElement> badge = driver.findElements(cartBtnBadge);
        return !badge.isEmpty() && badge.getFirst().isDisplayed();
    }

    public int getCartBadgeCount() {
        try {
            String rawCartCount = driver.findElement(cartBtnBadge).getText().trim();
            return Integer.parseInt(rawCartCount);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return 0;
        }
    }

    public void openCart() {
        driver.findElement(cartBtn).click();
    }

    public void openSidePanel() {
        driver.findElement(sidePanelBtn).click();
    }

    public void clickAllItems() {
        driver.findElement(allItemsBtn).click();
    }

    public void clickAbout() {
        driver.findElement(aboutBtn).click();
    }

    public void clickLogout() {
        driver.findElement(logoutBtn).click();
    }

    public void clickResetAppState() {
        driver.findElement(resetAppStateBtn).click();
    }

    public void closeSidePanel() {
        driver.findElement(closeSidePanelBtn).click();
    }

    protected double cleanAndParsePrice(String rawPrice) {
        return StringUtils.cleanAndParsePrice(rawPrice);
    }

    protected List<String> getTextFromElements(By locator) {
        List<String> texts = new ArrayList<>();
        for (WebElement element : driver.findElements(locator)) {
            texts.add(element.getText().trim());
        }
        return texts;
    }

    protected List<Double> getPricesFromElements(By locator) {
        List<Double> priceList = new ArrayList<>();
        List<WebElement> elements = driver.findElements(locator);

        for (WebElement element : elements) {
            double price = StringUtils.cleanAndParsePrice(element.getText());
            priceList.add(price);
        }
        return priceList;
    }
}
