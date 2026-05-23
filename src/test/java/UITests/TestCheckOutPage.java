package UITests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class TestCheckOutPage extends BaseTest {
    @Test
    public void testCheckOutPageIsReachable() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.checkOut();
        String pageUrl = driver.getCurrentUrl();
        String pageName = cartPage.getPageName();

        Assert.assertEquals(pageName, "Checkout: Your Information", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/checkout-step-one.html", "Redirected to: " + pageUrl);
    }


    @Test
    public void testCancel() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(driver);
        checkout.cancelToCart();
        String pageUrl = driver.getCurrentUrl();
        String pageName = cartPage.getPageName();

        Assert.assertEquals(pageName, "Your Cart", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/cart.html", "Redirected to: " + pageUrl);
    }


    @Test
    public void testContinueWithMissingFormData() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(driver);
        checkout.continueShopping();

        Assert.assertTrue(checkout.isErrorMessageDisplayed(), "Error message not received");

        String errorMessage = checkout.getErrorMessage();
        Assert.assertEquals(errorMessage, "Error: First Name is required", "Actual received error: "  + errorMessage);
    }


    @Test
    public void testContinueWithMissingLastName() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(driver);
        checkout.fillForm(".", "", ".");

        Assert.assertTrue(checkout.isErrorMessageDisplayed(), "Error message not received");

        String errorMessage = checkout.getErrorMessage();
        Assert.assertEquals(errorMessage, "Error: Last Name is required", "Actual received error: "  + errorMessage);
    }


    @Test
    public void testContinueWithMissingZipCode() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(driver);
        checkout.fillForm(".", ".", "");

        Assert.assertTrue(checkout.isErrorMessageDisplayed(), "Error message not received");

        String errorMessage = checkout.getErrorMessage();
        Assert.assertEquals(errorMessage, "Error: Postal Code is required", "Actual received error: "  + errorMessage);
    }

    @Test
    public void testDismissErrorAndResubmitFormWithValidData() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(driver);
        checkout.continueShopping();

        Assert.assertTrue(checkout.isErrorMessageDisplayed(), "Error message not received");

        checkout.closeError();
        checkout.fillForm("John", "Doe", "12345");
        String pageUrl = driver.getCurrentUrl();
        String pageName = cartPage.getPageName();

        Assert.assertEquals(pageName, "Checkout: Overview", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/checkout-step-two.html", "Redirected to: " + pageUrl);
    }
}
