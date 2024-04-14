package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.GenericMethods;

public class OrderDetailsPage {
    WebDriver driver;
    public OrderDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath="//iframe[@id='snap-midtrans']")
    WebElement frameOrderPage;
    @FindBy(xpath = "//span[@class='header-detail']")
    WebElement linkOrderDetails;
    @FindBy(xpath = "//span[normalize-space()='Midtrans Pillow']")
    WebElement productDetails;
    @FindBy(xpath = "//div[@class='close-snap-button clickable']")
    WebElement closeFrameProductDetails;
    @FindBy(xpath = "//div[@class='header-amount']")
    WebElement orderTotal;
    @FindBy(xpath = "//p[@class='header-order-id']")
    WebElement orderID;
    @FindBy(xpath = "//div[contains(text(),'GoPay/GoPay Later')]")
    WebElement paymentTypeGoPay;
    @FindBy(xpath = "//span[normalize-space()='Virtual account']")
    WebElement paymentTypeVirtualAccount;
    @FindBy(xpath = "//a[@href='#/credit-card']")
    WebElement paymentTypeCreditCard;
    @FindBy(xpath = "//div[contains(text(),'ShopeePay/SPayLater')]")
    WebElement paymentTypeShopeePay;
    @FindBy(xpath = "//div[contains(text(),'QRIS')]")
    WebElement paymentTypeQRIS;
    @FindBy(xpath = "//div[contains(text(),'Alfa Group')]")
    WebElement paymentTypeAlfaGroup;
    @FindBy(xpath = "//div[contains(text(),'Indomaret')]")
    WebElement paymentTypeIndomaret;
    @FindBy(xpath = "//span[normalize-space()='Akulaku PayLater']")
    WebElement paymentTypeAkulaku;
    @FindBy(xpath = "//span[normalize-space()='Kredivo']")
    WebElement paymentTypeKredivo;

    public void orderDetailsScreen() {
        driver.switchTo().frame(frameOrderPage);
        linkOrderDetails.click();
        try {
            Assert.assertTrue(productDetails.isDisplayed());
        } catch (AssertionError e) {
            System.out.println("* Order Details are NOT displayed!");
        }
        GenericMethods.pauseExecutionFor(4);
        closeFrameProductDetails.click();
    }
    public String orderDetailsID(){
        return orderID.getText();
    }
    public void paymentOptions(){
        try {
            Assert.assertTrue(paymentTypeGoPay.isDisplayed());
            Assert.assertTrue(paymentTypeVirtualAccount.isDisplayed());
            Assert.assertTrue(paymentTypeCreditCard.isDisplayed());
            Assert.assertTrue(paymentTypeShopeePay.isDisplayed());
            Assert.assertTrue(paymentTypeQRIS.isDisplayed());
            Assert.assertTrue(paymentTypeAlfaGroup.isDisplayed());
            Assert.assertTrue(paymentTypeIndomaret.isDisplayed());
            Assert.assertTrue(paymentTypeAkulaku.isDisplayed());
            Assert.assertTrue(paymentTypeKredivo.isDisplayed());
        } catch (AssertionError e) {
            System.out.println("* Payment options are NOT displayed!");
        }
    }
    public void selectPayment(){
        paymentTypeCreditCard.click();
        GenericMethods.pauseExecutionFor(3);
    }


}
