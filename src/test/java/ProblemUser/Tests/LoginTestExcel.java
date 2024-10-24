package ProblemUser.Tests;

import Base.BaseTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTestExcel extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.navigate().to("https://www.saucedemo.com/");
    }

    //Login Tests with Excel data
    @Test
    public void verifyThatAllValidUsersCanLogin() throws InterruptedException {
        for (int i = 1; i <= excelReader.getLastRow("Sheet1"); i++) {

            String validUsername = excelReader.getStringData("Sheet1", i, 0);
            String validPassword = excelReader.getStringData("Sheet1", 1, 1);
            loginPage.usernameField.clear();
            loginPage.usernameField.sendKeys(validUsername);
            loginPage.passwordField.clear();
            loginPage.passwordField.sendKeys(validPassword);
            loginPage.clickOnLoginButton();
            inventoryPage.burgerMenu.click();
            Thread.sleep(1000);
            inventoryPage.logOutButton.click();
        }
    }

    @AfterMethod
    public void tearDownPage() {
        driver.close();
    }
}
