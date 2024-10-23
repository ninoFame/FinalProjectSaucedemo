package StandardUser.Tests;

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

import java.time.Duration;

public class InventoryPageTests extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        inventoryDetailsPage = new InventoryDetailsPage();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("standard_user");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("secret_sauce");
        loginPage.clickOnLoginButton();
    }

    @Test (priority = 12)
    public void addOneItemToCart() {
        inventoryPage.addBackpackToCart();
        //assert that the amount of items added it correct
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "1");

        inventoryPage.clickOnShoppingCartButton();
        String name = "Sauce Labs Backpack";
        Assert.assertEquals(cartPage.returnInventoryElementName(name), name);
    }

    @Test (priority = 13)
    public void addAllItemsToCart() throws InterruptedException {
        inventoryPage.addAllItemsToCart();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "6");

        for (int i = 0; i < cartPage.listOfItemNames.size(); i++) {
            Assert.assertTrue(cartPage.listOfItemNames.get(i).isDisplayed());
        }
    }

    @Test (priority = 14)
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

    @Test (priority = 15)
    public void removeAllItemsFromCart() throws InterruptedException {
        inventoryPage.addAllItemsToCart();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "6");
        Thread.sleep(3000);
        inventoryPage.removeAllItemsFromCart();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "");
    }

    @Test (priority = 16)
    public void sortItemsByGivenCriteria() {
        inventoryPage.selectSortOption("Name (A to Z)");
        Assert.assertEquals(inventoryPage.currentSortOption.getText(), "Name (A to Z)");
        inventoryPage.selectSortOption("Name (Z to A)");
        Assert.assertEquals(inventoryPage.currentSortOption.getText(), "Name (Z to A)");
        inventoryPage.selectSortOption("Price (low to high)");
        Assert.assertEquals(inventoryPage.currentSortOption.getText(), "Price (low to high)");
        inventoryPage.selectSortOption("Price (high to low)");
        Assert.assertEquals(inventoryPage.currentSortOption.getText(), "Price (high to low)");
    }

    @Test (priority = 17)
    public void userCanLogOut() throws InterruptedException {
        inventoryPage.burgerMenu.click();
        Thread.sleep(2000);
        inventoryPage.logOutButton.click();
        Assert.assertTrue(loginPage.usernameField.isDisplayed());
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test (priority = 18)
    public void clickAndViewOneProduct() throws InterruptedException {
        Thread.sleep(1000);
        inventoryDetailsPage.viewInventoryItem("Sauce Labs Backpack");
        Assert.assertEquals(inventoryDetailsPage.inventoryDetailsName.getText(), "Sauce Labs Backpack");
    }

    @Test (priority = 19)
    public void addItemToCartFromTheInventoryDetailsPage() throws InterruptedException {
        Thread.sleep(1000);
        String name = "Sauce Labs Backpack";
        inventoryDetailsPage.viewInventoryItem(name);
        inventoryDetailsPage.clickOnAddToCartFromInventoryDetailsPage();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "1");

        inventoryPage.clickOnShoppingCartButton();
        Assert.assertEquals(cartPage.returnInventoryElementName(name), name);
    }

    @Test (priority = 20)
    public void removeItemToCartFromTheInventoryDetailsPage() throws InterruptedException {
        Thread.sleep(1000);
        inventoryDetailsPage.viewInventoryItem("Sauce Labs Backpack");
        inventoryDetailsPage.clickOnAddToCartFromInventoryDetailsPage();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "1");
        inventoryDetailsPage.clickOnRemoveFromCartFromInventoryDetailsPage();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "");
    }

    @Test (priority = 21)
    public void resetAppState() throws InterruptedException {
        inventoryPage.addAllItemsToCart();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "6");
        inventoryPage.clickOnBurgerMenu();
        inventoryPage.clickOnResetAppStateButton();
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "");
        Thread.sleep(500);
        //check if the "Add to cart" button is displayed again on all items
        inventoryPage.clickOnCloseBurgerMenu();
        Thread.sleep(500);

        for (int i = 0; i < inventoryPage.removeFromCartButton.size(); i++) {
            Assert.assertFalse(inventoryPage.removeFromCartButton.get(i).isDisplayed());
        }
    }

    @Test (priority = 22)
    public void addSpecificItemToCart() {
        String item1 = "Sauce Labs Onesie";
        String item2 = "Sauce Labs Fleece Jacket";
        String item3 = "Test.allTheThings() T-Shirt (Red)";
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "");

        inventoryPage.addSpecificProductToCart(item1);
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "1");
        inventoryPage.addSpecificProductToCart(item2);
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "2");
        inventoryPage.addSpecificProductToCart(item3);
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "3");

        inventoryPage.clickOnShoppingCartButton();
        cartPage.checkIfItemIsInCart(item1);
        cartPage.checkIfItemIsInCart(item2);
        cartPage.checkIfItemIsInCart(item3);
    }

    @Test (priority = 23)
    public void addSpecifiedNumberOfItemsToCart() throws InterruptedException {
        inventoryPage.addSpecificNumberOfItems(2);
        Assert.assertEquals(inventoryPage.shoppingCartButton.getText(), "2");
    }

  @AfterMethod
    public void tearDownPage() {
        driver.close();
    }
}
