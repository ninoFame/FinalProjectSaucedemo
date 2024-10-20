package Tests;

import Base.BaseTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class LoginTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp () {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void closeBrowser () {
        driver.close();
    }
}
