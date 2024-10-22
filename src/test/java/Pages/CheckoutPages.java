package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutPages extends BaseTest {

    public CheckoutPages() {
        PageFactory.initElements(driver, this);
    }

    //_________________________Checkout visible buttons

    @FindBy (css = ".title")
    public WebElement checkoutTitle;

    @FindBy (id = "cancel")
    public WebElement cancelButton;

    public void clickOnCancelButton () {
        cancelButton.click();
    }

    //_________________________Checkout: Your Information

    @FindBy (css = "#first-name")
    public WebElement firstNameField;

    @FindBy (css = "#last-name")
    public WebElement lastNameField;

    @FindBy (css = "#postal-code")
    public WebElement zipPostalCodeField;

    @FindBy (css = "#continue")
    public WebElement continueButton;

    public void clickOnContinueButton () {
        continueButton.click();
    }

    @FindBy (css = ".error-message-container.error")
    public WebElement errorMessage;

    //_________________________Checkout: Overview

    @FindBy (css = "div[data-test='payment-info-label']")
    public WebElement paymentInformation;

    @FindBy (css = "div[data-test='shipping-info-label']")
    public WebElement shippingInformation;

    @FindBy (css = "div[data-test='total-info-label']")
    public WebElement priceTotal;

    @FindBy (css = "#finish")
    public WebElement finishButton;

    public void clickOnFinishButton () {
        finishButton.click();
    }

    //_________________________Checkout: Complete!

    @FindBy (css = ".complete-header")
    public WebElement successNotification;

    @FindBy (css = "#back-to-products")
    public WebElement backHomeButton;

    public void clickOnBackHomeButton () {
        backHomeButton.click();
    }









}
