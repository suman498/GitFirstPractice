package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.*;
import utils.ConfigReader;
import utils.ExcelUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;

public class EndToEndTest {

    WebDriver driver;
    WebDriverWait wait;
    pages.Homepage home;
    pages.prodTypePage prod;
    prodPage product;
    shoppingCartPage cart;
    checkoutPage checkout;

    ExtentReports extent;
    ExtentTest test;


    @BeforeSuite
    public void setUpExtentReport(){
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

    }


    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        driver = new ChromeDriver();
        home= new Homepage(driver);
        wait =  new WebDriverWait(driver, Duration.ofMillis(1400));

        driver.get(ConfigReader.properties.getProperty("url"));
        driver.manage().window().maximize();

    }

    //Data Provider Method
    @DataProvider(name="excelTests")
    public Object[][] getTestsFromExcel(){
        //return ExcelUtils.getRunnableTests();
        Object[][] data = ExcelUtils.getRunnableTests();
        System.out.println("Data from Data Provider : "+ Arrays.deepToString(data));
        return data;
    }



    @Test(dataProvider = "excelTests")
    public void endToEndTest(Map<String,String> data) throws IOException, InterruptedException {
        System.out.println("Running Test --->"+data.get("TestCase ID")+"::"+data.get("TestCase Name"));
        switch (data.get("TestCase ID")){
            case "TC001":
                custLoginTest();
                custLogoutTest();
                break;

            case "TC002":
                custLoginTest();
                addToCart(data.get("Product Type"),
                        data.get("Products to Add"),
                        data.get("Sizes"),
                        data.get("Colour"),
                        data.get("QuantityToAdd")
                );
                //Display Products in cart and print total payable
                displayCart();
                break;

            case "TC003":
                custLoginTest();
                break;

            default:
                System.out.println("No Logic Defined for TestCase : "+data.get("TestCase ID"));

        }


    }

    private void custLoginTest()  {
        try{
            test = extent.createTest("custLoginTest");
            //nested POM homepage ----> CustomerLoginPage
            CustomerLogin custLogin = home.signIn();
            custLogin.setEmail(ConfigReader.properties.getProperty("email"));
            custLogin.setPass(ConfigReader.properties.getProperty("pass"));
            //nested POM CustomerLoginPage ----> HomePage
            home = custLogin.clickLogin();
            Thread.sleep(800);

            //Take Screenshot
            File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src,new File("screenshots/LoggedIn.png"));
            test.pass("Successful Login");

            //home.signOut();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void custLogoutTest()  {
        try{
            test = extent.createTest("custLogoutTest");

            home.signOut();

            test.pass("Successfull Logout");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void navigateTo(String item){
        try{
            String prodType = item.split(" ")[1];
            System.out.println("Product type is "+
                    prodType
                    +" and it is for "
                    +item.split(" ")[0]
            );
            if(item.contains("Men")){
                home.navToMen(prodType);
            }
            else if(item.contains("Women")){
                home.navToWomen(prodType);
            }
            else if(item.contains("Gear")){
                home.navToGear(prodType);

            }

        } catch (RuntimeException e) {
            System.out.println("Error in navigateTo Method : Unable to Navigate");
            throw new RuntimeException(e);

        }
    }

    public void addToCart(String i1,String i2,String i3,String i4,String i5){
        try{
            String[] prodType_list=i1.split(",");
            String[] prod_list=i2.split(",");
            String[] prodSize_list=i3.split(",");
            String[] prodColour_list=i4.split(",");
            String[] prodQuantity_list=i5.split(",");

            for(int i=0;i<prod_list.length;i++){

                //Navigating to Correct Product Type
                navigateTo(prodType_list[i]);

                //SelectProduct
                selectProd(prodType_list[i],prod_list[i]);

                //Adding into Cart
                cartAddition(prod_list[i],prodSize_list[i],prodColour_list[i],prodQuantity_list[i]);

            }





        } catch (Exception e) {
            System.out.println("Error in Add to Cart method : Unable to add to Cart");
            throw new RuntimeException(e);
        }



    }

    public void selectProd(String item,String prodName){
        try{
            String prodType="";
            if(item.contains("Bras & Tanks")){
                prodType="Bras & Tanks";
            }
            else if(item.contains("Hoodies & Sweatshirts")){
                prodType="Hoodies & Sweatshirts";
            }
            else{
                prodType=item.split(" ")[1];
            }
            prod = home.prodTypeList();
            //Nesting prodTypePage to prodPage
            product = prod.findProd(prodType,prodName);



        } catch (Exception e) {
            System.out.println("Error in selectProd : Unable to find "+prod+" in page");
            e.printStackTrace();
        }
    }

    public void cartAddition(String prodName,String prodSize,String prodColor,String prodQuantity){
        try{
            product.verify(prodName);
            product.setSize(prodSize);
            product.setColor(prodColor);
            product.setQuantity(prodQuantity);
            product.addToCart();



        } catch (Exception e) {
            System.out.println("Error in cartAddition Method");
            e.printStackTrace();
        }
    }

    public void displayCart(){
        try{
            //Nesting productPage POM with Shopping Cart Page POM
            cart = product.openCart();
            cart.orderTotal();

            //Proceeding with Checkout
            //Nesting shoppingcartPage POM with checkout POM
            checkout = cart.checkout();


        } catch (Exception e) {
            System.out.println("Error in display cart method in test");
            e.printStackTrace();
        }
    }



    @AfterMethod
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    @AfterSuite
    public void flushReports(){
        extent.flush();
    }
}
