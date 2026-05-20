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
        loginPage.sendUsername("standard_user");
        loginPage.sendPassword("secret_sauce");
        loginPage.login();

        BasePage basePage = new BasePage(driver);
        String pageName = basePage.getPageName();
        Assert.assertEquals(pageName, "Products");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void testFailsWhenUsernameIsNotProvided() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername("");
        loginPage.sendPassword("secret_sauce");
        loginPage.login();

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(errorMessage,"Epic sadface: Username is required");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void testFailsWhenPasswordIsNotProvided() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername("standard_user");
        loginPage.sendPassword("");
        loginPage.login();

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(errorMessage,"Epic sadface: Password is required");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void testFailsWhenCredentialsAreInvalid() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername("test");
        loginPage.sendPassword("test");
        loginPage.login();

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(errorMessage,"Epic sadface: Username and password do not match any user in this service");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void testFailsWhenUsingLockedUpUserCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername("locked_out_user");
        loginPage.sendPassword("secret_sauce");
        loginPage.login();

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(errorMessage,"Epic sadface: Sorry, this user has been locked out.");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }


}
