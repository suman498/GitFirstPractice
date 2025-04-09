package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CustomerLogin {
    private WebDriver driver;
    public WebDriverWait wait;

    @FindBy(id="email")
    private WebElement email;

    @FindBy(id="pass")
    private WebElement password;

    @FindBy(id="send2")
    private WebElement loginButton;


    //Constructor
    public CustomerLogin(WebDriver d){
        try{
            this.driver=d;
            wait = new WebDriverWait(driver, Duration.ofMillis(1400));
            PageFactory.initElements(driver,this);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void setEmail(String mail) {
        try{
            wait.until(ExpectedConditions.visibilityOf(email));

            String pageTitle = driver.findElement(By.xpath("//h1/span")).getText();
            Assert.assertEquals(
                    pageTitle,
                    "Customer Login",
                    "Login Page not opened properly");

            email.sendKeys(mail);

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("No such Element Found");
        }


    }

    public void setPass(String pass) {
        try{

            wait.until(ExpectedConditions.visibilityOf(password));
            password.sendKeys(pass);

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("No such Element Found");
        }

    }

    public Homepage clickLogin() {
        try{
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            loginButton.click();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("No such Element Found");
        }

        return new Homepage(driver);

    }



}
