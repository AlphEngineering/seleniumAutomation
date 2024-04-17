package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pageobjects.*;
import utils.BrowserFactory;
import utils.GenericMethods;

import static utils.ReadData.propertyMerchant;

public class PurchaseProductStepDef {
    WebDriver driver = null;
    HomePage homePage;
    ShoppingDetailsPage shoppingDetailsPage;
    OrderDetailsPage orderDetailsPage;
    PaymentDetailsPage paymentDetailsPage;
    TransactionDetailsPage transactionDetailsPage;

    @Before
    public void setup(){
        driver = BrowserFactory.launchBrowser("browser");
        driver.get("https://demo.midtrans.com/");
        homePage = new HomePage(driver);
        shoppingDetailsPage = new ShoppingDetailsPage(driver);
        orderDetailsPage = new OrderDetailsPage(driver);
        paymentDetailsPage = new PaymentDetailsPage(driver);
        transactionDetailsPage = new TransactionDetailsPage(driver);
    }
    @Given("Website is loaded")
    public void websiteIsLoaded(){
        homePage.verifyBuyNowIsDisplayed();
    }
    @When("User clicks Buy Now button")
    public void verifyButtonNavigation(){
        homePage.clickButtonBuyNow();
    }
    @Then("Website navigates to Shopping Details page")
    public void verifyShoppingCart(){
        shoppingDetailsPage.verifyShoppingCartIsDisplayed();
    }
    @And("Product Midtrans Pillow is added to Cart")
    public void verifyProductAddedToCart(){
        shoppingDetailsPage.productAddedToCart();
    }
    @And("Customer details are listed in Cart")
    public void verifyCustomerDetails(){
        shoppingDetailsPage.customerDetailsDisplayed();
    }
    @And("User enters their information on Shopping Details page")
    public void verifyCustomerDetailsCanEdit(){
        shoppingDetailsPage.inputCustomerDetails();
    }
    @And("User clicks Checkout button")
    public void verifyCheckoutNavigation(){
        shoppingDetailsPage.clickToCheckOut();
    }
    @Then("Website navigates to Order Details page")
    public void verifyProductDetailsScreen(){
        orderDetailsPage.orderDetailsScreen();

    }
    @And("Product details confirmed")
    public void productDetailsConfirmed(){
        orderDetailsPage.orderDetailsID();
    }
    @And("Payments are listed")
    public void verifyPaymentsAvailable(){
        orderDetailsPage.paymentOptions();
    }
    @And("Credit-Debit payment is selected")
    public void verifyPaymentSelection(){
        orderDetailsPage.selectPayment();
    }
    @Then("Website navigates to Card Details page")
    public void verifyNavigationToPaymentPage(){
        paymentDetailsPage.paymentDetails();
        paymentDetailsPage.orderTotalIsDisplayed();

    }
    @And("Order displays total before Promo is applied")
    public void verifyOrderBeforePromo(){
        paymentDetailsPage.orderTotalPrePromo();
        paymentDetailsPage.inputCardDetails();
    }
    @And("Order displays Promo amount")
    public void verifyPromotionAmount(){
        paymentDetailsPage.orderPromoAmount();
    }
    @And("Order displays total after Promo is applied")
    public void verifyOrderAfterPromo(){
        paymentDetailsPage.orderTotalPostPromo();
    }
    @And("Card entry is displayed")
    public void verifyCardEntryAndPay(){
        paymentDetailsPage.entryCardDetails();
    }
    @And("User clicks Pay Now button")
    public void verifyPayNowButton(){
        paymentDetailsPage.buttonPayNow();
    }
    @Then("Website navigates to Transaction Details page")
    public void verifyTransactionDetailsPage(){
        transactionDetailsPage.confirmTransactionDetails();
    }
    @And("Merchant details are listed")
    public void verifyMerchantDetails(){
        transactionDetailsPage.getMerchantName();
        transactionDetailsPage.getTransactionAmount();
        transactionDetailsPage.getTransactionTime();
        transactionDetailsPage.getCardNumber();
    }
    //Using Valid OTP
    @And("User clicks Ok button with valid {string}")
    public void verifyValidOTP(String OTP){
        OTP = propertyMerchant.getProperty("validOTP");
        transactionDetailsPage.transactionSubmitValidOTP();
    }
    @Then("Order returns Successful")
    public void orderSuccessful(){
        transactionDetailsPage.transactionConfirmation();
    }
    @And("Navigates to Homepage - Success")
    public void returnHomePageSuccessful(){
        transactionDetailsPage.returnHomeSuccess();
    }
    @But("Displays Success confirmation message")
    public void returnHomePageSuccessfulMessage(){
        transactionDetailsPage.transactionConfirmationText1();
        transactionDetailsPage.transactionConfirmationText2();
    }

    //Using Invalid OTP
    @And("User clicks Ok button with invalid {string}")
    public void verifyInvalidOTP(String OTP){
        OTP = propertyMerchant.getProperty("invalidOTP");
        transactionDetailsPage.transactionSubmitInvalidOTP();
    }
    @Then("Order returns Failed")
    public void orderFailed() {
        transactionDetailsPage.getFailedMessage();
        transactionDetailsPage.continueAfterInvalidOTP();
    }
    @And("Navigates to Homepage - Failed")
    public void returnHomePageFailed(){
        transactionDetailsPage.returnHomeFailed();
    }
    @But("Displays Failed confirmation message")
    public void returnHomePageFailedMessage(){
        transactionDetailsPage.transactionInvalidMessageFinal();
    }

    //Cancel Order
    @And("User clicks Cancel button")
    public void verifyCancelOrder(){
        transactionDetailsPage.transactionFailedCanceled();
    }
    @Then("Order returns Canceled")
    public void orderCanceled() {
        transactionDetailsPage.getFailedMessage();
        transactionDetailsPage.continueAfterCancelOrder();

    }
    @And("Navigates to Homepage - Canceled")
    public void returnHomePageCanceled(){
        transactionDetailsPage.returnHomeCanceled();
    }
    @But("Displays Canceled confirmation message")
    public void returnHomePageCanceledMessage(){
        transactionDetailsPage.transactionCancelledMessageFinal();
    }

    @After
    public void quit(){
        GenericMethods.pauseExecutionFor(10);
        driver.close();
    }


}


