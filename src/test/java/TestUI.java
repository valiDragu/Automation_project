import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.ProductsPage;

import java.util.Collections;
import java.util.List;

public class TestUI extends BaseTest {

    @Test
    public void Test1() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername("standard_user");
        loginPage.sendPassword("secret_sauce");
        loginPage.login();

        Assert.assertEquals(driver.getTitle(), "Products");
    }

    @Test
    public void Test2() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername("standard_user");
        loginPage.sendPassword("secret_sauce");
        loginPage.login();

        BasePage basePage = new BasePage(driver);
        basePage.openSidePanel();
        basePage.clickAllItems();
    }

    @Test
    public void Test3() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername("standard_user");
        loginPage.sendPassword("secret_sauce");
        loginPage.login();

        BasePage basePage = new BasePage(driver);
        Assert.assertEquals(basePage.getPageName(), "Products");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectFilter("Name (Z to A)");

        List<String> actualDisplayedProducts = productsPage.getAllProductsNames();
        List<String> expectedDisplayedProducts = productsPage.fiterNamesASC();
        Collections.sort(expectedDisplayedProducts, Collections.reverseOrder());

        Assert.assertEquals(actualDisplayedProducts, expectedDisplayedProducts, "Filter error");
    }

    @Test
    public void Test4() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername("standard_user");
        loginPage.sendPassword("secret_sauce");
        loginPage.login();

        BasePage basePage = new BasePage(driver);
        Assert.assertEquals(basePage.getPageName(), "Products");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectFilter("Name (A to Z)");

        List<String> actualDisplayedProducts = productsPage.getAllProductsNames();
        List<String> expectedDisplayedProducts = productsPage.fiterNamesASC();
        Collections.sort(expectedDisplayedProducts);

        Assert.assertEquals(actualDisplayedProducts, expectedDisplayedProducts, "Filter error");
    }

    @Test
    public void Test5() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername("standard_user");
        loginPage.sendPassword("secret_sauce");
        loginPage.login();

        BasePage basePage = new BasePage(driver);
        Assert.assertEquals(basePage.getPageName(), "Products");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectFilter("Price (low to high)");

        List<Double> actualDisplayedPrices = productsPage.getAllProductsPrices();
        List<Double> expectedDisplayedPrices = productsPage.filterPriceASC();
        Collections.sort(expectedDisplayedPrices);

        Assert.assertEquals(actualDisplayedPrices, expectedDisplayedPrices, "Filter error");
    }

    @Test
    public void Test6() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername("standard_user");
        loginPage.sendPassword("secret_sauce");
        loginPage.login();

        BasePage basePage = new BasePage(driver);
        Assert.assertEquals(basePage.getPageName(), "Products");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectFilter("Price (high to low)");

        List<Double> actualDisplayedPrices = productsPage.getAllProductsPrices();
        List<Double> expectedDisplayedPrices = productsPage.filterPriceASC();
        Collections.sort(expectedDisplayedPrices, Collections.reverseOrder());

        Assert.assertEquals(actualDisplayedPrices, expectedDisplayedPrices, "Filter error");
    }
}
