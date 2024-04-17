Feature: Purchase Product Functionality

  Scenario: Purchase product with valid OTP
    Given Website is loaded
    When User clicks Buy Now button
    Then Website navigates to Shopping Details page
    And Product Midtrans Pillow is added to Cart
    * Customer details are listed in Cart
    * User enters their information on Shopping Details page
    And User clicks Checkout button
    Then Website navigates to Order Details page
    * Product details confirmed
    * Payments are listed
    And Credit-Debit payment is selected
    Then Website navigates to Card Details page
    * Order displays total before Promo is applied
    * Order displays Promo amount
    * Order displays total after Promo is applied
    * Card entry is displayed
    And User clicks Pay Now button
    Then Website navigates to Transaction Details page
    * Merchant details are listed
    And User clicks Ok button with valid 'OTP'
    Then Order returns Successful
    And Navigates to Homepage - Success
    But Displays Success confirmation message

  Scenario: Purchase product with invalid OTP
    Given Website is loaded
    When User clicks Buy Now button
    Then Website navigates to Shopping Details page
    And Product Midtrans Pillow is added to Cart
    * Customer details are listed in Cart
    * User enters their information on Shopping Details page
    And User clicks Checkout button
    Then Website navigates to Order Details page
    * Product details confirmed
    * Payments are listed
    And Credit-Debit payment is selected
    Then Website navigates to Card Details page
    * Order displays total before Promo is applied
    * Order displays Promo amount
    * Order displays total after Promo is applied
    * Card entry is displayed
    And User clicks Pay Now button
    Then Website navigates to Transaction Details page
    * Merchant details are listed
    And User clicks Ok button with invalid 'OTP'
    Then Order returns Failed
    And Navigates to Homepage - Failed
    But Displays Failed confirmation message

  Scenario: Purchase product then Cancel Order
    Given Website is loaded
    When User clicks Buy Now button
    Then Website navigates to Shopping Details page
    And Product Midtrans Pillow is added to Cart
    * Customer details are listed in Cart
    * User enters their information on Shopping Details page
    And User clicks Checkout button
    Then Website navigates to Order Details page
    * Product details confirmed
    * Payments are listed
    And Credit-Debit payment is selected
    Then Website navigates to Card Details page
    * Order displays total before Promo is applied
    * Order displays Promo amount
    * Order displays total after Promo is applied
    * Card entry is displayed
    And User clicks Pay Now button
    Then Website navigates to Transaction Details page
    * Merchant details are listed
    And User clicks Cancel button
    Then Order returns Canceled
    And Navigates to Homepage - Canceled
    But Displays Canceled confirmation message