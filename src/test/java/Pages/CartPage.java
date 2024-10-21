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

    @FindBy(className = "inventory_item_name")
    public List<WebElement> inventoryItems; // list of all the items in the cart

    @FindBy(className = "inventory_item_name")
    public WebElement inventoryItem; //just one item

    //this returns the name of the cart we define
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
}
