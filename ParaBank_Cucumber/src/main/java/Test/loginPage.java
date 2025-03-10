package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginPage {
    private WebDriver driver;
    public WebElement e;
    public WebDriverWait wait;


    private By userName=By.name("username");
    private By password=By.name("password");
    private By loginButton=By.xpath("//div[@class='login']/input[contains(@value,'Log')]");
    private By logoutLink=By.xpath("//a[contains(@href,'log')]");

    //constructor
    public loginPage(WebDriver driver){
        this.driver=driver;
    }

    public void enterUserName(){
        e=driver.findElement(userName);
        e.sendKeys("SUMS54");
    }

    public void enterPassword(){
        e=driver.findElement(password);
        e.sendKeys("abc123");
    }

    public void clickLoginButton(){
        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        e=driver.findElement(loginButton);
        e.click();
        //Thead.sleep(800);
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
    }

    public void login(){
        enterUserName();
        enterPassword();
        clickLoginButton();
    }

    public boolean checkLogoutLink(){


        //will return 1 if displayed
        return driver.findElement(logoutLink).isDisplayed();
    }


}
