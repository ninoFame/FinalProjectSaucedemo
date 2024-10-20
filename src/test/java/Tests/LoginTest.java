package Tests;

import Base.BaseTest;
import Pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.navigate().to("https://www.saucedemo.com/");
    }

    //Login Tests with manual String data
    //TestCase 001
    @Test (priority = 1)
    public void pageElementsAreVisible() {
        Assert.assertTrue(loginPage.swagLabs.isDisplayed());
        Assert.assertTrue(loginPage.usernameField.isDisplayed());
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertTrue(loginPage.logginCredentials.isDisplayed());
    }

    //TestCase002
    @Test (priority = 2)
    public void userLoginWithValidCredentials () {
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("standard_user");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("secret_sauce");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    //TestCase003
    @Test (priority = 3)
    public void userLoginWithInvalidUsername () {
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("invalidusername");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("secret_sauce");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    //TestCase004
    @Test (priority = 4)
    public void userLoginWithInvalidPassword () {
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("standard_user");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("invalidpassword");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test (priority = 5)
    public void userLoginWithEmptyCredentials () {
        loginPage.usernameField.clear();
        loginPage.passwordField.clear();
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username is required");
    }
    //Login Tests with Excel data


   @AfterMethod
   public void closeBrowser() {
       driver.close();
    }
}
