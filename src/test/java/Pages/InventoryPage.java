package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventoryPage extends BaseTest {
    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    //________________________________ Burger Menu

    @FindBy (id = "react-burger-cross-btn")
    public WebElement closeBurgerMenu;

    public void clickOnCloseBurgerMenu () {
        closeBurgerMenu.click();
    }

    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenu;

    public void clickOnBurgerMenu() {
        burgerMenu.click();
    }

    @FindBy(id = "logout_sidebar_link")
    public WebElement logOutButton;

    public void clickOnLogoutButton() {
        logOutButton.click();
    }

    @FindBy(id = "reset_sidebar_link")
    public WebElement resetAppStateButton;

    public void clickOnResetAppStateButton() {
        resetAppStateButton.click();
    }

    //_____________________________ Cart Icon

    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCartButton;

    public void clickOnShoppingCartButton() {
        shoppingCartButton.click();
    }

    //_______________________________Sort Dropdown

    @FindBy(className = "product_sort_container")
    public WebElement sortDropdown;

    @FindBy(className = "active_option")
    public WebElement currentSortOption;

    public void selectSortOption(String criteria) {
        Select criteriaSelect = new Select(sortDropdown);
        criteriaSelect.selectByVisibleText(criteria);
    }

    //_______________________ Add/Remove all items in cart

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public List<WebElement> AddToCartButton;

    public void addAllItemsToCart() throws InterruptedException {
        for (int i = AddToCartButton.size() - 1; i >= 0; i--) {
            if (AddToCartButton.get(i).getText().equals("Add to cart"))
                Thread.sleep(500);
            AddToCartButton.get(i).click();
        }
    }

    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    public List<WebElement> removeFromCartButton;

    public void removeAllItemsFromCart() throws InterruptedException {
        for (int i = removeFromCartButton.size() - 1; i >= 0; i--) {
            if (removeFromCartButton.get(i).getText().equals("Remove"))
                Thread.sleep(500);
            removeFromCartButton.get(i).click();
        }
    }

    //___________________________ Add/Remove specific number of items in cart

    public void addSpecificNumberOfItems(int numberOfItems) throws InterruptedException {
        for (int i = numberOfItems - 1; i >= 0; i--) {
            if (AddToCartButton.get(i).getText().equals("Add to cart"))
                Thread.sleep(500);
            AddToCartButton.get(i).click();
        }
    }

    public void removeSpecificNumberOfItems(int numberOfItems) throws InterruptedException {
        for (int i = numberOfItems - 1; i >= 0; i--) {
            if (removeFromCartButton.get(i).getText().equals("Remove"))
                Thread.sleep(500);
            removeFromCartButton.get(i).click();
        }
    }

    //___________________________ Add/Remove specific items in cart

    public void addSpecificProductToCart(String productName) {
        if (productName.equalsIgnoreCase("Sauce Labs Backpack") || productName.equalsIgnoreCase("Backpack") ) {
            addBackpackButton.click();
        } else if (productName.equalsIgnoreCase("Sauce Labs Bike Light") || productName.equalsIgnoreCase("Bike Light")) {
            addBikeLightButton.click();
        } else if (productName.equalsIgnoreCase("Sauce Labs Bolt T-Shirt") || productName.equalsIgnoreCase("T-Shirt")) {
            addTShirtButton.click();
        } else if (productName.equalsIgnoreCase("Sauce Labs Fleece Jacket") || productName.equalsIgnoreCase("Fleece Jacket")) {
            addFleeceJacketButton.click();
        } else if (productName.equalsIgnoreCase("Sauce Labs Onesie") || productName.equalsIgnoreCase("Onesie")) {
            addOnesieButton.click();
        } else if (productName.equalsIgnoreCase("Test.allTheThings() T-Shirt (Red)") || productName.equalsIgnoreCase("T-Shirt (Red)") || productName.equalsIgnoreCase("Red T-Shirt")) {
            addTShirtRedButton.click();
        }

    }

    //______________________________ Sauce Labs Backpack

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    public WebElement addBackpackButton;

    public void addBackpackToCart() {
        addBackpackButton.click();
    }

    @FindBy(id = "remove-sauce-labs-backpack")
    public WebElement removeBackpackButton;

    public void removeBackpackFromCart() {
        removeBackpackButton.click();
    }

    //______________________________ Sauce Labs Bike Light

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    public WebElement addBikeLightButton;

    public void addBikeLightToCart() {
        addBikeLightButton.click();
    }

    @FindBy(id = "remove-sauce-labs-bike-light")
    public WebElement removeBikeLightButton;

    public void removeBikeLightToCart() {
        removeBikeLightButton.click();
    }

    //______________________________ Sauce Labs Bolt T-Shirt

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    public WebElement addTShirtButton;

    public void addTShirtToCart() {
        addTShirtButton.click();
    }

    @FindBy(id = "remove-sauce-labs-bolt-t-shirt")
    public WebElement removeTShirtButton;

    public void removeTShirtFromCart() {
        removeTShirtButton.click();
    }

    //______________________________ Sauce Labs Fleece Jacket

    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    public WebElement addFleeceJacketButton;

    public void addFleeceJacketToCart() {
        addFleeceJacketButton.click();
    }

    @FindBy(id = "remove-sauce-labs-fleece-jacket")
    public WebElement removeFleeceJacketButton;

    public void removeFleeceJacketFromCart() {
        removeFleeceJacketButton.click();
    }

    //______________________________ Sauce Labs Onesie

    @FindBy(id = "add-to-cart-sauce-labs-onesie")
    public WebElement addOnesieButton;

    public void addOnesieToCart() {
        addOnesieButton.click();
    }

    @FindBy(id = "remove-sauce-labs-onesie")
    public WebElement removeOnesieButton;

    public void removeOnesieFromCart() {
        removeOnesieButton.click();
    }

    //______________________________ T-Shirt (Red)

    @FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
    public WebElement addTShirtRedButton;

    public void addTShirtRedToCart() {
        addTShirtRedButton.click();
    }

    @FindBy(id = "remove-test.allthethings()-t-shirt-(red)")
    public WebElement removeTShirtRedButton;

    public void removeTShirtRedFromCart() {
        removeTShirtRedButton.click();
    }

    //___________________________ Add/Remove specific items in cart

    @FindBy (className = "inventory_item_name")
    public List<WebElement> listOfProductNames;

    public void addSpecificItemToCartByName (String productName) {
        for (int i = listOfProductNames.size(); i>=0; i--) {
            if (listOfProductNames.get(i).getText().equals(productName)) {
                AddToCartButton.get(i).click();
            }
        }
    }



}
