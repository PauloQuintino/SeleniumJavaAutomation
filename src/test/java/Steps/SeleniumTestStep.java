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
	public void iVisitGooglePage() throws IOException{
		//evidenceList = new ArrayList<SeleniumEvidence>();
		try {
			System.out.println("COMEÇOU");
			seleniumTestPage = new SeleniumTestPage(getDriver());
			seleniumTestPage.googlePage();
			seleniumTestPage.validateTitle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("search for {string}")
	public void search_for(String videoName) throws IOException {
		//evidenceList = new ArrayList<SeleniumEvidence>();
		try {
			seleniumTestPage = new SeleniumTestPage(getDriver());
			seleniumTestPage.search(videoName);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
	}

	@Then("I validate de result")
	public void i_validate_de_result() throws IOException {
		//evidenceList = new ArrayList<SeleniumEvidence>();
		try {
			seleniumTestPage = new SeleniumTestPage(getDriver());
			assertTrue(seleniumTestPage.validateSearchResults());	
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
			
	}
	
	private String takeScreenshot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
	
	
	//login-csv
	@Given("que esteja na pagina de login")
	public void que_esteja_na_pagina_de_login() {
		seleniumTestPage = new SeleniumTestPage(getDriver());
//		seleniumTestPage.acessAutomationPraticePage();
		seleniumTestPage.acessLoginPage();;
	}

	@When("inserir usuario e senha corretamente:")
	public void inserir_usuario_e_senha_corretamente(DataTable dataTable) {
		seleniumTestPage = new SeleniumTestPage(getDriver());
		seleniumTestPage.loginWithCSV(dataTable);
	}

	@Then("o login eh validado com sucesso")
	public void o_login_eh_validado_com_sucesso() {
		try {
			Thread.sleep(2000);
			seleniumTestPage = new SeleniumTestPage(getDriver());
			seleniumTestPage.validateSucessfullyLogin();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
