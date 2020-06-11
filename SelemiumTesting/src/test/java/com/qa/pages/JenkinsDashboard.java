package com.qa.pages;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class JenkinsDashboard {

	public static final String URL = "http://localhost:8080/";

	private WebDriver driver;
	private ExtentTest test;

	public JenkinsDashboard(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#name")
	WebElement newProjectName;

	@FindBy(css = "#j-add-item-type-standalone-projects > ul > li.hudson_model_FreeStyleProject")
	WebElement freestyleProjectButton;

	@FindBy(css = "#ok-button")
	WebElement okButton;

	@FindBy(css = "#yui-gen38-button")
	WebElement saveButton;

	@FindBy(css = "#itemname-invalid")
	WebElement errorMsg;

	@FindBy(css = "#tasks > div:nth-child(3) > a.task-link")
	WebElement createUser;

	@FindBy(css = "#username")
	WebElement usernameField;

	@FindBy(xpath = "/html/body/div[4]/div[2]/form/div[1]/table/tbody/tr[2]/td[2]/input")
	WebElement passwordField;

	@FindBy(xpath = "/html/body/div[4]/div[2]/form/div[1]/table/tbody/tr[3]/td[2]/input")
	WebElement confirmPasswordField;

	@FindBy(xpath = "/html/body/div[4]/div[2]/form/div[1]/table/tbody/tr[4]/td[2]/input")
	WebElement fullNameField;

	@FindBy(xpath = "/html/body/div[4]/div[2]/form/div[1]/table/tbody/tr[5]/td[2]/input")
	WebElement emailField;

	@FindBy(css = "#yui-gen1-button")
	WebElement createUserButton;

	@FindBy(xpath = "/html/body/div[4]/div[2]/form/div[1]/div")
	WebElement userExists;

	public void newProject(String name) {
		driver.get("http://localhost:8080/view/all/newJob");
		newProjectName.sendKeys(name);
		freestyleProjectButton.click();
		if (errorMsg.isDisplayed()) {
			test.fail("Project Already Exists!");
			fail();
		}
		okButton.click();
		saveButton.click();
		driver.get(URL);
	}

	public void addUser(String username, String fullName, String password, String email) {
		driver.get("http://localhost:8080/securityRealm/");
		createUser.click();
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
		fullNameField.sendKeys(fullName);
		emailField.sendKeys(email);
		createUserButton.click();
		try {
			if (userExists.isDisplayed()) {
				test.fail("User already exists!");
				fail();
			}
		} catch (Exception e) {

		}

	}

	public List<String> getUsers() {
		driver.get("http://localhost:8080/securityRealm/");
		List<WebElement> usernameElements = driver
				.findElements(By.xpath("/html/body/div[4]/div[2]/table/tbody/tr[position()>1]/td[2]/a"));
		List<String> usernames = new ArrayList<String>();
		for (WebElement element : usernameElements) {
			usernames.add(element.getText());
		}
		return usernames;
	}

}
