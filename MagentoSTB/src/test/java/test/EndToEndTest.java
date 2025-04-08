package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CustomerLogin;
import pages.Homepage;
import utils.ConfigReader;

import java.time.Duration;

public class EndToEndTest {

    WebDriver driver;
    WebDriverWait wait;
    pages.Homepage home;


    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        driver = new ChromeDriver();
        home= new Homepage(driver);
        wait =  new WebDriverWait(driver, Duration.ofMillis(1400));

        driver.get(ConfigReader.properties.getProperty("url"));

    }

    @Test
    public void endToEndTest(){

        custLoginTest();

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

    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
