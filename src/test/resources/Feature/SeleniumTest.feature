
@web
Feature: Test Automation Java Selenium

  @tag1
  Scenario: Setting up web driver
    Given I visit Google page
    When search for "Selenium Tutorial"
    Then I validate de result

  @login-csv
  Scenario: Login
    Given que esteja na pagina de login
    When inserir usuario e senha corretamente:
    |user|password|
    Then o login eh validado com sucesso
