package ProblemUser.Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutPageTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws InterruptedException {
        driver = new ChromeDriver();
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        inventoryDetailsPage = new InventoryDetailsPage();
        checkoutPages = new CheckoutPages();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("problem_user");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("secret_sauce");
        loginPage.clickOnLoginButton();
        inventoryPage.addSpecificNumberOfItems(1);
        inventoryPage.clickOnShoppingCartButton();
        Thread.sleep(500);
        cartPage.clickOnCheckoutButton();
    }

    @Test
    public void cancelFromCheckout() throws InterruptedException {
        Thread.sleep(500);
        checkoutPages.clickOnCancelButton();
        Thread.sleep(500);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "1");
    }

    @Test
    public void checkoutWithValidData() {
        checkoutPages.firstNameField.sendKeys("Firstname");
        checkoutPages.lastNameField.sendKeys("Lastname");
        checkoutPages.zipPostalCodeField.sendKeys("18000");
        checkoutPages.clickOnContinueButton();
        Assert.assertEquals(checkoutPages.checkoutTitle.getText(), "Checkout: Overview");
        Assert.assertTrue(checkoutPages.paymentInformation.isDisplayed());
        Assert.assertTrue(checkoutPages.shippingInformation.isDisplayed());
        Assert.assertTrue(checkoutPages.priceTotal.isDisplayed());
        checkoutPages.clickOnFinishButton();
        Assert.assertEquals(checkoutPages.checkoutTitle.getText(), "Checkout: Complete!");
        Assert.assertEquals(checkoutPages.successNotification.getText(), "Thank you for your order!");
        Assert.assertTrue(checkoutPages.backHomeButton.isDisplayed());
    }

    @Test
    public void returnToHomepageAfterSuccessfulCheckout() {
        checkoutPages.firstNameField.sendKeys("Firstname");
        checkoutPages.lastNameField.sendKeys("Lastname");
        checkoutPages.zipPostalCodeField.sendKeys("18000");
        checkoutPages.clickOnContinueButton();
        Assert.assertEquals(checkoutPages.checkoutTitle.getText(), "Checkout: Overview");
        Assert.assertTrue(checkoutPages.paymentInformation.isDisplayed());
        Assert.assertTrue(checkoutPages.shippingInformation.isDisplayed());
        Assert.assertTrue(checkoutPages.priceTotal.isDisplayed());
        checkoutPages.clickOnFinishButton();
        Assert.assertEquals(checkoutPages.checkoutTitle.getText(), "Checkout: Complete!");
        Assert.assertEquals(checkoutPages.successNotification.getText(), "Thank you for your order!");
        Assert.assertTrue(checkoutPages.backHomeButton.isDisplayed());
        checkoutPages.clickOnBackHomeButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "");
    }

    @Test
    public void checkoutWithNoData() {
        checkoutPages.clickOnContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkoutPages.errorMessage.isDisplayed());
        Assert.assertEquals(checkoutPages.errorMessage.getText(), "Error: First Name, Last Name, Zip/Postal Code is required");
    }

    @AfterMethod
    public void tearDownPage() {
        driver.close();
    }
}
