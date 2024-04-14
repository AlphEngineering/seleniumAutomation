package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.GenericMethods;

import static utils.ReadData.propertyCustomer;

public class PaymentDetailsPage {
    WebDriver driver;
    public PaymentDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath="//div[@class='header-amount']")
    WebElement orderTotalBeforePromo;
    @FindBy(xpath = "//span[@class='title-text text-actionable-bold']")
    WebElement correctPaymentSelected;
    @FindBy(xpath = "//input[@autocomplete='cc-number']")
    WebElement inputCardNumber;
    @FindBy(xpath = "//input[@id='card-expiry']")
    WebElement inputCardExpiration;
    @FindBy(xpath = "//input[@id='card-cvv']")
    WebElement inputCardCVV;
    @FindBy(xpath = "//label[@for='690']")
    WebElement promoFlashSaleButton;
    @FindBy(xpath = "//label[@for='628']")
    WebElement promoTestingButton;
    @FindBy(xpath = "//label[@for='no-promo']")
    WebElement promoNoneButton;
    @FindBy(xpath = "//span[contains(text(),'-Rp1.000')]")
    WebElement promoAmount;
    @FindBy(xpath ="//div[@class='header-amount']")
    WebElement orderTotalAfterPromo;
    @FindBy(xpath ="//button[@type='button']")
    WebElement buttonPayNow;
    public void paymentDetails(){
        try{
            Assert.assertTrue(correctPaymentSelected.isDisplayed());
        }catch(AssertionError e){
            System.out.println("* Payment Credit/Debit card is NOT selected!");
        }
    }
    public void orderTotalIsDisplayed(){
        try{
            Assert.assertTrue(orderTotalBeforePromo.isDisplayed());
        }catch(AssertionError e){
            System.out.println("* Order total is NOT displayed!");
        }
    }
    public String orderTotalPrePromo() {
        return orderTotalBeforePromo.getText();
    }
    public String orderPromoAmount(){
        return promoAmount.getText();
    }
    public String orderTotalPostPromo(){
        return orderTotalAfterPromo.getText();
    }
    public void inputCardDetails(){
        inputCardNumber.sendKeys(propertyCustomer.getProperty("cardNumber"));
        inputCardExpiration.sendKeys(propertyCustomer.getProperty("cardExpiration"));
        inputCardCVV.sendKeys(propertyCustomer.getProperty("cardCVV"));
    }
    public void entryCardDetails(){
        try{
            Assert.assertTrue(inputCardNumber.isDisplayed());
            Assert.assertTrue(inputCardExpiration.isDisplayed());
            Assert.assertTrue(inputCardCVV.isDisplayed());
        }catch (AssertionError e) {
            System.out.println("* Credit/Debit card entry was NOT successful!");
        }
    }
    public void buttonPayNow(){
        buttonPayNow.click();
        GenericMethods.pauseExecutionFor(6);
    }


}
