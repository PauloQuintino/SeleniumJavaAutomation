@Web
Feature: Test Automation Java Selenium

  @CT-001
  Scenario: Login with different account
    Given I am in login page
    When input user and password correctly
    Then the login is successful

  @CT-002
  Scenario: Buy clothes successfully
    Given that I am logged
    And choose the product to buy
    And validate the product on the checkout page
    And validate the adress
    When choose the payment method
    Then the purchase have to be successfully done
    And the Order ID should appears at Order History

