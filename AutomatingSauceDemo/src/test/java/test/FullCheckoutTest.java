package test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.TestDataProvider;
import utils.configReader;

import java.time.Duration;
import java.util.Map;

public class FullCheckoutTest {
    WebDriver driver;
    LoginPage loginPage;
    WebDriverWait wait;
    static float perProductPrice=0;
    WebElement e;
    String[] products;

    //loading InputExcel Data
    //inputExcelReader excel = new inputExcelReader(configReader.getProperty("inputExcelPath"),configReader.getProperty("sheetName"));

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(configReader.getProperty("url"));
        loginPage = new LoginPage(driver);
    }



    @Test(dataProvider = "testData" , dataProviderClass = TestDataProvider.class)
    //@Test
    public void fullCheckoutTest(Map<String,String> testData){
        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        try {
            //for Login
            testValidLogin();

            //adding products in cart
            addProducts(testData.get("Products"));

            //Move to cart page
            cart();

            //Checkout
            checkoutOne();

            //EnteringCustomerDetails
            custDetails(testData.get("FirstName"),testData.get("LastName"),testData.get("Zipcode"));

            //FinalCheckout Page , total Amount payable will be verified here
            checkoutFinal();

            //Click on finish button
            finish();

            //logout process
            logout();

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in Main test method");
        }
    }




    public void testValidLogin() throws InterruptedException {
        loginPage.Login(configReader.getProperty("uid"),configReader.getProperty("pwd"));

        //waiting for products page
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Products')]")));

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));




    }



    public void addProducts(String listOfProducts) {
        try {
            products = listOfProducts.split(",");

            for (String s : products) {
                //adding each products in cart
                String buttonElement = "//div[contains(text(),'"
                        + s +
                        "')]/parent::*/parent::*/parent::*//button";

                String priceElement = "//div[contains(text(),'"
                        + s +
                        "')]/parent::*/parent::*/parent::*/div[@class='pricebar']/div";

                driver.findElement(By.xpath(buttonElement)).click();

                wait.until(ExpectedConditions.textToBePresentInElement(
                        driver.findElement(By.xpath(buttonElement)), "Remove"));

                //Adding prices of each products added in cart to verify with total price
                perProductPrice = perProductPrice + Float.parseFloat(
                        driver.findElement(By.xpath(priceElement))
                                .getText().substring(1)
                );

                System.out.println(perProductPrice);


                Thread.sleep(800);


            }

        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Unable to find element");
        }
    }

    public void cart() {
        try{
            //Click on Cart
            driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

            //Wait until cart visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div/span[contains(text(),'Cart')]")));




        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void checkoutOne() {
        try{
            //Click on Checkout button in cart page
            //JavaScript Executor Used
            e =driver.findElement(By.id("checkout"));
            JavascriptExecutor js= (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true)", e);

            //waiting until clickable
            wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));

            //Clicking on Checkout
            e.click();

            //Adding assertion to check if button clicked
            String checkoutURL="https://www.saucedemo.com/checkout-step-one.html";
            Assert.assertEquals(driver.getCurrentUrl(),checkoutURL
                    ,"URL did not change as expected!"
            );


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void custDetails(String firstName, String lastName, String zipcode) {
        try{
            e=driver.findElement(By.xpath("//div/span"));
            wait.until(ExpectedConditions.textToBePresentInElement(
                    e,"Checkout: Your Information"
            ));

            //Enter firstName
            driver.findElement(By.id("first-name")).sendKeys(firstName);

            //Enter lastName
            driver.findElement(By.id("last-name")).sendKeys(lastName);

            //Enter ZipCode
            driver.findElement(By.id("postal-code")).sendKeys(zipcode);

            //Click on Continue
            driver.findElement(By.id("continue")).click();

            Assert.assertEquals(driver.getCurrentUrl(),
                    "https://www.saucedemo.com/checkout-step-two.html"
                    ,"URL did not change as expected!"
            );



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void checkoutFinal(){
        try{
            e=driver.findElement(By.xpath("//div/span"));
            wait.until(ExpectedConditions.textToBePresentInElement(
                    e,"Checkout: Overview"
            ));

            //Confirming no of products present in cart
            int noOfProducts=driver.findElements(By.xpath("//div[@class='cart_item']")).size();
            Assert.assertEquals(noOfProducts,products.length,"No of Products Ordered does not match");

            //Page Scroll to finish button
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true)",driver.findElement(By.id("finish")));

            wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));

            //Printing Payment Info
            System.out.println("Payment Information : "+driver.findElement(
                    By.cssSelector("div[data-test='payment-info-value']")).getText()
            );

            System.out.println("Shipping Information : "+driver.findElement(
                    By.cssSelector("div[data-test='shipping-info-value']")).getText()
            );



            //Matching total item pricing
            Float calculatedPrice= Float.valueOf(driver.findElement(
                    By.cssSelector("div[data-test='subtotal-label']"))
                    .getText().split(":")[1]
                    .substring(2));

            if(calculatedPrice==perProductPrice){
                System.out.println("Total Price Matched");
            }
            else{
                System.out.println("Total Price Mismatched "
                +calculatedPrice
                );
            }


            System.out.println("Item Total : "+driver.findElement(
                    By.cssSelector("div[data-test='subtotal-label']")).getText().split(":")[1]
            );

            System.out.println("Tax : "+driver.findElement(
                    By.cssSelector("div[data-test='tax-label']")).getText().split(":")[1]
            );

            System.out.println("Total : "+driver.findElement(
                    By.cssSelector("div[data-test='total-label']")).getText().split(":")[1]
            );





        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void finish() {
        try{
            //Click on finish button
            driver.findElement(By.id("finish")).click();

            wait.until(ExpectedConditions.textToBePresentInElement(
                    driver.findElement(By.xpath("//div/span")),"Checkout: Complete!"
            ));


            Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-complete.html",
                    "checkout not complete yet");

            System.out.println(driver.findElement(By
                    .xpath("//h2[@data-test='complete-header']"))
                    .getText());



        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void logout(){
        try{
            //top left button click
            driver.findElement(By.id("react-burger-menu-btn")).click();
            //Thread.sleep(800);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By
                    .xpath("//div[@class='bm-menu']")));


            //will wait for logout link to open
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
            wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
            driver.findElement(By.id("logout_sidebar_link")).click();


            Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");


        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
