package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginPage {
    private WebDriver driver;
    WebDriverWait wait;

    public loginPage(WebDriver d){
        try{
            this.driver=d;
            PageFactory.initElements(driver,this);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @FindBy(id="login2")
    WebElement loginLink;


    @FindBy(id="loginusername")
    WebElement userName;

    @FindBy(id="loginpassword")
    WebElement password;

    @FindBy(xpath = "//button[@onclick='logIn()']")
    WebElement loginButton;

    public void login(String usnName,String pwd) throws InterruptedException {
        wait = new WebDriverWait(driver,Duration.ofMillis(2000));

        loginLink.click();
        driver.switchTo().activeElement();

        wait.until(ExpectedConditions.visibilityOf(userName));
        Thread.sleep(800);
        userName.sendKeys(usnName);
        password.sendKeys(pwd);

        loginButton.click();
    }

}
