package Pages;

import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageFooter extends BaseTest {
    public PageFooter () {
        PageFactory.initElements(driver,this);

    }

    @FindBy (className = "social_twitter")
    public WebElement XIcon;

    public void clickOnXIcon (){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", XIcon);
        XIcon.click();
    }

    @FindBy (className = "social_facebook")
    public WebElement facebookIcon;

    public void clickOnFacebookIcon (){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", facebookIcon);
        facebookIcon.click();
    }

    @FindBy (className = "social_linkedin")
    public WebElement linkedinIcon;

    public void clickOnLinkedinIcon (){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", linkedinIcon);
        linkedinIcon.click();
    }



}
