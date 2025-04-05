package Test;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.loginpage;
import utils.configReader;

import java.time.Duration;


public class orangeHRM_Login_stepdefs {
    public WebDriver d;
    public WebDriverWait wait;
    pages.loginpage letsLogin;


    @Before
    public void setup(){
        System.setProperty("webdriver.driver.chrome","driver/chromedriver.exe");
        d= new ChromeDriver();
        wait= new WebDriverWait(d,Duration.ofMillis(1400));
        //initalise login Page
        letsLogin = new loginpage(d);
    }

    @After
    public void tearDown(){
        d.close();
        d.quit();
    }


    @Given("i am at the OrangeHRM Login Page")
    public void i_am_at_the_OrangeHRM_Login_Page(){
        d.get(configReader.getProperty("url"));
        By loginTitle = By.xpath("//h5[contains(@class,'login-title')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginTitle));
        Assert.assertEquals(
                d.findElement(loginTitle).getText(),
                "Login",
                "Login page not opened properly"
        );
    }


    @Given("i have entered the username and password")
    public void iHaveEnteredTheUsernameAndPassword() {
        letsLogin.setID_Password(configReader.getProperty("userName"),configReader.getProperty("pwd"));
    }

    @When("i click on the login button")
    public void iClickOnTheLoginButton() {
        letsLogin.clickLoginButton();
    }

    @Then("i will be logged in successfully")
    public void iWillBeLoggedInSuccessfully() {
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[contains(@class,'header-breadcrumb-module')]")
            ));
            //Thread.sleep(1800);

            WebElement headerElement =d.findElement(By.xpath("//h6[contains(@class,'header-breadcrumb-module')]"));
            //wait.until(ExpectedConditions.visibilityOf(headerElement));

            Assert.assertEquals(headerElement.getText(),"Dashboard",
                    "Not Logged in Properly");

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
