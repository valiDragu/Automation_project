package UITests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;

public class TestLoginPage extends BaseTest {
    @Test
    public void testLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");

        BasePage basePage = new BasePage(driver);
        String pageName = basePage.getPageName();
        Assert.assertEquals(pageName, "Products");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }


    @Test
    public void testFailsWhenUsernameIsNotProvided() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("", "secret_sauce");

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(errorMessage,"Epic sadface: Username is required");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }


    @Test
    public void testFailsWhenPasswordIsNotProvided() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("standard_user", "");

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(errorMessage,"Epic sadface: Password is required");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }


    @Test
    public void testFailsWhenCredentialsAreInvalid() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("test", "test");

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(errorMessage,"Epic sadface: Username and password do not match any user in this service");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }


    @Test
    public void testFailsWhenUsingLockedUpUserCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("locked_out_user", "secret_sauce");

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(errorMessage,"Epic sadface: Sorry, this user has been locked out.");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }


}
