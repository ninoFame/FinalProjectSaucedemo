package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPages extends BaseTest {

    public CheckoutPages() {
        PageFactory.initElements(driver, this);
    }

    //_________________________Checkout: Your Information

    @FindBy (id = ".title")
    public WebElement checkoutTitle;

    @FindBy (id = "first-name")
    public WebElement firstNameField;

    @FindBy (id = "last-name")
    public WebElement lastNameField;

    @FindBy (id = "postal-code")
    public WebElement zipPostalCodeField;

    @FindBy (id = "continue")
    public WebElement continueButton;

    public void clickOnContinueButton () {
        continueButton.click();
    }
}
