package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JenkinsLogin {

	public static final String URL = "http://localhost:8080/";

	private WebDriver driver;

	public JenkinsLogin(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#j_username")
	WebElement usernameField;

	@FindBy(css = "body > div > div > form > div:nth-child(2) > input")
	WebElement passwordField;

	@FindBy(css = "body > div > div > form > div.submit.formRow > input")
	WebElement submitButton;

	public void login(String username, String password) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		submitButton.click();
	}

}
