package Base;

import Pages.CartPage;
import Pages.InventoryDetailsPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest {
    public static WebDriver driver;
    public ExcelReader excelReader;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public InventoryDetailsPage inventoryDetailsPage;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        inventoryDetailsPage = new InventoryDetailsPage();

        //adding excel document data, insert filePath bellow
        excelReader = new ExcelReader("C:\\Users\\sm-denkovicn\\Desktop\\TestData.xlsx");
    }

}
