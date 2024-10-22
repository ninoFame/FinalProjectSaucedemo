package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {
    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    //__________________List of all/one of the items in the cart

    @FindBy(className = "inventory_item_name")
    public List<WebElement> inventoryItems;

    @FindBy(className = "inventory_item_name")
    public WebElement inventoryItem;

    //Returns the name of the item in the cart we define in the test, to check whether a specific item is in the cart
    public String returnInventoryElementName(String name) {
        String inventoryElement = "";
        for (int i = 0; i < inventoryItems.size(); i++) {
            if (inventoryItems.get(i).getText().equals(name)) {
                inventoryElement = inventoryItems.get(i).getText();
                break;
            }
        }
        return inventoryElement;
    }

    //_________________Continue shopping button

    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButton;

    public void clickOnContinueShopping() {
        continueShoppingButton.click();
    }

    //________________Checkout button
    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    public void clickOnCheckoutButton() {
        checkoutButton.click();
    }

    //_____________Remove all items from cart
    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    public List<WebElement> removeFromCartButton;

    public void removeAllItemsFromCart() throws InterruptedException {
        for (int i = removeFromCartButton.size() - 1; i >= 0; i--) {
            if (removeFromCartButton.get(i).getText().equals("Remove"))
                Thread.sleep(500);
            removeFromCartButton.get(i).click();
        }
    }
}
