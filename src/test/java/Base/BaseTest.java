package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest {
    public static WebDriver driver;
    public ExcelReader excelReader;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public InventoryDetailsPage inventoryDetailsPage;
    public CheckoutPages checkoutPages;
    public PageFooter pageFooter;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();

        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        inventoryDetailsPage = new InventoryDetailsPage();
        checkoutPages = new CheckoutPages();
        pageFooter = new PageFooter();

        //to use this, download Excel document from the TestData package and insert filePath bellow
        excelReader = new ExcelReader("TestData\\TestData.xlsx");
    }
}
