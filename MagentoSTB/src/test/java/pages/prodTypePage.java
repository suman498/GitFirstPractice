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

public class prodTypePage {
    private WebDriver driver;
    public WebDriverWait wait;

    @FindBy(xpath = "//h1/span")
    public WebElement pageTitle;


    public prodTypePage(WebDriver d){
        this.driver=d;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    //Nesting with Cart Page POM
    public prodPage findProd(String prodType , String prodName){
        try{
            wait.until(ExpectedConditions.visibilityOf(pageTitle));

            Assert.assertEquals(pageTitle.getText(),
                    prodType,
                    "Product Type Page not correct"
            );

            JavascriptExecutor js = (JavascriptExecutor) driver;
            //Scrolling until element is visible
            js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.linkText(
                    prodName
            )));
            Thread.sleep(800);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(prodName)));

            driver.findElement(By.linkText(prodName)).click();


        } catch (Exception e) {
            System.out.println("Error in findProd Method\n");
            e.printStackTrace();
        }

        return new prodPage(driver);

    }

}
