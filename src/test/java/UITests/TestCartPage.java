package UITests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.util.List;

public class TestCartPage extends BaseTest {
    @Test
    public void testCartPageIsReachable() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        String pageUrl = driver.getCurrentUrl();
        String pageName = cartPage.getPageName();

        Assert.assertEquals(pageName, "Your Cart", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/cart.html", "Redirected to: " + pageUrl);
    }


    @Test
    public void testContinueShoppingButton() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.backToProducts();
        String pageUrl = driver.getCurrentUrl();

        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/inventory.html", "Redirected to: " + pageUrl);

    }


    @Test
    public void testCheckoutButton() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.checkOut();
        String pageName = cartPage.getPageName();
        String pageUrl = driver.getCurrentUrl();

        Assert.assertEquals(pageName, "Checkout: Your Information", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl,  "https://www.saucedemo.com/checkout-step-one.html", "Redirected to: " + pageUrl);
    }


    @Test
    public void testCorrectProductIsAdded() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        String productName = "Sauce Labs Bolt T-Shirt";
        String productDescription  = productsPage.getProductDescription(productName);
        double productPrice = productsPage.getProductPrice(productName);
        productsPage.addProductToCart(productName);
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);
        String cartItemName = cartPage.getCartItemName();
        String cartItemDescription  = cartPage.getCartItemDescription();
        double cartItemPrice = cartPage.getCartItemPrice();
        int numberOfItems = cartPage.getCartItemCount();
        int numberOfItemsOnBadge = cartPage.getCartBadgeCount();

        Assert.assertEquals(cartItemName, productName, "Incorrect cartItemName: " + cartItemName);
        Assert.assertEquals(cartItemDescription, productDescription, "Incorrect cartItemDescription: " + cartItemDescription);
        Assert.assertEquals(cartItemPrice, productPrice, "Incorrect cartItemPrice: " + cartItemPrice);
        Assert.assertEquals(numberOfItems, numberOfItemsOnBadge, "Incorrect numberOfItemsOnBadge: " + numberOfItemsOnBadge);
    }

    @Test
    public void testRedirectToProductDetails() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        String productName = "Sauce Labs Bolt T-Shirt";
        productsPage.addProductToCart(productName);
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertFalse(cartPage.getCartItemName().isEmpty(), "No products available in cart");

        cartPage.clickItemName();
        String pageUrl = driver.getCurrentUrl();

        Assert.assertTrue(pageUrl.contains("https://www.saucedemo.com/inventory-item.html"), "Redirected to: " + pageUrl);
    }

    @Test
    public void testThatProductIsRemoved() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCart("Sauce Labs Bike Light");
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertFalse(cartPage.isCartItemRemoved(), "Cart is empty");

        cartPage.removeFromCart();
        Assert.assertTrue(cartPage.isCartItemRemoved(), "Cart is not empty");
    }

    @Test
    public void testAllProductAreCorrectlyAdded() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        BasePage basePage = new BasePage(driver);
        Assert.assertEquals(basePage.getPageName(), "Products");

        ProductsPage productsPage = new ProductsPage(driver);
        List<String> productsNames = productsPage.getAllProductsNames();
        List<String> productsDescriptions = productsPage.getAllProductsDescriptions();
        List<Double> productsPrices = productsPage.getAllProductsPrices();
        productsPage.addAllProductsToCart();

        CartPage cartPage = new CartPage(driver);
        List<String> cartItemsNames = cartPage.getAllCartItemsNames();
        List<String> cartItemsDescriptions = cartPage.getAllCartItemsDescriptions();
        List<Double> cartItemsPrices = cartPage.getAllCartItemsPrices();

        Assert.assertEquals(cartItemsNames,productsNames, "Product names list does not match cart names list");
        Assert.assertEquals(cartItemsDescriptions, productsDescriptions, "Product descriptions list does not match cart description list");
        Assert.assertEquals(cartItemsPrices, productsPrices, "Product prices list does not match cart prices list");
    }
}
