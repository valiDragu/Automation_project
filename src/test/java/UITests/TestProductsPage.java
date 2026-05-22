package UITests;

import base.BaseTest;
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
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        BasePage basePage = new BasePage(driver);
        String pageName = basePage.getPageName();
        String pageUrl = driver.getCurrentUrl();
        Assert.assertEquals(pageName, "Products", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl,  "https://www.saucedemo.com/inventory.html", "Redirected to: " + pageUrl);

    }

    @Test
    public void testFilterNameDesc() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        BasePage basePage = new BasePage(driver);
        Assert.assertEquals(basePage.getPageName(), "Products");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectFilter("Name (Z to A)");

        List<String> actualDisplayedProducts = productsPage.getAllProductsNames();
        List<String> expectedDisplayedProducts = productsPage.filterNamesASC();
        expectedDisplayedProducts.sort(Collections.reverseOrder());


        String filterName = productsPage.getFilterName();
        Assert.assertEquals(filterName, "Name (Z to A)", "Actual filter name is: " + filterName);
        Assert.assertEquals(actualDisplayedProducts, expectedDisplayedProducts, "Filter error");
    }

    @Test
    public void testFilterNameAsc() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        BasePage basePage = new BasePage(driver);
        Assert.assertEquals(basePage.getPageName(), "Products");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectFilter("Name (A to Z)");

        List<String> actualDisplayedProducts = productsPage.getAllProductsNames();
        List<String> expectedDisplayedProducts = productsPage.filterNamesASC();
        Collections.sort(expectedDisplayedProducts);

        String filterName = productsPage.getFilterName();
        Assert.assertEquals(filterName, "Name (A to Z)", "Actual filter name is: " + filterName);
        Assert.assertEquals(actualDisplayedProducts, expectedDisplayedProducts, "Filter error");
    }

    @Test
    public void testFilterPriceLowToHigh() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        BasePage basePage = new BasePage(driver);
        Assert.assertEquals(basePage.getPageName(), "Products");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectFilter("Price (low to high)");

        List<Double> actualDisplayedPrices = productsPage.getAllProductsPrices();
        List<Double> expectedDisplayedPrices = productsPage.filterPriceASC();
        Collections.sort(expectedDisplayedPrices);

        String filterName = productsPage.getFilterName();
        Assert.assertEquals(filterName, "Price (low to high)", "Actual filter name is: " + filterName);
        Assert.assertEquals(actualDisplayedPrices, expectedDisplayedPrices, "Filter error");
    }

    @Test
    public void testFilterPriceHighToLow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        BasePage basePage = new BasePage(driver);
        Assert.assertEquals(basePage.getPageName(), "Products");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectFilter("Price (high to low)");

        List<Double> actualDisplayedPrices = productsPage.getAllProductsPrices();
        List<Double> expectedDisplayedPrices = productsPage.filterPriceASC();
        expectedDisplayedPrices.sort(Collections.reverseOrder());

        String filterName = productsPage.getFilterName();
        Assert.assertEquals(filterName, "Price (high to low)", "Actual filter name is: " + filterName);
        Assert.assertEquals(actualDisplayedPrices, expectedDisplayedPrices, "Filter error");
    }

}
