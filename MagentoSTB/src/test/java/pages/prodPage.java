package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class prodPage {

    private WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;

    @FindBy(xpath = "//h1/span")
    public WebElement prodTitle;

    @FindBy(xpath = "//span[contains(@class,'selected-option')]")
    public List<WebElement> getSelectedOption;  //1--for Size 2-- for color

    @FindBy(xpath = "//div[contains(@id,'option-label-size')]")
    public List<WebElement> prodSize_list;

    @FindBy(xpath = "//div[contains(@id,'option-label-color')]")
    public List<WebElement> prodColor_list;

    public prodPage(WebDriver d){
        this.driver=d;
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver,this);
    }

    public void setColor(String prodColor){
        try{
            js = (JavascriptExecutor) driver;
            //js.executeScript("arguments[0].scrollIntoView(true);",prodColor_list.getFirst());
            wait.until(ExpectedConditions.visibilityOf(prodColor_list.getFirst()));

            //looping in the list
            for(WebElement element : prodColor_list){
                //System.out.println("color : "+element.getAttribute("aria-label"));
                if(element.getAttribute("aria-label").equals(prodColor)){
                    element.click();
                }
            }
            wait.until(ExpectedConditions.textToBePresentInElement(getSelectedOption.get(1),prodColor));
            Assert.assertEquals(getSelectedOption.get(1).getText(),
                    prodColor,
                    "Selected Color does not match with input color "
            );

        } catch (Exception e) {
            System.out.println("Error in setColor Method in prodPage");
            throw new RuntimeException(e);
        }
    }

    public void setSize(String prodSize){
        try{
            js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);",prodSize_list.getFirst());
            wait.until(ExpectedConditions.visibilityOf(prodSize_list.getFirst()));

            //looping in the list
            for(WebElement element : prodSize_list){
                //System.out.println("Size : "+element.getText());
                if(element.getText().equals(prodSize)){
                    element.click();
                }
            }
            wait.until(ExpectedConditions.textToBePresentInElement(getSelectedOption.get(0),prodSize));
            Assert.assertEquals(getSelectedOption.get(0).getText(),
                    prodSize,
                    "Selected Size does not match with input size "
            );

        } catch (Exception e) {
            System.out.println("Error in setSize Method in prodPage");
            throw new RuntimeException(e);
        }
    }

    public void setQuantity(String prodQuantity){
        try{

        } catch (Exception e) {
            System.out.println("Error in setQuantity Method in prodPage");
            throw new RuntimeException(e);
        }
    }

    public void verify(String prodName) {
        try{
            wait.until(ExpectedConditions.visibilityOf(prodTitle));
            Assert.assertEquals(prodTitle.getText(),
                    prodName,
                    "Product Page not opened properly"
            );

        } catch (Exception e) {
            System.out.println("Error in verify Method in prodPage");
            throw new RuntimeException(e);
        }
    }

    public void addToCart() {
        try{

        } catch (Exception e) {
            System.out.println("Error in verify Method in prodPage");
            throw new RuntimeException(e);
        }
    }
}
