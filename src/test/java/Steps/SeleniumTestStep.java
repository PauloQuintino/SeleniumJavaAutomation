package Steps;

import static Core.DriverFactory.getDriver;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import Page.SeleniumTestPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class SeleniumTestStep {


    private SeleniumTestPage seleniumTestPage;

    @Given("I visit Google page")
    public void iVisitGooglePage() throws IOException {
        System.out.println("COMEÇOU");
        seleniumTestPage = new SeleniumTestPage(getDriver());
        seleniumTestPage.googlePage();
        seleniumTestPage.validateTitle();

    }

    @When("search for {string}")
    public void search_for(String videoName) throws Exception {
        seleniumTestPage = new SeleniumTestPage(getDriver());
        seleniumTestPage.search(videoName);

    }

    @Then("I validate de result")
    public void i_validate_de_result() throws Exception {
        seleniumTestPage = new SeleniumTestPage(getDriver());
        assertTrue(seleniumTestPage.validateSearchResults());
    }

    //login-csv
    @Given("que esteja na pagina de login")
    public void que_esteja_na_pagina_de_login() {
        seleniumTestPage = new SeleniumTestPage(getDriver());
		seleniumTestPage.acessAutomationPraticePage();
        seleniumTestPage.acessLoginPage();
        ;
    }

    @When("inserir usuario e senha corretamente:")
    public void inserir_usuario_e_senha_corretamente(DataTable dataTable) throws Exception {
        seleniumTestPage = new SeleniumTestPage(getDriver());
        seleniumTestPage.loginWithCSV(dataTable);
    }

    @Then("o login eh validado com sucesso")
    public void o_login_eh_validado_com_sucesso() throws InterruptedException {
            Thread.sleep(2000);
            seleniumTestPage = new SeleniumTestPage(getDriver());
            seleniumTestPage.validateSucessfullyLogin();
    }

}
