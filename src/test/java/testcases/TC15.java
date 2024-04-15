package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.*;
import utils.BrowserFactory;

public class TC15 {
    WebDriver driver = null;
    HomePage homePage;
    ShoppingDetailsPage shoppingDetailsPage;
    OrderDetailsPage orderDetailsPage;
    PaymentDetailsPage paymentDetailsPage;
    TransactionDetailsPage transactionDetailsPage;

    @BeforeClass
    public void setup(){
        driver = BrowserFactory.launchBrowser("browser");
        driver.get("https://demo.midtrans.com/");
        homePage = new HomePage(driver);
        shoppingDetailsPage = new ShoppingDetailsPage(driver);
        orderDetailsPage = new OrderDetailsPage(driver);
        paymentDetailsPage = new PaymentDetailsPage(driver);
        transactionDetailsPage = new TransactionDetailsPage(driver);
    }
    @Test
    public void verifyButtonNavigation(){
        System.out.println("* URL:  "+homePage.getCurrentUrl());
        homePage.verifyBuyNowIsDisplayed();
        homePage.clickButtonBuyNow();
        shoppingDetailsPage.verifyShoppingCartIsDisplayed();
        System.out.println("* TC_1 Button 'BuyNow' redirects to Shopping Cart page.");
    }
    @Test
    public void verifyProductAddedToCart(){
        shoppingDetailsPage.productAddedToCart();
        System.out.println("* TC_2 Button 'Midtrans Pillow' is added to Cart @ Rp 20,000.");
    }
    @Test
    public void verifyCustomerDetails(){
        shoppingDetailsPage.customerDetailsDisplayed();
        System.out.println("* TC_3 Customer Details are displayed on Shopping Cart.");
    }
    @Test
    public void verifyCustomerDetailsCanEdit(){
        shoppingDetailsPage.inputCustomerDetails();
        System.out.println("* TC_4 Customer Details are editable and user can enter details.");
    }
    @Test
    public void verifyCheckoutNavigation(){
        shoppingDetailsPage.clickToCheckOut();
        System.out.println("* TC_5 Button 'Checkout' navigates to Order Details page.");
    }
    @Test
    public void verifyProductDetails(){
        orderDetailsPage.orderDetailsScreen();
        System.out.println("* TC_6 Product details confirmed. "+orderDetailsPage.orderDetailsID());
    }
    @Test
    public void verifyOrderDetailsCheckoutButton(){
        System.out.println("* TC_7 No additional 'Checkout' button. Payments already listed.");
    }
    @Test
    public void verifyPaymentsAvailable(){
        orderDetailsPage.paymentOptions();
        System.out.println("* TC_8 All payments listed: GoPay, Virtual Account, Credit/Debit, ShopeePay, QRIS, Alfa Group, Indomaret, Akulaku, Kredivo");
    }
    @Test
    public void verifyPaymentSelectionAndNavigation(){
        orderDetailsPage.selectPayment();
        paymentDetailsPage.paymentDetails();
        System.out.println("* TC_9 Payment Credit/Debit selected. Navigation to Card Details page successful.");
    }
    @Test
    public void verifyOrder(){
        paymentDetailsPage.orderTotalIsDisplayed();
        System.out.println("* TC_10 Order total before Promo: "+paymentDetailsPage.orderTotalPrePromo());
        paymentDetailsPage.inputCardDetails();
        System.out.println("        Promo amount: "+paymentDetailsPage.orderPromoAmount());
        System.out.println("        Order total after Promo: "+paymentDetailsPage.orderTotalPostPromo());
    }
    @Test
    public void verifyCardEntryAndPay(){
        paymentDetailsPage.entryCardDetails();
        paymentDetailsPage.buttonPayNow();
        System.out.println("* TC_11 Credit/Debit card entry confirmed. 'Pay Now' button clicked");
    }
    @Test
    public void verifyTransactionDetailsPage(){
        transactionDetailsPage.confirmTransactionDetails();
        System.out.println("* TC_12 Clicking 'Pay Now' redirects user to Transaction Details page.");
        System.out.println("        Merchant Details Confirmed.");
        System.out.println("        Merchant Name: "+transactionDetailsPage.getMerchantName());
        System.out.println("        Transaction Amount: "+transactionDetailsPage.getTransactionAmount());
        System.out.println("        Transaction Time: "+transactionDetailsPage.getTransactionTime());
        System.out.println("        Card Number: "+transactionDetailsPage.getCardNumber());
    }

    @Test
    public void verifyCancelOrder(){
        transactionDetailsPage.transactionFailedCanceled();
        System.out.println("* TC_15 Clicking 'Cancel' button returns - Order Failed.");
        System.out.println("        Message: "+transactionDetailsPage.getFailedMessage());
        transactionDetailsPage.continueAfterCancelOrder();
        System.out.println("        Returned Home - "+transactionDetailsPage.transactionCancelledMessageFinal());
    }
    @AfterClass
    public void quit(){
        driver.close();
    }

}


