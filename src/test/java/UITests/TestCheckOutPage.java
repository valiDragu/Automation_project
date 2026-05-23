package UITests;

import base.BaseTest;
import base.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class TestCheckOutPage extends BaseTest {
    @Test
    public void testCheckOutPageIsReachable() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.openCart();
        cartPage.checkOut();
        String pageUrl = DriverFactory.getDriver().getCurrentUrl();
        String pageName = cartPage.getPageName();

        Assert.assertEquals(pageName, "Checkout: Your Information", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/checkout-step-one.html", "Redirected to: " + pageUrl);
    }


    @Test
    public void testCancel() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.openCart();
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(DriverFactory.getDriver());
        checkout.cancelToCart();
        String pageUrl = DriverFactory.getDriver().getCurrentUrl();
        String pageName = cartPage.getPageName();

        Assert.assertEquals(pageName, "Your Cart", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/cart.html", "Redirected to: " + pageUrl);
    }


    @Test
    public void testContinueWithMissingFormData() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.openCart();
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(DriverFactory.getDriver());
        checkout.continueShopping();

        Assert.assertTrue(checkout.isErrorMessageDisplayed(), "Error message not received");

        String errorMessage = checkout.getErrorMessage();
        Assert.assertEquals(errorMessage, "Error: First Name is required", "Actual received error: "  + errorMessage);
    }


    @Test
    public void testContinueWithMissingLastName() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.openCart();
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(DriverFactory.getDriver());
        checkout.fillForm(".", "", ".");

        Assert.assertTrue(checkout.isErrorMessageDisplayed(), "Error message not received");

        String errorMessage = checkout.getErrorMessage();
        Assert.assertEquals(errorMessage, "Error: Last Name is required", "Actual received error: "  + errorMessage);
    }


    @Test
    public void testContinueWithMissingZipCode() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.openCart();
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(DriverFactory.getDriver());
        checkout.fillForm(".", ".", "");

        Assert.assertTrue(checkout.isErrorMessageDisplayed(), "Error message not received");

        String errorMessage = checkout.getErrorMessage();
        Assert.assertEquals(errorMessage, "Error: Postal Code is required", "Actual received error: "  + errorMessage);
    }

    @Test
    public void testDismissErrorAndResubmitFormWithValidData() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.openCart();
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(DriverFactory.getDriver());
        checkout.continueShopping();

        Assert.assertTrue(checkout.isErrorMessageDisplayed(), "Error message not received");

        checkout.closeError();
        checkout.fillForm("John", "Doe", "12345");
        String pageUrl = DriverFactory.getDriver().getCurrentUrl();
        String pageName = cartPage.getPageName();

        Assert.assertEquals(pageName, "Checkout: Overview", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/checkout-step-two.html", "Redirected to: " + pageUrl);
    }
}
