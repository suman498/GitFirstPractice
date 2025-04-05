package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginpage {

    private WebDriver d;

    public loginpage(WebDriver driver){
        try{
            this.d=driver;
            PageFactory.initElements(driver,this);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    //Elements in Page
    @FindBy(name="username")
    WebElement setUsername;

    @FindBy(name="password")
    WebElement setPassword;

    @FindBy(xpath="//button[contains(@class,'login-button')]")
    WebElement loginButton;

    public void setID_Password(String userID,String password){
        try{
            setUsername.sendKeys(userID);
            setPassword.sendKeys(password);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void clickLoginButton(){
        try{
            loginButton.click();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
