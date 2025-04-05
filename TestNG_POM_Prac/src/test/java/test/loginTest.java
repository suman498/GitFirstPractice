package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.loginPage;
import utils.configReader;

import java.time.Duration;

public class loginTest {
    WebDriver d;
    WebDriverWait wait;
    static pages.loginPage letsLogin;



    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.driver.chrome","driver.chromedriver.exe");
        d =  new ChromeDriver();
        wait = new WebDriverWait(d, Duration.ofSeconds(1200));

        //initalise login Page
        letsLogin = new loginPage(d);

        d.get(configReader.getProperty("url"));
        d.manage().window().maximize();

    }



    @Test
    public void loginTest() {
        try{
            letsLogin.Login(configReader.getProperty("userName"),configReader.getProperty("pwd"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[contains(@class,'header-breadcrumb-module')]")
            ));
            //Thread.sleep(1800);

            WebElement headerElement =d.findElement(By.xpath("//h6[contains(@class,'header-breadcrumb-module')]"));
            //wait.until(ExpectedConditions.visibilityOf(headerElement));

            Assert.assertEquals(headerElement.getText(),"Dashboard",
                    "Not Logged in Properly");

        } catch (RuntimeException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



    @AfterTest
    public void tearDown(){
        d.close();
        d.quit();

    }



}
