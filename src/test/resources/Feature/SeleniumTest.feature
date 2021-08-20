@web
Feature: Test Automation Java Selenium

  Background:
    Given that I am logged

  @buy-clothes-csv
  Scenario: Buy clothes successfully
    And choose the product to buy
    And validate the product on the checkout page
    And validate the adress
    When choose the payment method
    Then the purchase have to be successfully done

