package UITests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class TestOrderCompletePage extends BaseTest {
    @Test
    public void testEndToEnd() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCart("Sauce Labs Bike Light");
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(driver);
        checkout.fillForm("John", "Doe", "12234");

        OverviewPage overview = new OverviewPage(driver);
        overview.finish();

        OrderCompletePage orderComplete = new OrderCompletePage(driver);
        String pageUrl = driver.getCurrentUrl();
        String pageName = cartPage.getPageName();
        String header = orderComplete.getCompleteHeader();
        String message = orderComplete.getCompleteText();

        Assert.assertEquals(pageName, "Checkout: Complete!", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/checkout-complete.html", "Redirected to: " + pageUrl);
        Assert.assertEquals(header, "Thank you for your order!", "Actual page header is: " + header);
        Assert.assertEquals(message, "Your order has been dispatched, and will arrive just as fast as the pony can get there!", "Actual page message is: " + message);

    }
}
