package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    //for username field
    @FindBy(id="user-name")
    WebElement userNameField;

    //for password field
    @FindBy(id="password")
    WebElement passwordField;

    //for the login button
    @FindBy(id="login-button")
    WebElement loginButton;


    public LoginPage(WebDriver driver){
        try {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Login(String userName, String pwd){
        userNameField.sendKeys(userName);
        passwordField.sendKeys(pwd);
        loginButton.click();

    }


}
