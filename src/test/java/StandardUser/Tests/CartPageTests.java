package StandardUser.Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartPageTests extends BaseTest {
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
        loginPage.usernameField.sendKeys("standard_user");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("secret_sauce");
        loginPage.clickOnLoginButton();
        inventoryPage.addSpecificNumberOfItems(1);
        inventoryPage.clickOnShoppingCartButton();
    }

    @Test
    public void returnToInventoryPage() {
        cartPage.clickOnContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void removeItemFromCart() throws InterruptedException {
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "1");
        cartPage.removeAllItemsFromCart();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "");
    }

    @Test
    public void checkoutRedirection() throws InterruptedException {
        cartPage.clickOnCheckoutButton();
        Thread.sleep(500);
        Assert.assertTrue(checkoutPages.firstNameField.isDisplayed());
        Assert.assertTrue(checkoutPages.lastNameField.isDisplayed());
        Assert.assertTrue(checkoutPages.zipPostalCodeField.isDisplayed());
        Assert.assertTrue(checkoutPages.continueButton.isDisplayed());
        Assert.assertEquals(checkoutPages.checkoutTitle.getText(), "Checkout: Your Information");
    }

    @AfterMethod
    public void tearDownPage() {
        driver.close();
    }
}
