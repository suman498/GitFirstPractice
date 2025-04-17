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

public class shoppingCartPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @FindBy(xpath = "//h1/span")
    private WebElement pageTitle;

    @FindBy(xpath = "//td[@class='amount']/span[@data-th]")
    private WebElement subtotal;

    @FindBy(xpath = "//td[@data-th='Discount']/span/span")
    private WebElement discount;

    @FindBy(xpath = "//td[@data-th='Order Total']/strong/span")
    private WebElement orderTotal;

    @FindBy(xpath = "//button[contains(@data-role,'checkout')]")
    private WebElement checkoutButton;


    public shoppingCartPage(WebDriver d){
        this.driver=d;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver,this);
    }

    public void orderTotal(){
        try{
            wait.until(ExpectedConditions.urlToBe("https://magento.softwaretestingboard.com/checkout/cart/"));
            Assert.assertEquals(pageTitle.getText(),
                    "Shopping Cart",
                    "Page title does not Match");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//tr[@class='totals sub']/th")));
            wait.until(ExpectedConditions.textToBePresentInElement(
                    driver.findElement(By.xpath("//tr[@class='totals sub']/th")),
                    "Subtotal"
            ));

            System.out.println("Subtotal : "+subtotal.getText());
            System.out.println("Discount Applied : "+discount.getText());
            System.out.println("Order Total is : "+orderTotal.getText());


        } catch (Exception e) {
            System.out.println("Error in orderTotal Method in shoppingCartPage");
            e.printStackTrace();
        }
    }

    public checkoutPage checkout(){
        try{
            //click on checkout button
            checkoutButton.click();
            wait.until(ExpectedConditions.urlToBe("https://magento.softwaretestingboard.com/checkout/#shipping"));


        } catch (Exception e) {
            System.out.println("Error in checkout Method in shoppingCartPage");
            e.printStackTrace();
        }

        return new checkoutPage(driver);
    }


}
