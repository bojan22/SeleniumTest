package Base;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import Pages.*;

import java.io.*;
import java.time.*;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public HomepagePage homepagePage;
    public LoginPage loginPage;
    public ExcelReader excel;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        homepagePage = new HomepagePage(driver,wait);
        loginPage = new LoginPage(driver,wait);
        excel = new ExcelReader("C:\\Users\\BOKI\\Desktop\\SeleniumTest.xlsx");

    }




    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    public void visibilityWait(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
