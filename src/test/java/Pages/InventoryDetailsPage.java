package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryDetailsPage extends BaseTest {
    public InventoryDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_item_name")
    public List<WebElement> inventoryItems;

    public void viewInventoryItem(String inventoryItem) {
        for (int i = 0; i < inventoryItems.size(); i++) {
            if (inventoryItems.get(i).getText().equals(inventoryItem)) {
                inventoryItems.get(i).click();
                break;
            }
        }
    }

    @FindBy (css = ".inventory_details_name.large_size")
    public WebElement inventoryDetailsName;

    @FindBy (id = "add-to-cart")
    public WebElement addToCartFromInventoryDetailsPage;

    public void clickOnAddToCartFromInventoryDetailsPage () {
        addToCartFromInventoryDetailsPage.click();
    }

    @FindBy (id = "remove")
    public WebElement removeFromCartFromInventoryDetailsPage;

    public void clickOnRemoveFromCartFromInventoryDetailsPage () {
        removeFromCartFromInventoryDetailsPage.click();
    }

}
