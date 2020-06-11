package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class shopCreateAccountPage {

	public static final String URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation";

	private WebDriver driver;

	public shopCreateAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/form/div[1]/div[2]/input")
	WebElement firstNameField;

	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/form/div[1]/div[3]/input")
	WebElement lastNameField;

	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/form/div[1]/div[5]/input")
	WebElement PasswordField;

	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/form/div[2]/p[4]/input")
	WebElement addressField;

	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/form/div[2]/p[6]/input")
	WebElement cityField;

	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/form/div[2]/p[7]/div/select")
	WebElement stateField;

	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/form/div[2]/p[8]/input")
	WebElement zipCodeField;

	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/form/div[2]/p[13]/input")
	WebElement phoneField;

	@FindBy(css = "#submitAccount")
	WebElement register;

	public void createAccount(String firstName, String lastName, String password, String address, String city,
			String state, String zipCode, String phone) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		PasswordField.sendKeys(password);
		addressField.sendKeys(address);
		cityField.sendKeys(city);
		new Select(stateField).selectByVisibleText(state);
		zipCodeField.sendKeys(zipCode);
		phoneField.sendKeys(phone);
		register.click();
	}

}
