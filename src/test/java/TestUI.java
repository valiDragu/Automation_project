import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.ProductsPage;

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
        productsPage.selectFilterOption("Price (low to high)");

        String firstProduct = productsPage.getProductName(1);
        String firstProductPrice = productsPage.getProductPrice(firstProduct);
        String secondProduct = productsPage.getProductName(2);
        String secondProductPrice = productsPage.getProductPrice(secondProduct);

        Assert.assertTrue(firstProductPrice.compareTo(secondProductPrice) <= 0, "Low-to-high filter not working");


    }
}
