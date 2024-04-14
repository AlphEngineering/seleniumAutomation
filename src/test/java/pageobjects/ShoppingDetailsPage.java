package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.GenericMethods;
import utils.ReadData;

import static utils.ReadData.propertyCustomer;

public class ShoppingDetailsPage {
    WebDriver driver;
    public ShoppingDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div[@class='cart-inner']//span[1]")
    WebElement shoppingCartPage;
    @FindBy(xpath = "//td[normalize-space()='Midtrans Pillow']")
    WebElement productDescription;
    @FindBy(xpath = "//td[normalize-space()='× 1']")
    WebElement productQTY;
    @FindBy(xpath = "//td[@class='amount']")
    WebElement productTotal;
    @FindBy(xpath = "//input[@value='Budi']")
    WebElement inputFullName;
    @FindBy(xpath = "//input[@value='budi@utomo.com']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@value='081808466410']")
    WebElement inputPhoneNumber;
    @FindBy(xpath = "//input[@value='Jakarta']")
    WebElement inputCity;
    @FindBy(xpath = "//textarea[contains(text(),'MidPlaza')]")
    WebElement inputAddress;
    @FindBy(xpath = "//input[@value='10220']")
    WebElement inputPostalCode;
    @FindBy(xpath = "//div[@class='cart-checkout']")
    WebElement buttonCheckOut;
    public void verifyShoppingCartIsDisplayed(){
        try {
            Assert.assertTrue(shoppingCartPage.isDisplayed());
        }catch (AssertionError e){
            System.out.println("* Shopping Cart is NOT displayed!");
        }
    }
    public void productAddedToCart(){
        try{
            Assert.assertTrue((productDescription.isDisplayed()));
            Assert.assertTrue(productQTY.isDisplayed());
            Assert.assertTrue(productTotal.isDisplayed());
        }catch(AssertionError e){
            System.out.println("* 'Midtrans Pillow' is NOT added to Cart.");
        }
    }
    public void customerDetailsDisplayed(){
        try{
            Assert.assertTrue(inputFullName.isDisplayed());
            Assert.assertTrue(inputEmail.isDisplayed());
            Assert.assertTrue(inputPhoneNumber.isDisplayed());
            Assert.assertTrue(inputCity.isDisplayed());
            Assert.assertTrue(inputAddress.isDisplayed());
            Assert.assertTrue(inputPostalCode.isDisplayed());
        }catch(AssertionError e){
            System.out.println("* Customer Details are NOT displayed on Shopping Cart!");
        }
    }
    public void inputCustomerDetails(){
        inputFullName.clear();
        inputFullName.sendKeys(propertyCustomer.getProperty("fullName"));
        inputEmail.clear();
        inputEmail.sendKeys(propertyCustomer.getProperty("eMail"));
        inputPhoneNumber.clear();
        inputPhoneNumber.sendKeys(propertyCustomer.getProperty("phoneNumber"));
        inputCity.clear();
        inputCity.sendKeys(propertyCustomer.getProperty("city"));
        inputAddress.clear();
        inputAddress.sendKeys(propertyCustomer.getProperty("address"));
        inputPostalCode.clear();
        inputPostalCode.sendKeys(propertyCustomer.getProperty("postalCode"));
    }
    public void clickToCheckOut(){
        buttonCheckOut.click();
        GenericMethods.pauseExecutionFor(3);
    }
}
