package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[@class='btn buy']")
    WebElement buttonBuyNow;
    @FindBy(xpath = "//a[@class='btn btn-signup']")
    WebElement buttonSignUp;
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
    public void verifyBuyNowIsDisplayed(){
        try {
            Assert.assertTrue(buttonBuyNow.isDisplayed());
        }catch (AssertionError e){
            System.out.println("* 'BuyNow' button is NOT displayed!");
        }
    }
    public boolean displayBuyNow(){
        return buttonBuyNow.isDisplayed();
    }
    public void clickButtonBuyNow(){
        buttonBuyNow.click();
    }

}
