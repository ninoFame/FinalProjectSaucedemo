package Pages;

import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomepagePage extends BaseTest {

    public HomepagePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (className = "login_logo")
    public WebElement swagLabs;

    @FindBy (id = "user-name")
    public WebElement usernameField;

    @FindBy (id = "password")
    public WebElement passwordField;

    @FindBy (id = "login-button")
    public WebElement loginButton;

    public void clickOnLoginButton () {
        loginButton.click();
    }

    @FindBy (css = ".login_credentials_wrap-inner")
    public WebElement logginCredentials;

    @FindBy (css = "h3[data-test='error']")
    public WebElement errorMessage;

}
