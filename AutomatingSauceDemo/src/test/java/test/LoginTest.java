package test;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.configReader;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;




    @BeforeTest
    public void setUp(){
        //WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(configReader.getProperty("url"));
        loginPage =  new LoginPage(driver);
        //demo();
    }

    public void demo(){
        driver.findElement(By.id("user-name")).sendKeys(configReader.getProperty("uid"));
        driver.findElement(By.id("password")).sendKeys(configReader.getProperty("pwd"));
        driver.findElement(By.id("login-button")).click();

    }

    //@Test(dataProvider = "testData" , dataProviderClass = TestDataProvider.class)
    @Test
    public void testValidLogin() throws InterruptedException {
        loginPage.Login(configReader.getProperty("uid"),configReader.getProperty("pwd"));
        Thread.sleep(1500);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        //Assert.assertTrue(driver.getCurrentUrl().contains("blue"));

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
