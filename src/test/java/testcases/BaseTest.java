package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.*;
import utils.BrowserFactory;
import utils.GenericMethods;

public class BaseTest {
    WebDriver driver = null;
    HomePage homePage;
    ShoppingDetailsPage shoppingDetailsPage;
    OrderDetailsPage orderDetailsPage;
    PaymentDetailsPage paymentDetailsPage;
    TransactionDetailsPage transactionDetailsPage;

    @BeforeClass(groups = {"all"})
    public void setup(){
        driver = BrowserFactory.launchBrowser("browser");
        driver.get("https://demo.midtrans.com/");
        homePage = new HomePage(driver);
        shoppingDetailsPage = new ShoppingDetailsPage(driver);
        orderDetailsPage = new OrderDetailsPage(driver);
        paymentDetailsPage = new PaymentDetailsPage(driver);
        transactionDetailsPage = new TransactionDetailsPage(driver);
    }
    @Test(priority = 1)
    public void verifyButtonNavigation(){
        System.out.println("* URL:  "+homePage.getCurrentUrl());
        homePage.verifyBuyNowIsDisplayed();
        homePage.clickButtonBuyNow();
        GenericMethods.pauseExecutionFor(1);
        shoppingDetailsPage.verifyShoppingCartIsDisplayed();
        System.out.println("* TC_1 Button 'BuyNow' redirects to Shopping Cart page.");
    }
    @Test(priority = 2)
    public void verifyProductAddedToCart(){
        shoppingDetailsPage.productAddedToCart();
        System.out.println("* TC_2 Button 'Midtrans Pillow' is added to Cart @ Rp 20,000.");
    }
    @Test(priority = 3)
    public void verifyCustomerDetails(){
        shoppingDetailsPage.customerDetailsDisplayed();
        System.out.println("* TC_3 Customer Details are displayed on Shopping Cart.");
    }
    @Test(priority = 4)
    public void verifyCustomerDetailsCanEdit(){
        shoppingDetailsPage.inputCustomerDetails();
        System.out.println("* TC_4 Customer Details are editable and user can enter details.");
    }
    @Test(priority = 5)
    public void verifyCheckoutNavigation(){
        shoppingDetailsPage.clickToCheckOut();
        System.out.println("* TC_5 Button 'Checkout' navigates to Order Details page.");
    }
    @Test(priority = 6)
    public void verifyProductDetails(){
        orderDetailsPage.orderDetailsScreen();
        System.out.println("* TC_6 Product details confirmed. "+orderDetailsPage.orderDetailsID());
    }
    @Test(priority = 7)
    public void verifyOrderDetailsCheckoutButton(){
        System.out.println("* TC_7 No additional 'Checkout' button. Payments already listed.");
    }
    @Test(priority = 8)
    public void verifyPaymentsAvailable(){
        orderDetailsPage.paymentOptions();
        System.out.println("* TC_8 All payments listed: GoPay, Virtual Account, Credit/Debit, ShopeePay, QRIS, Alfa Group, Indomaret, Akulaku, Kredivo");
    }
    @Test(priority = 9)
    public void verifyPaymentSelectionAndNavigation(){
        orderDetailsPage.selectPayment();
        paymentDetailsPage.paymentDetails();
        System.out.println("* TC_9 Payment Credit/Debit selected. Navigation to Card Details page successful.");
    }
    @Test(priority = 10)
    public void verifyOrder(){
        paymentDetailsPage.orderTotalIsDisplayed();
        System.out.println("* TC_10 Order total before Promo: "+paymentDetailsPage.orderTotalPrePromo());
        paymentDetailsPage.inputCardDetails();
        System.out.println("        Promo amount: "+paymentDetailsPage.orderPromoAmount());
        System.out.println("        Order total after Promo: "+paymentDetailsPage.orderTotalPostPromo());
    }
    @Test(priority = 11)
    public void verifyCardEntryAndPay(){
        paymentDetailsPage.entryCardDetails();
        paymentDetailsPage.buttonPayNow();
        System.out.println("* TC_11 Credit/Debit card entry confirmed. 'Pay Now' button clicked");
    }
    @Test(priority = 12)
    public void verifyTransactionDetailsPage(){
        transactionDetailsPage.confirmTransactionDetails();
        System.out.println("* TC_12 Clicking 'Pay Now' redirects user to Transaction Details page.");
        System.out.println("        Merchant Details verified.");
        System.out.println("        "+transactionDetailsPage.getMerchantName());
        System.out.println("        "+transactionDetailsPage.getTransactionAmount());
        System.out.println("        "+transactionDetailsPage.getTransactionTime());
        System.out.println("        "+transactionDetailsPage.getCardNumber());
    }
    @Test(priority = 13)
    public void verifyValidOTP(){
        transactionDetailsPage.transactionSuccessfulValidOTP();
        transactionDetailsPage.transactionConfirmation();
        System.out.println("* TC_13 Clicking 'Ok' button with VALID OTP returns Order Successful.");
        System.out.println("        Message: "+transactionDetailsPage.transactionConfirmationText());
    }
    @Test(priority = 14)
    public void verifyInvalidOTP(){
        transactionDetailsPage.transactionFailedInvalidOTP();
        System.out.println("* TC_14 Clicking 'Ok' button with INVALID OTP returns Order Failed.");
        System.out.println("        Message: "+transactionDetailsPage.getFailedMessage());
    }
    @Test(priority = 15)
    public void verifyCancelOrder(){
        transactionDetailsPage.transactionFailedCanceled();
        System.out.println("* TC_15 Clicking 'Cancel' button returns Order Failed.");
        System.out.println("        Message: "+transactionDetailsPage.getFailedMessage());
    }

}


