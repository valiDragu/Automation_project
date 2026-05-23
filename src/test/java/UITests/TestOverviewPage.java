package UITests;

import base.BaseTest;
import base.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;

public class TestOverviewPage extends BaseTest {
    @Test
    public void testOverviewPageIsReachable() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.openCart();
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(DriverFactory.getDriver());
        checkout.fillForm(".", ".", ".");

        String pageUrl = DriverFactory.getDriver().getCurrentUrl();
        String pageName = cartPage.getPageName();

        Assert.assertEquals(pageName, "Checkout: Overview", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/checkout-step-two.html", "Redirected to: " + pageUrl);
    }

    @Test
    public void testCancelFunctionality() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.openCart();
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(DriverFactory.getDriver());
        checkout.fillForm(".", ".", ".");

        OverviewPage overview = new OverviewPage(DriverFactory.getDriver());
        overview.cancelToInventory();

        String pageUrl = DriverFactory.getDriver().getCurrentUrl();
        String pageName = cartPage.getPageName();

        Assert.assertEquals(pageName, "Products", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/inventory.html", "Redirected to: " + pageUrl);
    }

    @Test
    public void testFinishFunctionality() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.openCart();
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(DriverFactory.getDriver());
        checkout.fillForm(".", ".", ".");

        OverviewPage overview = new OverviewPage(DriverFactory.getDriver());
        overview.finish();

        String pageUrl = DriverFactory.getDriver().getCurrentUrl();
        String pageName = cartPage.getPageName();

        Assert.assertEquals(pageName, "Checkout: Complete!", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/checkout-complete.html", "Redirected to: " + pageUrl);
    }


    @Test
    public void testAllItemsAreAdded() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        BasePage basePage = new BasePage(DriverFactory.getDriver());
        Assert.assertEquals(basePage.getPageName(), "Products");

        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
        List<Double> productsPrices = productsPage.getAllProductsPrices();
        productsPage.addAllProductsToCart();
        productsPage.openCart();

        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.checkOut();

        CheckOutPage checkOut = new CheckOutPage(DriverFactory.getDriver());
        checkOut.fillForm("John", "Doe", "1234");

        OverviewPage overview = new OverviewPage(DriverFactory.getDriver());
        Assert.assertTrue(overview.isItemDisplayed());

        List<Double> overviewPrices = overview.getAllProductsPrices();

        Assert.assertEquals(overviewPrices, productsPrices, "Product prices list does not match overview prices list");
    }

    @Test
    public void testTotalPriceIsCorrect() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        BasePage basePage = new BasePage(DriverFactory.getDriver());
        Assert.assertEquals(basePage.getPageName(), "Products");

        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
        productsPage.addAllProductsToCart();
        productsPage.openCart();

        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.checkOut();

        CheckOutPage checkOut = new CheckOutPage(DriverFactory.getDriver());
        checkOut.fillForm("John", "Doe", "1234");

        OverviewPage overview = new OverviewPage(DriverFactory.getDriver());
        Assert.assertTrue(overview.isItemDisplayed());

        double itemTotal = overview.calculateItemTotal();
        double total = overview.calculateTotal();
        double displayedItemTotal = overview.getItemTotal();
        double displayedTotal = overview.getTotal();

        Assert.assertEquals(displayedItemTotal, itemTotal, "Actual items total price is: " + displayedItemTotal);
        Assert.assertEquals(displayedTotal, total, "Actual total price is: " + displayedTotal);
    }
}
