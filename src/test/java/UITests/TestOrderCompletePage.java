package UITests;

import base.BaseTest;
import base.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class TestOrderCompletePage extends BaseTest {
    @Test
    public void testEndToEnd() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginUser("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
        productsPage.addProductToCart("Sauce Labs Bike Light");
        productsPage.openCart();

        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.checkOut();

        CheckOutPage checkout = new CheckOutPage(DriverFactory.getDriver());
        checkout.fillForm("John", "Doe", "12234");

        OverviewPage overview = new OverviewPage(DriverFactory.getDriver());
        overview.finish();

        OrderCompletePage orderComplete = new OrderCompletePage(DriverFactory.getDriver());
        String pageUrl = DriverFactory.getDriver().getCurrentUrl();
        String pageName = cartPage.getPageName();
        String header = orderComplete.getCompleteHeader();
        String message = orderComplete.getCompleteText();

        Assert.assertEquals(pageName, "Checkout: Complete!", "Actual page name is: " + pageName);
        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/checkout-complete.html", "Redirected to: " + pageUrl);
        Assert.assertEquals(header, "Thank you for your order!", "Actual page header is: " + header);
        Assert.assertEquals(message, "Your order has been dispatched, and will arrive just as fast as the pony can get there!", "Actual page message is: " + message);

    }
}
