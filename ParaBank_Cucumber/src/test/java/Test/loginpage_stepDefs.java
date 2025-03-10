package Test;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class loginpage_stepDefs {

    public WebDriver driver;
    public loginPage loginPage;


    @Before
    public void setUp(){
        driver = new ChromeDriver();

    }

    @After
    public void tearDown(){
        if (driver!=null){
            driver.close();
            driver.quit();
        }

    }

    @Given("I am at the login Page of ParaBank")
    public void i_am_at_the_login_page_of_para_bank(){
        driver.get("https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC");
        loginPage = new loginPage(driver);
    }

    @Given("I have entered valid username and password")
    public void i_have_entered_valid_username_and_password(){
        loginPage.enterUserName();
        loginPage.enterPassword();
    }

    @When("I click on Login button")
    public void i_click_on_login_button(){
        loginPage.clickLoginButton();
    }

    @Then("i will be logged in successfully")
    public void i_will_be_logged_in_successfully(){
        Assert.assertTrue(loginPage.checkLogoutLink());

    }


}
