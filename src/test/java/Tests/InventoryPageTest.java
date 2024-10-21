package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.InventoryDetailsPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class InventoryPageTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        inventoryDetailsPage = new InventoryDetailsPage();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("standard_user");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("secret_sauce");
        loginPage.clickOnLoginButton();
    }

    @Test
    public void addOneItemToCart() {
        inventoryPage.addBackpackToCart();
        //assert that the amount of items added it correct
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "1");

        inventoryPage.clickOnShoppingCartButton();
        String name = "Sauce Labs Backpack";
        Assert.assertEquals(cartPage.returnInventoryElementName(name), name);

    }

    @Test
    public void addAllItemsToCart () throws InterruptedException {
        inventoryPage.addAllItemsToCart();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "6");
    }

    @Test
    public void removeOneItemToCart() {
        inventoryPage.addBackpackToCart();
        //assert that the amount of items added it correct
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "1");

        inventoryPage.clickOnShoppingCartButton();
        String name = "Sauce Labs Backpack";
        Assert.assertEquals(cartPage.returnInventoryElementName(name), name);

        inventoryPage.removeBackpackFromCart();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "");
    }

    /*@Test
    public void removeAllItemsFromCart () throws InterruptedException {
        inventoryPage.addAllItemsToCart();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "6");
        Thread.sleep(3000);
        inventoryPage.removeAllItemsFromCart();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "");
    }*/

    @Test
    public void sortItemsByGivenCriteria () {
        inventoryPage.selectSortOption("Name (A to Z)");
        Assert.assertEquals(inventoryPage.currentSortOption.getText(), "Name (A to Z)");
        inventoryPage.selectSortOption("Name (Z to A)");
        Assert.assertEquals(inventoryPage.currentSortOption.getText(), "Name (Z to A)");
        inventoryPage.selectSortOption("Price (low to high)");
        Assert.assertEquals(inventoryPage.currentSortOption.getText(), "Price (low to high)");
        inventoryPage.selectSortOption("Price (high to low)");
        Assert.assertEquals(inventoryPage.currentSortOption.getText(), "Price (high to low)");
    }

    @Test
    public void userCanLogOut () throws InterruptedException {
        inventoryPage.burgerMenu.click();
        Thread.sleep(2000);
        inventoryPage.logOutButton.click();
        Assert.assertTrue(loginPage.usernameField.isDisplayed());
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test
    public void clickAndViewOneProduct () throws InterruptedException {
        Thread.sleep(1000);
        inventoryDetailsPage.viewInventoryItem("Sauce Labs Backpack");
        Assert.assertEquals(inventoryDetailsPage.inventoryDetailsName.getText(), "Sauce Labs Backpack");
    }

    @Test
    public void addItemToCartFromTheInventoryDetailsPage () throws InterruptedException {
        Thread.sleep(1000);
        inventoryDetailsPage.viewInventoryItem("Sauce Labs Backpack");
        inventoryDetailsPage.clickOnAddToCartFromInventoryDetailsPage();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "1");
    }

    @Test
    public void removeItemToCartFromTheInventoryDetailsPage () throws InterruptedException {
        Thread.sleep(1000);
        inventoryDetailsPage.viewInventoryItem("Sauce Labs Backpack");
        inventoryDetailsPage.clickOnAddToCartFromInventoryDetailsPage();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "1");
        inventoryDetailsPage.clickOnRemoveFromCartFromInventoryDetailsPage();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "");
    }

    @Test
    public void resetAppState () throws InterruptedException {
        inventoryPage.addAllItemsToCart();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "6");
        inventoryPage.clickOnBurgerMenu();
        inventoryPage.clickOnResetAppStateButton();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "");
    }




    /*@AfterMethod
    public void closeBrowser() {
        driver.close();
    }*/
}
