package UITests;

import base.BaseTest;
import base.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class TestProductDetailsPage extends BaseTest {
    @Test
    public void testDetailsPageIsReachable() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
        productsPage.goToProductDetails("Sauce Labs Fleece Jacket");

        ProductDetailsPage detailsPage = new ProductDetailsPage(DriverFactory.getDriver());
        String pageUrl = DriverFactory.getDriver().getCurrentUrl();

        Assert.assertTrue(pageUrl.contains("https://www.saucedemo.com/inventory-item.html"), "Redirected to: " + pageUrl);
    }


    @Test
    public void testBackToProducts() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
        productsPage.goToProductDetails("Sauce Labs Fleece Jacket");

        ProductDetailsPage detailsPage = new ProductDetailsPage(DriverFactory.getDriver());
        detailsPage.returnToProductsPage();
        String pageName = detailsPage.getPageName();
        String pageUrl = DriverFactory.getDriver().getCurrentUrl();

        Assert.assertEquals(pageName, "Products", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl,  "https://www.saucedemo.com/inventory.html", "Redirected to: " + pageUrl);
    }


    @Test
    public void testRedirectToCorrectProduct() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
        String productName = "Sauce Labs Bike Light";
        String productDescription  = productsPage.getProductDescription(productName);
        double productPrice = productsPage.getProductPrice(productName);
        productsPage.goToProductDetails(productName);

        ProductDetailsPage detailsPage = new ProductDetailsPage(DriverFactory.getDriver());
        String detailItemName = detailsPage.getProductName();
        String detailItemDescription  = detailsPage.getProductDescription();
        double detailItemPrice = detailsPage.getProductPrice();

        Assert.assertEquals(detailItemName, productName, "Incorrect detailItemName: " + detailItemName);
        Assert.assertEquals(detailItemDescription, productDescription, "Incorrect detailItemDescription: " + detailItemDescription);
        Assert.assertEquals(detailItemPrice, productPrice, "Incorrect detailItemPrice: " + detailItemPrice);
    }


    @Test
    public void detailsAddToCart() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
        String productName = "Sauce Labs Onesie";
        productsPage.goToProductDetails(productName);

        ProductDetailsPage detailsPage = new ProductDetailsPage(DriverFactory.getDriver());
        detailsPage.detailsAddToCart();

        Assert.assertTrue(detailsPage.isCartBadgeDisplayed(), "Cart is empty");
        Assert.assertEquals(detailsPage.getCartBadgeCount(), 1, "The product was added to cart");
    }


    @Test
    public void detailsRemoveFromCart() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
        String productName = "Sauce Labs Onesie";
        productsPage.goToProductDetails(productName);

        ProductDetailsPage detailsPage = new ProductDetailsPage(DriverFactory.getDriver());
        detailsPage.detailsAddToCart();
        detailsPage.detailsRemoveFromCart();

        Assert.assertFalse(detailsPage.isCartBadgeDisplayed(), "The product was not removed from cart");
    }
}
