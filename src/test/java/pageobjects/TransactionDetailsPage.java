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

    @FindBy(xpath="//iframe[@title='3ds-iframe']")
    WebElement transactionFrame;
    @FindBy(xpath="//iframe[@id='snap-midtrans']")
    WebElement frameOrderPage;
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
    @FindBy(xpath = "//*[contains(text(),'Payment declined by bank')]")
    WebElement paymentCanceledOrInvalidScreen;
    @FindBy(xpath = "//button[normalize-space()='OK']")
    WebElement buttonPaymentCanceledOK;
    @FindBy(xpath = "//button[normalize-space()='Back']")
    WebElement buttonPaymentInvalidBack;
    @FindBy(xpath = "//div[@class='text-headline medium']")//div[@class='success-wrapper']
    WebElement transactionSuccessfulPopup;
    @FindBy(xpath = "//span[@data-reactid='.0.0.0.2.0.1.0.0:0']")
    WebElement transactionConfirmed1;
    @FindBy(xpath = "//span[@data-reactid='.0.0.0.2.0.1.0.0:2']")
    WebElement transactionConfirmed2;
    @FindBy(xpath="//div[@class='close-snap-button clickable']")
    WebElement closeTransactionWindow;
    @FindBy(xpath = "//span[@data-reactid='.0.0.0.2.0.1.0.0:0']")
    WebElement transactionInvalidFinalMessage;
    @FindBy(xpath = "//span[@data-reactid='.0.0.0.2.0.1.0.0:0']")
    WebElement transactionCanceledFinalMessage;
    public void confirmTransactionDetails(){
        driver.switchTo().frame(transactionFrame);
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
    public void transactionSubmitValidOTP(){ //Submits valid merchant OTP
        inputMerchantOTP.sendKeys(propertyMerchant.getProperty("validOTP"));
        buttonOk.click();
    }
    public void transactionConfirmation(){
        GenericMethods.pauseExecutionFor(8);
        driver.switchTo().defaultContent();
        try{
            Assert.assertTrue(transactionConfirmed1.isDisplayed());
            Assert.assertTrue(transactionConfirmed2.isDisplayed());
        }catch (AssertionError e) {
            System.out.println("* Transaction confirmation was NOT displayed!");
        }
    }
    public void returnHomeSuccess(){
        driver.getCurrentUrl();
    }
    public void returnHomeFailed(){
        driver.getCurrentUrl();
    }
    public void returnHomeCanceled(){
        driver.getCurrentUrl();
    }
    public String transactionConfirmationText1(){
        return transactionConfirmed1.getText();
    }
    public String transactionConfirmationText2(){
        return transactionConfirmed2.getText();
    }
    public void transactionSubmitInvalidOTP(){ //Submits invalid merchant OTP
        inputMerchantOTP.sendKeys(propertyMerchant.getProperty("invalidOTP"));
        buttonOk.click();
        GenericMethods.pauseExecutionFor(4);
        driver.switchTo().parentFrame();
        try{
            Assert.assertTrue(paymentCanceledOrInvalidScreen.isDisplayed());
        }catch (AssertionError e) {
            System.out.println("* Payment Invalid screen is NOT displayed!");
        }
    }
    public String getFailedMessage(){
        return paymentCanceledOrInvalidScreen.getText();
    }
    public void continueAfterInvalidOTP(){ //Continue 'OK' after Invalid OTP
        buttonPaymentCanceledOK.click();
        closeTransactionWindow.click();
        GenericMethods.pauseExecutionFor(4);
        driver.switchTo().defaultContent();
    }
    public String transactionInvalidMessageFinal(){
        return transactionInvalidFinalMessage.getText();
    }

    public void transactionFailedCanceled(){ //Cancels the transaction
        buttonCancel.click();
        GenericMethods.pauseExecutionFor(4);
        driver.switchTo().parentFrame();
        try{
            Assert.assertTrue(paymentCanceledOrInvalidScreen.isDisplayed());
        }catch (AssertionError e) {
            System.out.println("* Payment Canceled screen is NOT displayed!");
        }
    }
    public void continueAfterCancelOrder(){ //Continue 'OK' after Cancel Order.
        buttonPaymentCanceledOK.click();
        closeTransactionWindow.click();
        GenericMethods.pauseExecutionFor(4);
        driver.switchTo().defaultContent();
    }
    public String transactionCancelledMessageFinal(){
        return transactionCanceledFinalMessage.getText();
    }
    public void resetTimer(){
        buttonResetTimer.click();
    }
}
