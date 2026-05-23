package UITests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

public class TestBurgerMenu extends BaseTest {
    @Test
    public void testResetAppStateButton() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        String productName = "Sauce Labs Bolt T-Shirt";
        productsPage.addProductToCart(productName);

        Assert.assertTrue(productsPage.isCartBadgeDisplayed(), "Product was not added to cart");

        productsPage.openSidePanel();
        productsPage.clickResetAppState();
        productsPage.closeSidePanel();

        Assert.assertFalse(productsPage.isCartBadgeDisplayed(), "Reset App button not working");
    }


    @Test
    public void testAllItemsButton() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(driver);
        cartPage.openSidePanel();
        cartPage.clickAllItems();

        String pageName = cartPage.getPageName();
        String pageUrl = driver.getCurrentUrl();

        Assert.assertEquals(pageName, "Products", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl,  "https://www.saucedemo.com/inventory.html", "All Items button not working");
    }


    @Test
    public void testLogoutButton() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openSidePanel();
        productsPage.clickLogout();

        String pageUrl = driver.getCurrentUrl();

        Assert.assertEquals(pageUrl,  "https://www.saucedemo.com/", "Logout button not working");
    }

    @Test
    public void testAboutButton() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openSidePanel();
        productsPage.clickAbout();

        String pageUrl = driver.getCurrentUrl();

        Assert.assertEquals(pageUrl,  "https://saucelabs.com/", "Logout button not working");
    }
}
