package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransactionDetailsPage {
    WebDriver driver;
    public TransactionDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy (xpath = "//h1[@class='left']")
    WebElement transactionDetails;
    @FindBy(xpath = "//input[@id='otp']")
    WebElement inputMerchantOTP;
    @FindBy(xpath = "//button[@name='ok']")
    WebElement buttonOk;
    @FindBy(xpath = "//button[@name='cancel']")
    WebElement buttonCancel;
    @FindBy(xpath = "//button[@name='resend']")
    WebElement buttonResend;
    @FindBy(xpath = "//div[@class='cancel-modal-title']")
    WebElement paymentCanceled;
    @FindBy(xpath = "//button[normalize-space()='OK']")
    WebElement buttonPaymentCanceledOK;
    @FindBy(xpath = "//button[normalize-space()='Back']")
    WebElement buttonPaymentCanceledBack;



}
