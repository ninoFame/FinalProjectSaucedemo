package ProblemUser.Tests;

import Base.BaseTest;
import Pages.InventoryPage;
import Pages.LoginPage;
import Pages.PageFooter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class PageFooterTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new ChromeDriver();
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        pageFooter = new PageFooter();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("problem_user");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("secret_sauce");
        loginPage.clickOnLoginButton();
    }

    @Test
    public void userCanOpenXAccount () throws InterruptedException {
        pageFooter.clickOnXIcon();
        ArrayList<String> tablist = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tablist.get(1));
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://twitter.com/saucelabs");
    }

    @Test
    public void userCanOpenFacebookAccount () throws InterruptedException {
        pageFooter.clickOnFacebookIcon();;
        ArrayList<String> tablist = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tablist.get(1));
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/saucelabs");
    }

    @Test
    public void userCanOpenLinkedinAccount () throws InterruptedException {
        pageFooter.clickOnLinkedinIcon();
        ArrayList<String> tablist = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tablist.get(1));
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/company/sauce-labs/");
    }
}
