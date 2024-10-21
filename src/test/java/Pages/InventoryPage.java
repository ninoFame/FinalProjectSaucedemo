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

    //____________________Cart Icom
    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCartButton;

    public void clickOnShoppingCartButton() {
        shoppingCartButton.click();
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

    public void removeTShirtToCart() {
        removeTShirtButton.click();
    }

    //_______________________________ Dropdown

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

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public List<WebElement> removeFromCartButton;

    public void removeAllItemsFromCart() throws InterruptedException {
        for (int i = removeFromCartButton.size() - 1; i >= 0; i--) {
            if (removeFromCartButton.get(i).getText().equals("Remove"))
                Thread.sleep(500);
            removeFromCartButton.get(i).click();
        }
    }


}
