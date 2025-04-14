package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(xpath = "//a[contains(@href,'/women')]/span[text()='Women']")
    private WebElement womenDropdown;

    @FindBy(xpath = "//a[contains(@href,'/men')]/span[text()='Men']")
    private WebElement menDropdown;

    @FindBy(xpath = "//a[contains(@href,'/gear')]/span[text()='Gear']")
    private WebElement gearDropdown;

    //WebElement Declaration for Women Dropdown Starts here
    @FindBy(xpath = "//a[contains(@href,'women')]/span[contains(text(),'Tops')]")
    private WebElement w_tops;

    @FindBy(xpath = "//a[contains(@href,'women')]/span[contains(text(),'Bottoms')]")
    private WebElement w_bottom;

    @FindBy(xpath = "//a[contains(@href,'/tops-women')]/span[text()='Jackets']")
    private WebElement w_jackets;

    @FindBy(xpath = "//a[contains(@href,'/tops-women')]/span[text()='Hoodies & Sweatshirts']")
    private WebElement w_hoodies;

    @FindBy(xpath = "//a[contains(@href,'/tops-women')]/span[text()='Tees']")
    private WebElement w_tees;

    @FindBy(xpath = "//a[contains(@href,'/tops-women')]/span[contains(text(),'Bras')]")
    private WebElement w_bras;

    @FindBy(xpath = "//a[contains(@href,'/bottoms-women')]/span[contains(text(),'Pants')]")
    private WebElement w_pants;

    @FindBy(xpath = "//a[contains(@href,'/bottoms-women')]/span[contains(text(),'Shorts')]")
    private WebElement w_shorts;

    //WebElement Declaration for Women Dropdown ends here

    //WebElement Declaration for Men Dropdown Starts here
    @FindBy(xpath = "//a[contains(@href,'tops-men')]/span[contains(text(),'Tops')]")
    private WebElement m_tops;

    @FindBy(xpath = "//a[contains(@href,'/bottoms-men')]/span[contains(text(),'Bottoms')]")
    private WebElement m_bottom;

    @FindBy(xpath = "//a[contains(@href,'/tops-men')]/span[text()='Jackets']")
    private WebElement m_jackets;

    @FindBy(xpath = "//a[contains(@href,'/tops-men')]/span[text()='Hoodies & Sweatshirts']")
    private WebElement m_hoodies;

    @FindBy(xpath = "//a[contains(@href,'/tops-men')]/span[text()='Tees']")
    private WebElement m_tees;

    @FindBy(xpath = "//a[contains(@href,'/tops-men')]/span[contains(text(),'Tanks')]")
    private WebElement m_tanks;

    @FindBy(xpath = "//a[contains(@href,'/bottoms-men')]/span[contains(text(),'Pants')]")
    private WebElement m_pants;

    @FindBy(xpath = "//a[contains(@href,'/bottoms-men')]/span[contains(text(),'Shorts')]")
    private WebElement m_shorts;

    //WebElement Declaration for Men Dropdown Ends here

    //WebElement Declaration for Gear Dropdown Starts here
    @FindBy(xpath = "//a[contains(@href,'gear')]/span[contains(text(),'Bags')]")
    private WebElement gear_bags;

    @FindBy(xpath = "//a[contains(@href,'gear')]/span[contains(text(),'Fitness')]")
    private WebElement gear_fitness;

    @FindBy(xpath = "//a[contains(@href,'gear')]/span[contains(text(),'Watches')]")
    private WebElement gear_watch;

    //WebElement Declaration for Gear Dropdown Ends here





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

    //Navigation for Women Dropdown
    public void navToWomen(String item){
        try{
            Actions action = new Actions(driver);
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

            if(item.contains("Jackets")){
                //Hover Action on Women Dropdown
                action.moveToElement(womenDropdown).perform();

                wait.until(ExpectedConditions.visibilityOf(w_tops));
                action.moveToElement(w_tops).perform();

                wait.until(ExpectedConditions.visibilityOf(w_jackets));
                action.moveToElement(w_jackets).click().perform();

            }
            else if(item.contains("Hoodies")){
                action.moveToElement(womenDropdown).perform();

                wait.until(ExpectedConditions.visibilityOf(w_tops));
                action.moveToElement(w_tops).perform();

                wait.until(ExpectedConditions.visibilityOf(w_hoodies));
                action.moveToElement(w_hoodies).click().perform();

            }
            else if(item.contains("Tees")){
                action.moveToElement(womenDropdown).perform();

                wait.until(ExpectedConditions.visibilityOf(w_tops));
                action.moveToElement(w_tops).perform();

                wait.until(ExpectedConditions.visibilityOf(w_tees));
                action.moveToElement(w_tees).click().perform();

            }
            else if(item.contains("Bras")){
                action.moveToElement(womenDropdown).perform();

                wait.until(ExpectedConditions.visibilityOf(w_tops));
                action.moveToElement(w_tops).perform();

                wait.until(ExpectedConditions.visibilityOf(w_bras));
                action.moveToElement(w_bras).click().perform();

            }
            else if(item.contains("Pants")){
                action.moveToElement(womenDropdown).perform();

                wait.until(ExpectedConditions.visibilityOf(w_bottom));
                action.moveToElement(w_bottom).perform();

                wait.until(ExpectedConditions.visibilityOf(w_pants));
                action.moveToElement(w_pants).click().perform();

            }
            else if(item.contains("Shorts")){
                action.moveToElement(womenDropdown).perform();

                wait.until(ExpectedConditions.visibilityOf(w_bottom));
                action.moveToElement(w_bottom).perform();

                wait.until(ExpectedConditions.visibilityOf(w_shorts));
                action.moveToElement(w_shorts).click().perform();

            }
        } catch (Exception e) {
            System.out.println("Error in navToWomen");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    //Navigation for Men Dropdown
    public void navToMen(String item){
        try{
            Actions action = new Actions(driver);
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

            if(item.contains("Jackets")){
                action.moveToElement(menDropdown).perform();

                wait.until(ExpectedConditions.visibilityOf(m_tops));
                action.moveToElement(m_tops).perform();

                wait.until(ExpectedConditions.visibilityOf(m_jackets));
                action.moveToElement(m_jackets).click().perform();

            }
            else if(item.contains("Hoodies")){
                action.moveToElement(menDropdown).perform();

                wait.until(ExpectedConditions.visibilityOf(m_tops));
                action.moveToElement(m_tops).perform();

                wait.until(ExpectedConditions.visibilityOf(m_hoodies));
                action.moveToElement(m_hoodies).click().perform();

            }
            else if(item.contains("Tees")){
                action.moveToElement(menDropdown).perform();

                wait.until(ExpectedConditions.visibilityOf(m_tops));
                action.moveToElement(m_tops).perform();

                wait.until(ExpectedConditions.visibilityOf(m_tees));
                action.moveToElement(m_tees).click().perform();

            }
            else if(item.contains("Tanks")){
                action.moveToElement(menDropdown).perform();

                wait.until(ExpectedConditions.visibilityOf(m_tops));
                action.moveToElement(m_tops).perform();

                wait.until(ExpectedConditions.visibilityOf(m_tanks));
                action.moveToElement(m_tanks).click().perform();

            }
            else if(item.contains("Pants")){
                action.moveToElement(menDropdown).perform();

                wait.until(ExpectedConditions.visibilityOf(m_bottom));
                action.moveToElement(m_bottom).perform();

                wait.until(ExpectedConditions.visibilityOf(m_pants));
                action.moveToElement(m_pants).click().perform();

            }
            else if(item.contains("Shorts")){
                action.moveToElement(menDropdown).perform();

                wait.until(ExpectedConditions.visibilityOf(m_bottom));
                action.moveToElement(m_bottom).perform();

                wait.until(ExpectedConditions.visibilityOf(m_shorts));
                action.moveToElement(m_shorts).click().perform();

            }
        } catch (Exception e) {
            System.out.println("Error in navToMen");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void navToGear(String item){
        try{
            Actions action = new Actions(driver);
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

            if(item.contains("Bags")){
                action.moveToElement(gearDropdown).perform();
                wait.until(ExpectedConditions.visibilityOf(gear_bags));
                action.moveToElement(gear_bags).click().perform();
            }
            else if(item.contains("Fitness")){
                action.moveToElement(gearDropdown).perform();
                wait.until(ExpectedConditions.visibilityOf(gear_fitness));
                action.moveToElement(gear_fitness).click().perform();
            }
            else if(item.contains("Watches")){
                action.moveToElement(gearDropdown).perform();
                wait.until(ExpectedConditions.visibilityOf(gear_watch));
                action.moveToElement(gear_watch).click().perform();
            }

        } catch (Exception e) {
            System.out.println("Error in navToGear");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


}
