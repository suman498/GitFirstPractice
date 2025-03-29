package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.loginPage;
import util.configReader;

import java.time.Duration;

public class loginTest {
    WebDriver d;
    static pages.loginPage login;
    WebDriverWait wait;

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.driver.chrome","drivers/chromedriver.exe");
        d=new ChromeDriver();
        wait= new WebDriverWait(d, Duration.ofMillis(2000));
        login = new loginPage(d);

        d.get(configReader.getProperty("url"));
        d.manage().window().maximize();

    }

    @Test
    public static void Customerlogin() throws InterruptedException {
        login.login(configReader.getProperty("username"),"abc123");
    }

    @AfterTest
    public void tearDown(){
        d.close();
        d.quit();
    }
}
