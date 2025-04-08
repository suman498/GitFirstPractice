package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Homepage {
    private WebDriver driver;
    public WebDriverWait wait;

    @FindBy(partialLinkText = "Sign In")
    private WebElement signIn;

    @FindBy(xpath = "(//button[contains(@data-action,'menu-toggle')])[1]")
    private WebElement ddwnButton;

    @FindBy(partialLinkText = "Sign Out")
    private WebElement signOut;


    //Constructor
    public Homepage(WebDriver d){
        try{
            this.driver=d;
            wait = new WebDriverWait(driver, Duration.ofMillis(1400));
            PageFactory.initElements(driver,this);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public CustomerLogin signIn(){
        try{
            wait.until(ExpectedConditions.visibilityOf(signIn));
            signIn.click();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Sign In Not Found");
        }

        return new CustomerLogin(driver);
    }

    public void signOut(){
        try{
            ddwnButton.click();
            wait.until(ExpectedConditions.visibilityOf(signOut));
            signOut.click();


        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("No such Element Found");
        }
    }


}
