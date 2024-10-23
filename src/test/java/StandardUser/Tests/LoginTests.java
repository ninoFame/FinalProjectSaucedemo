package StandardUser.Tests;

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

    @Test(priority = 2)
    public void pageElementsAreVisible() {
        Assert.assertTrue(loginPage.swagLabs.isDisplayed());
        Assert.assertTrue(loginPage.usernameField.isDisplayed());
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertTrue(loginPage.logginCredentials.isDisplayed());
    }

    @Test(priority = 3)
    public void userCanLoginWithValidCredentials() throws InterruptedException {
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("standard_user");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("secret_sauce");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Thread.sleep(5000);

        //check if all products on the inventory page are displayed
        for (int i = 0; i < inventoryPage.listOfProductNames.size(); i++) {
            Assert.assertTrue(inventoryPage.listOfProductNames.get(i).isDisplayed());
        }
    }

    @Test(priority = 4)
    public void userCanNotLoginWithInvalidUsernameValidPassword() {
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("invalidusername");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("secret_sauce");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 5)
    public void userCanNotLoginWithValidUsernameInvalidPassword() {
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("standard_user");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("invalidpassword");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 6)
    public void userCanNotLoginWithEmptyCredentials() {
        loginPage.usernameField.clear();
        loginPage.passwordField.clear();
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password are required");
    }

    @Test(priority = 7)
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
