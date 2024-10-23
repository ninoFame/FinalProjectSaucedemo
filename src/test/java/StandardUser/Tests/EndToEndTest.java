package StandardUser.Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class EndToEndTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws InterruptedException {
        driver = new ChromeDriver();
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        inventoryDetailsPage = new InventoryDetailsPage();
        checkoutPages = new CheckoutPages();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
    }

    @Test(priority = 1)
    public void endToEndHappyPath() throws InterruptedException {
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("standard_user");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("secret_sauce");

        loginPage.clickOnLoginButton();
        Thread.sleep(500);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "1");

        inventoryPage.clickOnShoppingCartButton();
        Thread.sleep(500);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        String name = "Sauce Labs Backpack";
        Assert.assertEquals(cartPage.returnInventoryElementName(name), name);

        cartPage.clickOnCheckoutButton();
        Thread.sleep(500);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkoutPages.firstNameField.isDisplayed());
        Assert.assertTrue(checkoutPages.lastNameField.isDisplayed());
        Assert.assertTrue(checkoutPages.zipPostalCodeField.isDisplayed());
        Assert.assertTrue(checkoutPages.continueButton.isDisplayed());
        Assert.assertEquals(checkoutPages.checkoutTitle.getText(), "Checkout: Your Information");
        checkoutPages.firstNameField.sendKeys("Firstname");
        checkoutPages.lastNameField.sendKeys("Lastname");
        checkoutPages.zipPostalCodeField.sendKeys("18000");

        checkoutPages.clickOnContinueButton();
        Thread.sleep(500);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertEquals(checkoutPages.checkoutTitle.getText(), "Checkout: Overview");
        Assert.assertTrue(checkoutPages.paymentInformation.isDisplayed());
        Assert.assertTrue(checkoutPages.shippingInformation.isDisplayed());
        Assert.assertTrue(checkoutPages.priceTotal.isDisplayed());

        checkoutPages.clickOnFinishButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
        Thread.sleep(500);
        Assert.assertEquals(checkoutPages.checkoutTitle.getText(), "Checkout: Complete!");
        Assert.assertEquals(checkoutPages.successNotification.getText(), "Thank you for your order!");
        Assert.assertTrue(checkoutPages.backHomeButton.isDisplayed());

        checkoutPages.clickOnBackHomeButton();
        Thread.sleep(500);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "");

        inventoryPage.burgerMenu.click();
        Thread.sleep(1000);
        inventoryPage.logOutButton.click();
        Thread.sleep(500);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.swagLabs.isDisplayed());
        Assert.assertTrue(loginPage.usernameField.isDisplayed());
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertTrue(loginPage.logginCredentials.isDisplayed());
    }

    @AfterMethod
    public void tearDownPage() {
        driver.close();
    }

}
