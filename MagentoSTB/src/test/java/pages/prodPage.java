package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class prodPage {

    private WebDriver driver;
    public WebDriverWait wait;

    public prodPage(WebDriver d){
        this.driver=d;
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public void setColor(String prodColor){
        try{

        } catch (Exception e) {
            System.out.println("Error in setColor Method in cartPage");
            throw new RuntimeException(e);
        }
    }

    public void setSize(String prodSize){
        try{

        } catch (Exception e) {
            System.out.println("Error in setSize Method in cartPage");
            throw new RuntimeException(e);
        }
    }

    public void setQuantity(String prodQuantity){
        try{

        } catch (Exception e) {
            System.out.println("Error in setQuantity Method in cartPage");
            throw new RuntimeException(e);
        }
    }

    public void verify(String prodName) {
        try{

        } catch (Exception e) {
            System.out.println("Error in verify Method in cartPage");
            throw new RuntimeException(e);
        }
    }

    public void addToCart() {
        try{

        } catch (Exception e) {
            System.out.println("Error in verify Method in cartPage");
            throw new RuntimeException(e);
        }
    }
}
