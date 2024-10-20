package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomepagePage extends BaseTest {
    public HomepagePage() {
        PageFactory.initElements(driver, this);
    }

    //Username Field
    @FindBy (id = "user-name")
    public WebElement usernameField;

    @FindBy (id = "password")
    public WebElement passwordField;

    @FindBy (id = "login-button")
    public WebElement loginButton;

    public void clickOnLoginButton () {
        loginButton.click();
    }

}
