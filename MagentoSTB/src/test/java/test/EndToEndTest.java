package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.CustomerLogin;
import pages.Homepage;
import utils.ConfigReader;
import utils.ExcelUtils;

import java.time.Duration;
import java.util.Arrays;

public class EndToEndTest {

    WebDriver driver;
    WebDriverWait wait;
    pages.Homepage home;


    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        driver = new ChromeDriver();
        home= new Homepage(driver);
        wait =  new WebDriverWait(driver, Duration.ofMillis(1400));

        driver.get(ConfigReader.properties.getProperty("url"));
        driver.manage().window().maximize();

    }

    //Data Provider Method
    @DataProvider(name="excelTests")
    public Object[][] getTestsFromExcel(){
        //return ExcelUtils.getRunnableTests();
        Object[][] data = ExcelUtils.getRunnableTests();
        System.out.println("Data from Data Provider : "+ Arrays.deepToString(data));
        return data;
    }



    @Test(dataProvider = "excelTests")
    public void endToEndTest(String testCaseID,String testCaseName){
        System.out.println("Running Test --->"+testCaseID+"::"+testCaseName);
        switch (testCaseID){
            case "TC001":
                custLoginTest();
                break;

            default:
                System.out.println("No Logic Defined for TestCase : "+testCaseID);

        }


    }

    private void custLoginTest() {
        //nested POM homepage ----> CustomerLoginPage
        CustomerLogin custLogin = home.signIn();
        custLogin.setEmail(ConfigReader.properties.getProperty("email"));
        custLogin.setPass(ConfigReader.properties.getProperty("pass"));
        //nested POM CustomerLoginPage ----> HomePage
        home = custLogin.clickLogin();
        home.signOut();
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
