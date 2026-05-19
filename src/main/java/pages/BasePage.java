package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    private WebDriver driver;

    private By cartBtn = By.xpath("//a[@data-test='shopping-cart-link']");
    private By pageTitle = By.xpath("//span[@data-test='title']");
    private By sidePanelBtn = By.xpath("//button[@id='react-burger-menu-btn']");
    private By closeSidePanelBtn = By.xpath("//button[@id='react-burger-cross-btn']");
    private By allItemsBtn = By.xpath("//a[@data-test='inventory-sidebar-link']");
    private By aboutBtn = By.xpath("//a[@data-test='about-sidebar-link']");
    private By logoutBtn = By.xpath("//a[@data-test='logout-sidebar-link']");
    private By resetAppStateBtn = By.xpath("//a[@data-test='reset-sidebar-link']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openCart() {
        driver.findElement(cartBtn).click();
    }

    public String getPageName() {
        return driver.findElement(pageTitle).getText().trim();
    }

    public void openSidePanel() {
        driver.findElement(sidePanelBtn).click();
    }

    public void closeSidePanel() {
        driver.findElement(closeSidePanelBtn).click();
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
}
