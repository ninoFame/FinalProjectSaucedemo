package ProblemUser.Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class LoginTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new ChromeDriver();
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test()
    public void pageElementsAreVisible() {
        Assert.assertTrue(loginPage.swagLabs.isDisplayed());
        Assert.assertTrue(loginPage.usernameField.isDisplayed());
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertTrue(loginPage.logginCredentials.isDisplayed());
    }

    @Test()
    public void userCanLoginWithValidCredentials() {
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("problem_user");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("secret_sauce");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        //check if all products on the inventory page are displayed
        for (int i = 0; i < cartPage.listOfItemNames.size(); i++) {
            Assert.assertTrue(cartPage.listOfItemNames.get(i).isDisplayed());
        }
    }

    @Test()
    public void userCanNotLoginWithInvalidUsernameValidPassword() {
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("invalidusername");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("secret_sauce");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test()
    public void userCanNotLoginWithValidUsernameInvalidPassword() {
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("standard_user");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("invalidpassword");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test()
    public void userCanNotLoginWithEmptyCredentials() {
        loginPage.usernameField.clear();
        loginPage.passwordField.clear();
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password are required");
    }

    @Test()
    public void userCanNotLoginWithLockedOutUsername() {
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("locked_out_user");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("secret_sauce");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @AfterMethod
    public void tearDownPage() {
        driver.close();
    }
}
