package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.GenericMethods;

import static utils.ReadData.propertyMerchant;

public class TransactionDetailsPage {
    WebDriver driver;
    public TransactionDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy (xpath = "//h1[@class='left']")
    WebElement transactionDetailsPage;
    @FindBy (xpath = "//p[@id='merchant_name']")
    WebElement merchantName;
    @FindBy (xpath = "//p[@id='txn_amount']")
    WebElement transactionAmount;
    @FindBy (xpath = "//p[@id='txn_time']")
    WebElement transactionTime;
    @FindBy (xpath = "//p[@id='card_number']")
    WebElement transactionCardNumber;
    @FindBy(xpath = "//input[@id='otp']")
    WebElement inputMerchantOTP;
    @FindBy(xpath = "//button[@name='ok']")
    WebElement buttonOk;
    @FindBy(xpath = "//button[@name='cancel']")
    WebElement buttonCancel;
    @FindBy(xpath = "//button[@name='resend']")
    WebElement buttonResetTimer;
    @FindBy(xpath = "//div[@class='cancel-modal-title']")
    WebElement paymentCanceledOrInvalidScreen;
    @FindBy(xpath = "//button[normalize-space()='OK']")
    WebElement buttonPaymentCanceledOK;
    @FindBy(xpath = "//button[normalize-space()='Back']")
    WebElement buttonPaymentInvalidBack;
    @FindBy(xpath = "//div[@class='text-headline medium']")//div[@class='success-wrapper']
    WebElement transactionSuccessfulPopup;
    @FindBy(xpath = "//span[@data-reactid='.0.0.0.2.0.1.0.0:0']")
    WebElement transactionConfirmed;

    public void confirmTransactionDetails(){
        try{
            Assert.assertTrue(transactionDetailsPage.isDisplayed());
            Assert.assertTrue(merchantName.isDisplayed());
            Assert.assertTrue(transactionAmount.isDisplayed());
            Assert.assertTrue(transactionTime.isDisplayed());
            Assert.assertTrue(transactionCardNumber.isDisplayed());
        }catch (AssertionError e) {
            System.out.println("* Transaction Details are NOT displayed!");
        }
    }
    public String getMerchantName(){
        return merchantName.getText();
    }
    public String getTransactionAmount(){
        return transactionAmount.getText();
    }
    public String getTransactionTime(){
        return transactionTime.getText();
    }
    public String getCardNumber(){
        return transactionCardNumber.getText();
    }
    public void transactionSuccessfulValidOTP(){ //Submits valid merchant OTP
        inputMerchantOTP.sendKeys(propertyMerchant.getProperty("merchantOTP"));
        buttonOk.click();
        GenericMethods.pauseExecutionFor(3);
        try{
            Assert.assertTrue(transactionSuccessfulPopup.isDisplayed());
        }catch (AssertionError e) {
            System.out.println("* Transaction successful screen was NOT displayed!");
        }
    }
    public void transactionConfirmation(){
        GenericMethods.pauseExecutionFor(6);
        try{
            Assert.assertTrue(transactionConfirmed.isDisplayed());
        }catch (AssertionError e) {
            System.out.println("* Transaction confirmation was NOT displayed!");
        }
    }
    public String transactionConfirmationText(){
        return transactionConfirmed.getText();
    }
    public void transactionFailedInvalidOTP(){ //Submits invalid merchant OTP
        inputMerchantOTP.sendKeys(propertyMerchant.getProperty("invalidOTP"));
        buttonOk.click();
        GenericMethods.pauseExecutionFor(3);
        try{
            Assert.assertTrue(paymentCanceledOrInvalidScreen.isDisplayed());
        }catch (AssertionError e) {
            System.out.println("* Payment Invalid screen is NOT displayed!");
        }
        GenericMethods.pauseExecutionFor(5);
        buttonPaymentInvalidBack.click();
    }
    public void transactionFailedCanceled(){ //Cancels the transaction
        buttonCancel.click();
        GenericMethods.pauseExecutionFor(3);
        try{
            Assert.assertTrue(paymentCanceledOrInvalidScreen.isDisplayed());
        }catch (AssertionError e) {
            System.out.println("* Payment Canceled screen is NOT displayed!");
        }
        GenericMethods.pauseExecutionFor(5);
        buttonPaymentCanceledOK.click();
    }
    public String getFailedMessage(){
        return paymentCanceledOrInvalidScreen.getText();
    }
    public void resetTimer(){
        buttonResetTimer.click();
    }
}
