package StandardUser.Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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
        loginPage.usernameField.sendKeys("standard_user");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("secret_sauce");
        loginPage.clickOnLoginButton();
    }

    @Test (priority = 9)
    public void userCanOpenXAccount () throws InterruptedException {
        pageFooter.clickOnXIcon();
        ArrayList<String> tablist = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tablist.get(1));
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://twitter.com/saucelabs");
    }

    @Test (priority = 10)
    public void userCanOpenFacebookAccount () throws InterruptedException {
        pageFooter.clickOnFacebookIcon();;
        ArrayList<String> tablist = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tablist.get(1));
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/saucelabs");
    }

    @Test (priority = 11)
    public void userCanOpenLinkedinAccount () throws InterruptedException {
        pageFooter.clickOnLinkedinIcon();
        ArrayList<String> tablist = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tablist.get(1));
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/company/sauce-labs/");
    }

    @AfterMethod
    public void tearDownPage() {
        driver.close();
    }
}
