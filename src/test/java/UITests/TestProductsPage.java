package UITests;

import base.BaseTest;
import base.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.ProductsPage;

import java.util.Collections;
import java.util.List;

public class TestProductsPage extends BaseTest {
    @Test
    public void testRedirectToProductsPageAfterLogin() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        BasePage basePage = new BasePage(DriverFactory.getDriver());
        String pageName = basePage.getPageName();
        String pageUrl = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertEquals(pageName, "Products", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl,  "https://www.saucedemo.com/inventory.html", "Redirected to: " + pageUrl);
    }


//    @Test
//    public void testFilterNameDesc() {
//        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
//        loginPage.loginUser("standard_user", "secret_sauce");
//
//        BasePage basePage = new BasePage(DriverFactory.getDriver());
//        Assert.assertEquals(basePage.getPageName(), "Products");
//
//        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
//        productsPage.selectFilter("Name (Z to A)");
//
//        List<String> actualDisplayedProducts = productsPage.getAllProductsNames();
//        List<String> expectedDisplayedProducts = productsPage.filterNamesASC();
//        expectedDisplayedProducts.sort(Collections.reverseOrder());
//
//
//        String filterName = productsPage.getFilterName();
//        Assert.assertEquals(filterName, "Name (Z to A)", "Actual filter name is: " + filterName);
//        Assert.assertEquals(actualDisplayedProducts, expectedDisplayedProducts, "Filter error");
//    }


//    @Test
//    public void testFilterNameAsc() {
//        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
//        loginPage.loginUser("standard_user", "secret_sauce");
//
//        BasePage basePage = new BasePage(DriverFactory.getDriver());
//        Assert.assertEquals(basePage.getPageName(), "Products");
//
//        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
//        productsPage.selectFilter("Name (A to Z)");
//
//        List<String> actualDisplayedProducts = productsPage.getAllProductsNames();
//        List<String> expectedDisplayedProducts = productsPage.filterNamesASC();
//        Collections.sort(expectedDisplayedProducts);
//
//        String filterName = productsPage.getFilterName();
//        Assert.assertEquals(filterName, "Name (A to Z)", "Actual filter name is: " + filterName);
//        Assert.assertEquals(actualDisplayedProducts, expectedDisplayedProducts, "Filter error");
//    }


//    @Test
//    public void testFilterPriceLowToHigh() {
//        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
//        loginPage.loginUser("standard_user", "secret_sauce");
//
//        BasePage basePage = new BasePage(DriverFactory.getDriver());
//        Assert.assertEquals(basePage.getPageName(), "Products");
//
//        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
//        productsPage.selectFilter("Price (low to high)");
//
//        List<Double> actualDisplayedPrices = productsPage.getAllProductsPrices();
//        List<Double> expectedDisplayedPrices = productsPage.filterPriceASC();
//        Collections.sort(expectedDisplayedPrices);
//
//        String filterName = productsPage.getFilterName();
//        Assert.assertEquals(filterName, "Price (low to high)", "Actual filter name is: " + filterName);
//        Assert.assertEquals(actualDisplayedPrices, expectedDisplayedPrices, "Filter error");
//    }


//    @Test
//    public void testFilterPriceHighToLow() {
//        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
//        loginPage.loginUser("standard_user", "secret_sauce");
//
//        BasePage basePage = new BasePage(DriverFactory.getDriver());
//        Assert.assertEquals(basePage.getPageName(), "Products");
//
//        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
//        productsPage.selectFilter("Price (high to low)");
//
//        List<Double> actualDisplayedPrices = productsPage.getAllProductsPrices();
//        List<Double> expectedDisplayedPrices = productsPage.filterPriceASC();
//        expectedDisplayedPrices.sort(Collections.reverseOrder());
//
//        String filterName = productsPage.getFilterName();
//        Assert.assertEquals(filterName, "Price (high to low)", "Actual filter name is: " + filterName);
//        Assert.assertEquals(actualDisplayedPrices, expectedDisplayedPrices, "Filter error");
//    }


    @Test
    public void testAddToCartWorksForAllProducts() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        BasePage basePage = new BasePage(DriverFactory.getDriver());
        Assert.assertEquals(basePage.getPageName(), "Products");

        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
        int numberOfListedProducts = productsPage.getProductsCount();
        productsPage.addAllProductsToCart();
        int numberOfItemsShownOnCartBadge = productsPage.getCartBadgeCount();

        Assert.assertTrue(productsPage.isCartBadgeDisplayed(), "Cart item counter is not present");
        Assert.assertEquals(numberOfListedProducts, numberOfItemsShownOnCartBadge, "Actual number of cart items is " + numberOfItemsShownOnCartBadge + "instead of " + numberOfListedProducts);
    }


    @Test
    public void testRemoveFromCartWorksForAllProducts() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        BasePage basePage = new BasePage(DriverFactory.getDriver());
        Assert.assertEquals(basePage.getPageName(), "Products");

        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
        productsPage.addAllProductsToCart();
        productsPage.removeProductsFromCart();

        Assert.assertFalse(productsPage.isCartBadgeDisplayed(), "Not all products were removed from cart");
    }

}
