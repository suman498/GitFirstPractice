package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginPage {
    private WebDriver d;
    WebDriverWait wait;


    //Elements in Page
    @FindBy(name="username")
    WebElement setUsername;

    @FindBy(name="password")
    WebElement setPassword;

    @FindBy(xpath="//button[contains(@class,'login-button')]")
    WebElement loginButton;


    //Constructor
    public loginPage(WebDriver driver){
        try{
            this.d=driver;
            PageFactory.initElements(driver,this);
        }
        catch (Exception e){
            System.out.println(e);
        }


    }

    public void Login(String usrName,String pwd) throws InterruptedException {
        wait= new WebDriverWait(d,Duration.ofMillis(4000));
        wait.until(ExpectedConditions.visibilityOf(setUsername));

        setUsername.sendKeys(usrName);
        setPassword.sendKeys(pwd);
        Thread.sleep(800);
        loginButton.click();

    }


}
